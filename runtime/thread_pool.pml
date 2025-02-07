#define MAX_THREADS 8
#define THREAD_MUTEX_COUNT 2
#define MAX_MEMORY MAX_THREADS*THREAD_MUTEX_COUNT
#define UNLOCKED 0
#define LOCKED 1

// WASM Linear Memory
byte M0[MAX_MEMORY];

// WASM ATOMIC Instructions
bool M0_WAIT_NOTIFY[MAX_MEMORY];

inline wait(address){
    do
    :: M0_WAIT_NOTIFY[address] == 1 -> break;  /* Proceed when notified */
    :: M0_WAIT_NOTIFY[address] == 0 -> skip;
    od
    M0_WAIT_NOTIFY[address] = false;
}

inline notify(address){
     atomic {
        /* Notify the waiting process */
        M0_WAIT_NOTIFY[address] = true;
    }
}

// WAPC WASM Mutex

inline try_lock_mutex(mutex_address){
    byte mutex = M0[mutex_address];
    if
    :: mutex == UNLOCKED ->
        M0[mutex_address] = LOCKED;
        success = true;
    :: else -> skip;
    fi
}

inline lock_mutex(mutex_address){
    //printf("Lock Mutex %d\n", mutex_address);
    bool success = false;
    do
        :: try_lock_mutex(mutex_address)
        if
        :: success -> break;
        :: else -> skip;
        fi
        wait(mutex_address);
    od
}

inline unlock_mutex(mutex_address){
    //printf("Unlock Mutex %d\n", mutex_address);
    atomic {
        M0[mutex_address] = UNLOCKED;
    }
    notify(mutex_address);
}

inline join_mutex(mutex_address){
    lock_mutex(mutex_address);
    unlock_mutex(mutex_address);
}

// WAPC Threads



#define __wack_thread_mutex1 0
#define __wack_thread_mutex2 1

#define thread_property_address(tid,prop) ((tid*THREAD_MUTEX_COUNT)+prop) 


// WAPC Thread Pool
mtype = { NOT_INITED, INITED, DESTROY };
mtype pool_state = NOT_INITED;
int kernel_id = -1;

// WAPC WASI Thread Start

proctype WasiThread(int tid) {
    //printf("Thread %d started.\n", tid);
    int mutex1 = thread_property_address(tid, __wack_thread_mutex1);
    int mutex2 = thread_property_address(tid, __wack_thread_mutex2);
    do
    :: pool_state == DESTROY -> break;
    :: M0[mutex1] == LOCKED ->
        join_mutex(mutex2);

        if
        :: pool_state == DESTROY -> break;
        :: else -> skip;
        fi

        // Simulate work
        printf("Thread %d: Running Kernel %d\n", tid, kernel_id);

        lock_mutex(mutex2);
        unlock_mutex(mutex1);
    :: else -> skip;
    od
    printf("Thread %d exited.\n", tid);
}

inline make_thread_pool(){
    if
    :: pool_state == NOT_INITED ->
        int _p_tid = 0;
        do
        :: (_p_tid < MAX_THREADS) ->
            int m1 = thread_property_address(_p_tid, __wack_thread_mutex1);
            int m2 = thread_property_address(_p_tid, __wack_thread_mutex2);
            M0[m1] = UNLOCKED;
            M0[m2] = LOCKED;
            run WasiThread(_p_tid);
            _p_tid = _p_tid + 1;
        :: (_p_tid >= MAX_THREADS) -> break;
        od
        pool_state = INITED;
    :: pool_state == INITED -> skip;
    fi;
}

inline destroy_thread_pool(){
    if
    :: pool_state == INITED ->
        pool_state = DESTROY;
    int tt = 0;
     do
     :: (tt < MAX_THREADS) ->
             int m1 = thread_property_address(tt, __wack_thread_mutex1);
             int m2 = thread_property_address(tt, __wack_thread_mutex2);
             unlock_mutex(m2);
             tt = tt + 1;
     :: (tt >= MAX_THREADS) -> break;
     od
     fi
}

inline wapc_parallel(_kernel_id) {
    kernel_id = _kernel_id;
    int thread = 0;
    do
    :: (thread < MAX_THREADS) ->
            int m1 = thread_property_address(thread, __wack_thread_mutex1);
            int m2 = thread_property_address(thread, __wack_thread_mutex2);
            lock_mutex(m1);
            unlock_mutex(m2);
            thread = thread + 1;
    :: (thread >= MAX_THREADS) -> break;
    od
    thread = 0;
    do
    :: (thread < MAX_THREADS) ->
            int m1_2 = thread_property_address(thread, __wack_thread_mutex1); 
            join_mutex(m1_2);
            thread = thread + 1;
    :: (thread >= MAX_THREADS) -> break;
    od
}

init {
    make_thread_pool();
    wapc_parallel(0);
    wapc_parallel(1);
    wapc_parallel(2);
    destroy_thread_pool();
}
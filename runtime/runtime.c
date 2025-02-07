typedef mutex_t int;

/*
 * Imports
*/
__attribute__((import_module("wasi"), import_name("thread-spawn")))
int thread_spawn(int arg);
void call_kernel(int kernel_id);
void try_lock_mutex(int mutex_address);
void lock_mutex(int mutex_address);
void unlock_mutex(int mutex_address);
void wait_mutex_lock(int mutex_address);

/*
 * Globals
*/

// M2[0]
void set_max_threads(int threads);
int get_max_threads();

// M2[1]
void set_stack_base(int stack);
void get_stack_base();

// M2[2]
void set_kernel_id(int kernel_id);
void get_kernel_id();

// M2[3]
void set_thread_pool_state(int i); // 0 -> not-inited, 1 -> inited
int get_thread_pool_init();

/*
 * Structs
*/

int __wack_thread_mutex1 = 0;
int __wack_thread_mutex2 = 1;
/*
 * when running in serial: mutex1 = 0, mutex2 = 1
 * when running in parallel: mutex1 = 1, mutex2 = 0
*/
struct wack_thread {
    mutex_t mutex1;
    mutex_t mutex2;
}
__attribute__((export_name("get_wack_thread_property_address")))
int get_thread_property_address(int thread_id, int property){
    return (thread_id * sizeof(struct wack_thread)) + (property * sizeof(int));
}

/*
 * Functions
*/

void make_thread_pool(int max_threads){
    if(get_thread_pool_state() == 1) {
        return;
    }
    set_max_threads(max_threads);
    for(int i=0;i<max_threads;i++){
        unlock_mutex(mutex1);
        lock_mutex(mutex2);
        if(thread_spawn(i) < 0){
            exit(1);
        }
    }
    set_thread_pool_state(1);
}

void destroy_thread_pool(){
    if(get_thread_pool_init() == 0) {
        return;
    }
    set_thread_pool_state(0);
    set_max_threads(0);
}

__attribute__((export_name("parallel")))
void parallel(int kernel_id) {
    make_thread_pool(8);
    set_kernel_id(kernel_id);
    num_threads = get_max_threads();
    for (int i = 0; i < num_threads; ++i) {
        lock_mutex(get_wack_thread_property_address(i, __wack_thread_mutex1));
        unlock_mutex(get_wack_thread_property_address(i, __wack_thread_mutex2));
    }
    for (int i = 0; i < num_threads; ++i){
        join_mutex(get_wack_thread_property_address(i, __wack_thread_mutex1));
    }
    destroy_thread_pool();
}

/*
 * WASI-threads
*/
__attribute__((export_name("wasi_thread_start")))
void wasi_thread_start(int id, int tid){
    int mutex1 = get_wack_thread_property_address(tid, __wack_thread_mutex1);
    int mutex2 = get_wack_thread_property_address(tid, __wack_thread_mutex2);
    while(true){
            join_mutex(mutex2);
            call_kernel(get_kernel_id(), tid);
            lock_mutex(mutex2);
            unlock_mutex(mutex1);
    }
}
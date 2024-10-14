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

/*
 * Structs
*/

int __wack_tid = 0;
int __wack_thread_mutex = 1;
int __wack_thread_state = 2;
int __status_stopped = 0;
int __status_started = 1;
int __status_has_task = 2;
int __status_running_task = 3;
int __status_task_done = 4;
int __status_signal_exit = 5;
struct wack_thread {
    int tid;
    mutex_t mutex;
    int state; // 0 -> stopped, 1 -> started, 2 -> has_task, 3 -> running_task, 4 -> task_done, 5 -> signal_exit
}
/*
 * Property Table:
 * 0 -> tid
 * 1 -> mutex
 * 2 -> running
 * 3 -> exit
*/
__attribute__((export_name("get_wack_thread_property_address")))
int get_thread_property_address(int thread_id, int property){
    return (thread_id * sizeof(struct wack_thread)) + (property * sizeof(int));
}
__attribute__((export_name("get_wack_thread_property")))
int get_thread_property(int thread_id, int property){
    return *(get_thread_property_address(thread_id, property));
}
__attribute__((export_name("set_wack_thread_property")))
void set_thread_property(int thread_id, int property, int value){
    *(get_thread_property_address(int thread_id, int property)) = value;
}

/*
 * Functions
*/

void make_thread_pool(int max_threads){
    if(get_max_threads() != 0){
        return;
    }
    set_max_threads(max_threads);
    for(int i=0;i<max_threads;i++){
        set_thread_property(i, __wack_thread_id, i);
        lock_mutex(get_thread_property_address(__wack_thread_mutex));
        if(thread_spawn(i) < 0){
            exit(1);
        }
    }
}

__attribute__((export_name("parallel")))
void parallel(int kernel_id) {
    set_kernel_id(kernel_id);
    num_threads = get_max_threads();
    for (int i = 0; i < num_threads; ++i) {
        set_wack_thread_property(i, __wack_thread_state, __status_has_task);
        unlock_mutex(get_wack_thread_property_address(i, __wack_thread_mutex));
    }
    for (int i = 0; i < num_threads; ++i){
        while(get_wack_thread_property(i, __wack_thread_state) != __status_task_done){}
        lock_mutex(get_wack_thread_property_address(i, __wack_thread_mutex));
    }
}

/*
 * WASI-threads
*/
__attribute__((export_name("wasi_thread_start")))
void wasi_thread_start(int id, int tid){
    set_wack_thread_property(tid, __wack_thread_state, __status_started);
    int mutex = get_wack_thread_property_address(tid, __wack_thread_mutex);
    while(true){
            int state = get_wack_thread_property(tid, __wack_thread_exit);
            lock_mutex(mutex);
            if(state == __status_signal_end){
                  break;
            }
            if(state == __status_has_task){
                set_wack_thread_property(tid, __wack_thread_state, __status_running_task);
                call_kernel(get_kernel_id(), tid);
                set_wack_thread_property(tid, __wack_thread_state, __status_task_done);
            }
            unlock_mutex(mutex);
    }
    set_wack_thread_property(tid, __wack_thread_state, __status_stopped);
}
struct wack_thread {
    int thread_id;
    int kernel_id;
    int stack_base;
}

int thread_address(int thread_id){
    return thread_id * sizeof(struct wack_thread);
}

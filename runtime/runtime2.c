
#include "pthread.h"

int num_threads = 8;

struct {
    int thread_id;
    int kernel_id;
    long N;
}


__attribute__((export_name("wasi_thread_start")))
void wasi_thread_start(int threadId, int arg){

}
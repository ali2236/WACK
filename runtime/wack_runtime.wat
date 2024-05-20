;; runtime template
;; not used by compiler
;; for demenstration purposes only
(module
  (type $kernel_type (func (param i32)))
  (type $thread_spawn_type (func (param i32) (result i32)))
  (type $thread_start_type (func (param i32 i32)))
  (import "wasi" "thread-spawn" (func (;0;) (type $thread_spawn_type)))
  (func $wasi_thread_start (type $thread_start_type) (param i32 i32)
     ;; unsigned int thread_id = args & 0x0000FFFF;
     local.get 1
     i32.const 65535
     i32.and
     ;; thread_num param
     ;; unsigned int kernel_id = (args & 0xFFFF0000) >> 16;
     local.get 1
     i32.const 4294901760
     i32.and
     i32.const 16
     i32.shr_u
     ;; function index
     ;; table[kernel_id](thread_id)
     call_indirect (type $kernel_type)
     ;; TODO: implement a thread barier
  )
  (global $num_threads (mut i32) (i32.const 8)) ;; $num_threads
  (export "wasi_thread_start" (func $wasi_thread_start))
  ;; must be generated (table 32 funcref)
  ;; must be generated (elem (i32.const 0) func $kernel_function_ids)
)
;; runtime template
;; not used by compiler
;; for demenstration purposes only
(module
  (type $main_type (func))
  (type $kernel_type (func (param i32)))
  (type $thread_spawn_type (func (param i32) (result i32)))
  (type $thread_start_type (func (param i32 i32)))
  (type $arg_encode_type (func (param i32 i32) (result i32)))
  (type $arg_decode_type (func (param i32) (result i32 i32)))
  (import "wasi" "thread-spawn" (func $thread_spawn (type $thread_spawn_type)))
  (func $try_lock_mutex (type $thread_spawn_type) (param $mutex_address i32) (result i32)
    local.get $mutex_address
    i32.const 0 ;; expected
    i32.const 1 ;; locked
    i32.atomic.rmw.cmpxchg (;$runtime_memory;)
    i32.eqz
  )
   (func $lock_mutex (type $kernel_type) (param $mutex_address i32)
      block $done
        loop $retry
          ;; Try to lock the mutex. $tryLockMutex returns 1 if the mutex
          ;; was locked, and 0 otherwise.
          local.get $mutex_address
          call $try_lock_mutex
          br_if $done
          ;; Wait for the other agent to finish with mutex.
          local.get $mutex_address ;; mutex address
          i32.const 1              ;; expected value (1 => locked)
          i64.const -1             ;; infinite timeout
          memory.atomic.wait32 (;$runtime_memory;)
          drop
          br $retry
        end
      end
   )
  (func $unlock_mutex (type $kernel_type) (param $mutex_address i32)
      ;; mutex must be locked before by caller
      ;; Unlock the mutex.
      local.get $mutex_address     ;; mutex address
      i32.const 0              ;; 0 => unlocked
      i32.atomic.store (;$runtime_memory;)

      ;; Notify one agent that is waiting on this lock.
      local.get $mutex_address   ;; mutex address
      i32.const 1            ;; notify 1 waiter
      memory.atomic.notify (;$runtime_memory;)
      drop
   )
  (func $wait_mutex_lock (type $kernel_type) (param $mutex_address i32)
    local.get $mutex_address
    call $lock_mutex
    local.get $mutex_address
    call $unlock_mutex
  )
  ;; thread_id ^ (kernel_id << 16)
  (func $encode_arg (type $arg_encode_type) (param i32 i32) (result i32)
    local.get 0
    local.get 1
    i32.const 16
    i32.shl
    i32.xor
  )
  (func $decode_arg (type $arg_decode_type) (param i32) (result i32 i32)
    ;; unsigned int thread_id = args & 0x0000FFFF;
    local.get 0
    i32.const 0x0000FFFF
    i32.and
    ;; thread_num param
    ;; unsigned int kernel_id = (args & 0xFFFF0000) >> 16;
    local.get 0
    i32.const 0xFFFF0000
    i32.and
    i32.const 16
    i32.shr_u
    ;; function index
  )

  (func $wasi_thread_start (type $thread_start_type) (param i32 i32)
    (local i32 i32)
     ;; unsigned int thread_id = args & 0x0000FFFF;
     local.get 1
     i32.const 0x0000FFFF
     i32.and
     local.set 2
     ;; thread_num param
     ;; unsigned int kernel_id = (args & 0xFFFF0000) >> 16;
     local.get 1
     i32.const 0xFFFF0000
     i32.and
     i32.const 16
     i32.shr_u
     local.set 3
     ;; function index

     ;; table[kernel_id](thread_id)
     local.get 2
     local.get 3
     call_indirect $kernels (type $kernel_type)

     ;; unlock mutex
     local.get 2
     call $unlock_mutex
  )
  (func $test (type $main_type)
    (local i32 i32)
    i32.const 0
    call $lock_mutex
    i32.const 0
    call $unlock_mutex
    i32.const 0
    call $wait_mutex_lock
  )
  (memory (;$runtime_memory;) 4 4 shared) ;; 64kb for thread join lock
  (global $num_threads (mut i32) (i32.const 8)) ;; $num_threads
  (export "wasi_thread_start" (func $wasi_thread_start)) ;; not for public use
  (export "_start" (func $test))
  (table $kernels 32 32 funcref) ;; must be generated
  ;;(elem (table $k) (i32.const 0) func $kernel_function_ids) ;; must be generated
)
;; runtime template
;; not used by compiler
;; for demenstration purposes only
(module
  (type $kernel_type (func (param i32)))
  (type $thread_spawn_type (func (param i32) (result i32)))
  (type $thread_start_type (func (param i32 i32)))
  (import "wasi" "thread-spawn" (func $thread_spawn (type $thread_spawn_type)))
  (func $try_lock_mutex (type $thread_spawn_type) (param $mutex_address i32) (result i32)
    local.get $mutex_address
    i32.const 0 ;; expected
    i32.const 1 ;; locked
    i32.atomic.rmw.cmpxchg $runtime_memory
    i32.eqz
  )
   (func $lock_mutex (param $mutex_address i32)
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
          memory.atomic.wait32
          drop
          br $retry
        end
      end
   )
  (func $unlock_mutex (param $mutex_address i32)
      ;; Unlock the mutex.
      local.get $mutex_address     ;; mutex address
      i32.const 0              ;; 0 => unlocked
      i32.atomic.store

      ;; Notify one agent that is waiting on this lock.
      local.get $mutex_address   ;; mutex address
      i32.const 1            ;; notify 1 waiter
      memory.atomic.notify $runtime_memory
      drop
   )
  (func $thread_join (type $kernel_type) (param $mutex_address i32)
    ;; TODO: check mutex locked
    local.get $mutex_address     ;; mutex address
    i32.const 0              ;; 0 => unlocked
    i32.atomic.store
    ;; TOOD: wait for it to unlock
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

     ;; lock thread mutex
     local.get 2
     call $lock_mutex

     ;; table[kernel_id](thread_id)
     local.get 2
     local.get 3
     call_indirect (type $kernel_type)

     ;; unlock mutex
     local.get 2
     call $unlock_mutex
  )
  (memory $runtime_memory 4 4 shared) ;; 64kb for thread join lock
  (global $num_threads (mut i32) (i32.const 8)) ;; $num_threads
  (export "wasi_thread_start" (func $wasi_thread_start)) ;; not for public use

  ;; must be generated (table $k funcref)
  ;; must be generated (elem (i32.const 0) func $kernel_function_ids)
)
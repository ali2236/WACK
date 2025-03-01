;; runtime template
;; not used by tools
;; for demenstration purposes only
(module
  (type $main_type (func))
  (type $kernel_type (func (param i32)))
  (type $thread_spawn_type (func (param i32) (result i32)))
  (type $thread_start_type (func (param i32 i32)))
  (type $arg_encode_type (func (param i32 i32) (result i32)))
  (type $arg_decode_type (func (param i32) (result i32)))
  (type $set_i32_type (func (param i32)))
  (type $get_i32_type (func (result i32)))
  (import "wasi" "thread-spawn" (func $thread_spawn (type $thread_spawn_type)))
  (import "env" "memory" (memory $runtime_mutex_memory 4 4 shared))
  (import "env" "memory-1"  (memory $runtime_memory 1 1 shared))
  (func $nothing (type $kernel_type) (param $thread_id i32)
    nop
  )
  (func $try_lock_mutex (type $thread_spawn_type) (param $mutex_address i32) (result i32)
    local.get $mutex_address
    i32.const 0 ;; expected
    i32.const 1 ;; locked
    i32.atomic.rmw.cmpxchg (;$runtime_mutex_memory;)
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
          memory.atomic.wait32 (;$runtime_mutex_memory;)
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
      i32.atomic.store (;$runtime_mutex_memory;)

      ;; Notify one agent that is waiting on this lock.
      local.get $mutex_address   ;; mutex address
      i32.const 1            ;; notify 1 waiter
      memory.atomic.notify (;$runtime_mutex_memory;)
      drop
   )
  (func $wait_mutex_lock (type $kernel_type) (param $mutex_address i32)
    local.get $mutex_address
    call $lock_mutex
    local.get $mutex_address
    call $unlock_mutex
  )
  ;; r
  (func $encode_arg (type $arg_encode_type) (param i32 i32) (result i32)
    local.get 0
    local.get 1
    i32.const 16
    i32.shl
    i32.xor
  )
  (func $decode_kernel_id (type $arg_decode_type) (param i32) (result i32)
    ;; unsigned int kernel_id =r;
    local.get 0
    i32.const 0x0000FFFF
    i32.and
    ;; function index
  )
  (func $decode_thread_id  (type $arg_decode_type) (param i32) (result i32)
    ;; unsigned int thread_id  = (args & 0xFFFF0000) >> 16;
    local.get 0
    i32.const 0xFFFF0000
    i32.and
    i32.const 16
    i32.shr_u
    ;; thread_num param
  )
  (func $set_stack_base (type $set_i32_type) (param $stack_base i32)
    i32.const 0
    local.get $stack_base
    i32.store ;;$runtime_memory
  )
  (func $get_stack_base (type $get_i32_type)
    i32.const 0
    i32.load ;;$runtime_memory
  )
  (func $parallel (type $kernel_type) (param $kernel_id i32)
    (local $i i32)
    i32.const 0
    local.set $i
    loop
        local.get $i
        global.get $num_threads
        i32.lt_u
        if
            local.get $i ;; thread_id
            i32.const 4
            i32.mul
            call $lock_mutex
            local.get $i ;; thread_id
            local.get $kernel_id
            call $encode_arg
            call $thread_spawn
            i32.const 0
            i32.lt_u
            if
                unreachable
            end
            local.get $i
            i32.const 1
            i32.add
            local.set $i
            br 1
        end
    end
    i32.const 0
    local.set $i
    loop ;; join all
        local.get $i
        global.get $num_threads
        i32.lt_u
        if
            local.get $i ;; thread_id
            i32.const 4
            i32.mul
            call $wait_mutex_lock
            local.get $i
            i32.const 1
            i32.add
            local.set $i
            br 1
        end
    end
  )
  (func $wasi_thread_start (type $thread_start_type) (param i32 i32)
    (local i32 i32)
     ;; unsigned int thread_id = args & 0x0000FFFF;
     local.get 1
     call $decode_thread_id
     local.set 2
     ;; thread_num param
     ;; unsigned int kernel_id = (args & 0xFFFF0000) >> 16;
     local.get 1
     call $decode_kernel_id
     local.set 3
     ;; function index

     ;; table[kernel_id](thread_id)
     local.get 2
     local.get 3
     call_indirect $kernels (type $kernel_type)

     ;; unlock mutex
     local.get 2
     i32.const 4
     i32.mul
     call $unlock_mutex
  )
  (func $wasi_thread_start2 (type $thread_start_type) (param i32 i32)
     i32.const 0
     call $lock_mutex
     loop
        br 0
     end
  )
  (func $test (type $main_type)
    ;;i32.const 0
    ;;call $lock_mutex
    i32.const 0
    call $thread_spawn
    drop
    i32.const 0
    call $lock_mutex
    ;;i32.const 0
    ;;call $wait_mutex_lock
  )
  (export "memory" (memory $runtime_mutex_memory)) ;; 64kb * 4 for thread join lock
  (export "memory-1" (memory $runtime_memory)) ;; 64kb for {int stack_base}
  (global $num_threads (mut i32) (i32.const 8)) ;; $num_threads
  (export "wasi_thread_start" (func $wasi_thread_start2)) ;; not for public use
  (export "_start" (func $test))
  (table $kernels 32 32 funcref) ;; must be generated
  (elem (table $kernels) (i32.const 0) func $nothing) ;; must be generated
)
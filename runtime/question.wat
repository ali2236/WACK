(module
  (type $main_type (func))
  (type $simple_type (func (param i32)))
  (type $thread_spawn_type (func (param i32) (result i32)))
  (type $thread_start_type (func (param i32 i32)))
  (import "wasi" "thread-spawn" (func $thread_spawn (type $thread_spawn_type)))
  (func $try_lock_mutex (type $thread_spawn_type) (param $mutex_address i32) (result i32)
    local.get $mutex_address
    i32.const 0 ;; expected
    i32.const 1 ;; locked
    i32.atomic.rmw.cmpxchg
    i32.eqz
  )
   (func $lock_mutex (type $simple_type) (param $mutex_address i32)
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
  (func $unlock_mutex (type $simple_type) (param $mutex_address i32)
      ;; mutex must be locked before by caller
      ;; Unlock the mutex.
      local.get $mutex_address     ;; mutex address
      i32.const 0              ;; 0 => unlocked
      i32.atomic.store

      ;; Notify one agent that is waiting on this lock.
      local.get $mutex_address   ;; mutex address
      i32.const 1            ;; notify 1 waiter
      memory.atomic.notify
      drop
   )
  (func $wait_mutex_lock (type $simple_type) (param $mutex_address i32)
    local.get $mutex_address
    call $lock_mutex
    local.get $mutex_address
    call $unlock_mutex
  )
  (func $wasi_thread_start (type $thread_start_type) (param i32 i32)
     local.get 1
     call $lock_mutex
     loop
        br 0
     end
     local.get 1
     call $unlock_mutex
  )
  (func $main (type $main_type)
    (local i32)
    i32.const 0
    call $thread_spawn
    drop
    i32.const 100000000
    local.set 0
    loop
        local.get 0
        i32.const 1
        i32.sub
        local.tee 0
        i32.eqz
        br_if 0
    end
    i32.const 0
    call $wait_mutex_lock
  )
  (memory (;0;) 4 4 shared)
  (export "memory" (memory 0))
  (export "wasi_thread_start" (func $wasi_thread_start))
  (export "_start" (func $main))
)
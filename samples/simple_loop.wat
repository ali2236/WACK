(module
  (type (;0;) (func (param i32)))
  (type (;1;) (func))
  (import "wasi_snapshot_preview1" "proc_exit" (func (;0;) (type 0)))
  (func (;1;) (type 1)
    (local i32 i32)
    block  ;; label = @1
      i32.const 1024
      i32.load
      i32.eqz
      if  ;; label = @2
        i32.const 1024
        i32.const 1
        i32.store
        i32.const 306560
        local.tee 0
        i32.const 0
        i32.store offset=12
        local.get 0
        i32.const 0
        i32.store offset=8
        loop  ;; label = @3
          local.get 0
          i32.load offset=8
          i32.const 30000
          i32.lt_s
          if  ;; label = @4
            local.get 0
            i32.load offset=8
            i32.const 2
            i32.shl
            local.tee 1
            i32.const 121040
            i32.add
            local.get 1
            i32.const 1040
            i32.add
            i32.load
            i32.store
            local.get 0
            local.get 0
            i32.load offset=8
            i32.const 1
            i32.add
            i32.store offset=8
            br 1 (;@3;)
          end
        end
        local.get 0
        i32.load offset=12
        local.tee 0
        br_if 1 (;@1;)
        return
      end
      unreachable
    end
    local.get 0
    call 0
    unreachable)
  (memory (;0;) 5)
  (export "memory" (memory 0))
  (export "_start" (func 1)))

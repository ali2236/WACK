(module
  (type (;0;) (func))
  (func (;0;) (type 0)
    (local i32)
    i32.const 1024
    i32.load
    i32.eqz
    if  ;; label = @1
      i32.const 1024
      i32.const 1
      i32.store
      global.get 0
      i32.const 80032
      i32.sub
      local.tee 0
      global.set 0
      local.get 0
      i32.const 0
      i32.store offset=80028
      local.get 0
      i32.const 10000
      i32.store offset=4
      local.get 0
      i32.const 1
      i32.store offset=8
      loop  ;; label = @2
        local.get 0
        i32.load offset=8
        local.get 0
        i32.load offset=4
        i32.lt_s
        if  ;; label = @3
          local.get 0
          local.get 0
          i32.load offset=12
          local.get 0
          i32.const 40016
          i32.add
          local.get 0
          i32.load offset=8
          i32.const 2
          i32.shl
          i32.add
          i32.load
          i32.add
          i32.store offset=12
          local.get 0
          local.get 0
          i32.load offset=8
          i32.const 1
          i32.add
          i32.store offset=8
          br 1 (;@2;)
        end
      end
      local.get 0
      i32.const 1
      i32.store offset=8
      loop  ;; label = @2
        local.get 0
        i32.load offset=8
        local.get 0
        i32.load offset=4
        i32.lt_s
        if  ;; label = @3
          local.get 0
          local.get 0
          i32.load offset=12
          local.get 0
          i32.const 40016
          i32.add
          local.get 0
          i32.load offset=8
          i32.const 2
          i32.shl
          i32.add
          i32.load
          i32.add
          i32.store offset=12
          local.get 0
          local.get 0
          i32.load offset=8
          i32.const 1
          i32.add
          i32.store offset=8
          br 1 (;@2;)
        end
      end
      local.get 0
      i32.const 80032
      i32.add
      global.set 0
      return
    end
    unreachable)
  (memory (;0;) 2)
  (global (;0;) (mut i32) (i32.const 66576))
  (export "memory" (memory 0))
  (export "_start" (func 0)))

(module
  (type $main_type (func))
  (type $simple_type (func (param i32)))
  (import "wack_runtime" "_start" (func $runtime_start (type $main_type)))
  (import "wack" "_start" (func $program_start (type $main_type)))
  (func $main (type $main_type)
    (local i32)
    call $runtime_start
    call $program_start
  )
  (memory (;0;) 2 10 shared)
  (export "memory" (memory 0))
  (export "_start" (func $main))
)
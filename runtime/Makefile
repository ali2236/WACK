build_glue:
	wat2wasm glue.wat --enable-threads -o glue.wasm

build: wack_runtime.wat
	wat2wasm.exe wack_runtime.wat --enable-threads -o runtime.wasm

run: build
	wasmtime -W all-proposals=y -S threads .\runtime.wasm

build2: question.wat
	wat2wasm.exe question.wat --enable-threads -o q.wasm

run2: build2
	wasmtime -W all-proposals=y -S threads .\q.wasm

#wasmer --enable-all .\q.wasm

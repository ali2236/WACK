build: wack_runtime.wat
	wat2wasm.exe wack_runtime.wat --enable-threads --enable-multi-memory -o runtime.wasm

run: build
	wasmtime -S threads .\runtime.wasm
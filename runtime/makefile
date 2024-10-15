build: wack_runtime.wat
	wat2wasm.exe wack_runtime.wat --enable-threads -o runtime.wasm

run: build
	wasmtime -W all-proposals=y -S threads .\runtime.wasm
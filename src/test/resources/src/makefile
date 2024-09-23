CC = clang
CFLAGS = -Wall
SYSROOT=C:\Users\Aligator\projects\matrix_multiply_test\sysroot

all: $(patsubst %.c, %.wasm, $(wildcard *.c))

%.wasm: %.c
	$(CC) --sysroot=$(SYSROOT) --target=wasm32-wasi -o $@ $<
	wasm-opt -O1 $@ -o $@

clean:
	rm *.wasm
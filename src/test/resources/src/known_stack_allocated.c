#include <stdio.h>
#include "debug_print.h"
#define N 3

int main() {
    int a[N][N];
    int b[N][N];
    int c[N][N];

    // matrix fill
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            a[i][j] = i+j;
            b[i][j] = i-j;
            c[i][j] = 5;
        }
    }

    // matrix multiply
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                c[i][j] = a[i][k] * b[k][j];
            }
        }
    }

    // matrix reduction
    int sum = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            sum += c[i][j];
        }
    }

    // print stats
    printf("checksum=%d\n", sum);

    return 0;
}
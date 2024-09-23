#include <stdio.h>
#include <stdlib.h>
const int _N = 500;
void matrix_multiply(int A[_N][_N], int B[_N][_N], int C[_N][_N], int n){
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                C[i][j] = A[i][k] * B[k][j];
            }
        }
    }
}

int main() {
    int N = _N;
    int** a = (int**) malloc(sizeof(int) * N * N);
    int** b = (int**) malloc(sizeof(int) * N * N);
    int** c = (int**) malloc(sizeof(int) * N * N);
    // matrix fill
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            a[i][j] = i+j;
            b[i][j] = i-j;
            c[i][j] = 5;
        }
    }

    // matrix multiply
    matrix_multiply(a, b, c, N);

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
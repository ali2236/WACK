//
// Created by Aligator on 10/17/2024.
//

#ifndef POLYBENCHC_4_2_1_DEBUG_PRINT_H
#define POLYBENCHC_4_2_1_DEBUG_PRINT_H

#include <stdio.h>

__attribute__((export_name("print_i32")))
void print_i32(int i){
    printf("%d\n", i);
}

__attribute__((export_name("print_2_i32")))
void print_2_i32(int i, int j){
    printf("%d,%d\n", i, j);
}

#endif //POLYBENCHC_4_2_1_DEBUG_PRINT_H

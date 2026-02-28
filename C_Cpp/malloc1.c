#include <stdio.h>
#include<stdlib.h>
int main() {
    int n=3;
    int * array;
    array = (int*) malloc(n*sizeof(int));
    if(array==NULL){
        printf("Memory allocation failed.");
        return 1;
    }

    for(int i=0; i<n; i++){
        array[i] = i+1;
    }
    for(int i=0; i<n; i++){
        printf("%d",array[i]);
    }
    printf("\n");
    int num = 5;
    array = (int*) realloc(array, (num*sizeof(int)));
    if(array==NULL){
        printf("Memory allocation failed.");
        return 1;
    }

    for(int i=n; i<num; i++){
        array[i] = i+1;
    }
    for(int i=0; i<num; i++){
        printf("%d",array[i]);
    }
    free(array);
    return 0;
}
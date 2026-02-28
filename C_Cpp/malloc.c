#include <stdio.h>
#include<stdlib.h>
int main() {
    int n=3;
    int *arr;
    arr = (int*) malloc(n*sizeof(int));
    if(arr==NULL){
        printf("Memory allocation failed.");
        return 1;
    }

    for(int i=0; i<n; i++){
        arr[i] = i+ 1;
    }

    for(int i=0; i<n; i++){
        printf("%d ",arr[i]);
    }
    printf("\n"); 

   int num = 5;
   arr = (int*) realloc(arr, (num* sizeof(int)));

   if(arr==NULL){
    printf("Memory allocation failed.");
    return 1;
   }
   for(int i= n; i<num; i++){
    arr[i] = i+1;
   }
   for(int i=0; i<num; i++){
    printf("%d ",arr[i]);
   }
    free(arr);

    return 0;
}
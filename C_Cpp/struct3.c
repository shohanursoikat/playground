#include <stdio.h>

int main() {
    #define n 10
    
 
    int arr[n];
    printf("enter elements: ");
    for(int i=0; i<n; i++){
        scanf("%d",&arr[i]);
    }
    for(int i=0; i<n; i++){
        printf("elements: %d\n",arr[i]);
    }
    return 0;
}
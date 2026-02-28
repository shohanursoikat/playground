#include<stdio.h>
int main(){
    int arr[]={1,2,3,4,5};
    int size=5;
    int index=3;
    for(int i=size; i>index; i--){
        arr[i]=arr[i+1];
    }
    arr[index]=3;
    size--;

    for(int i=0; i<size; i++ ){
    printf("%d",arr[i]);
    }


    return 0;
}
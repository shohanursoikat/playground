#include<stdio.h>
int main(){
    int avg;
    int sum=0;
    int num []={1,2,3,4,5};
    int size = sizeof(num) / sizeof(num[0]);

    for(int i=0; i<size; i++){
        sum+=num[i];
    }
    avg = sum/size;

    printf("%d",avg);


    return 0;
}
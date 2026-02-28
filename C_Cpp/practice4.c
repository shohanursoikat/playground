#include<stdio.h>
int main(){

    int arr[100][3][10]={0};
    int value=1;
    int (*p)[3][10] = arr;

    for(int i=0; i<100; i++){
        for(int j=0; j<3; j++){
            for(int k=0; k<10; k++){
                arr[i][j][k]=value++;
                
    printf("%d\t", p[i][j][k]);
    }
    printf("\n");
}
   printf("\n");
}
    
    return 0;
}
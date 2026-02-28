#include<stdio.h>

void function(int arr[][2],int row, int colms){
    for(int i=0; i<row; i++){
        for(int j=0; j<colms; j++){
            printf("%d ",arr[i][j]);
        }
        printf("\n");
    }
}
int main(){
    
    int array[3][2]={{1,2},{3,4},{5,6}};
    function(array,3,2);
    return 0;
}
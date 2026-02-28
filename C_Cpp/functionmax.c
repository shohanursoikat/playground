#include<stdio.h>
int function(int arr[], int size){
    int max = arr[0];
    for(int i = 1; i<size; i++){
        
        if(arr[i]>max){
            max = arr[i];
        }
       
    }
     return max;
}
int main(){
    int array[] = {1,2,3,4,51};
    int size = sizeof(array)/sizeof(array[0]);
   
    printf("%d", function(array,size));
    return 0;

}
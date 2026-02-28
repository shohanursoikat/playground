#include<stdio.h>
int main(){
    int arr[5]={111,222,122,32,22};
   
    for(int j=0; j<5; j++){
        int min_index=j;
        for(int k=j+1;k<5; k++){
            if(arr[k]<arr[min_index]){
                min_index=k;
            }
        }
        int temp=arr[j];
        arr[j]=arr[min_index];
        arr[min_index]=temp;
    }
    for (int i = 0; i < 5; i++)
    {
        printf("%d ", arr[i]);
    }
}
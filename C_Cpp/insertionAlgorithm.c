#include<stdio.h>
int main(){
    int la[6] = { 10,20,30,50,60};
    int n=5;
    int item = 40;
    int k = 3;
    for(int i = n; i>k; i--){
        la[i] = la[i-1];
    }
    
    la[k] = item;
    n++;
    
    for(int i=0; i<n; i++){
        printf("%d ",la[i]);
    }

    return 0;
}
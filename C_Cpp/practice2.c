#include<stdio.h>
#include<stdlib.h>
int main(){

    int n; 
    int* p;
   
    printf("Enter numbers: ");
    scanf("%d",&n);

   
    p = (int*) malloc(n* sizeof(int));

    
    if(p==NULL){
        printf("Memory allacation failed.\n");
        return 0;
    }

    printf("Enter %d elements: ",n);
      for(int i=0; i<n; i++){
    scanf("%d", &p[i]);
    }
    

     for(int i=0; i<n; i++){
    printf("Value: %d\n",p[i]);
     }
    free(p);

    return 0;
}
#include<stdio.h>
int main(){
    int mark;
    printf("Enter mark: ");
    scanf("%d", &mark);
    if(mark >=90 && mark <=90){
        printf("A+");
    }else if(mark>=80 && mark < 90){
        printf("A");
    }else if(mark>=70 && mark<80){
        printf("B");
    }else if(mark>=60 && mark<70){
        printf("C");
    }else if(mark>=60 && mark < 50){
        printf("D");
    }else{
        printf("Better luck.");
    }
}
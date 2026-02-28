#include <stdio.h>
#define N 100
int top = -1;
int stack[N];

void push(int item){
    if(top==N-1){
        printf("Stack Overflow\n");
    }else{
        top++;
        stack[top]=item;

    }
}
void pop(){
    if(top==-1){
        printf("Stack Underflow\n");
    }else{
        top--;
    }
}

void display(){
    for(int i = 0; i <= top; i++){
        printf("Elements: %d\n", stack[i]);
    }
}

int main() {
    push(5);
    push(9);
    push(11);
    push(44);
    pop();
    display();
    
    return 0;
}
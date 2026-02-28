#include <stdio.h>
#define size 100

int queue[size];
int front = -1;
int rare = -1;
int isfull(){
    if(rare == size -1){
       return 1;
    }else{
        return 0;
    }
}
int isempty(){
    if(front == -1){
        return 1;
    }else{
        return 0;
    }
}
void enqueue(int value){
    if(isfull()){
        printf("Queue is full.\n");
    }else{
        if(front == -1){
            front = 0;
        }
        rare ++;
        queue[rare] = value;
    }
}
void dequeue(){
    if(isempty()){
        printf("Queue is empty.\n");
    }else{
        front++;
    }
}
void display(){
    if(isempty()){
        printf("Queue is empty.\n");
    }else{
        for(int i = front; i<= rare; i++){
            printf("Elements: %d\n", queue[i]);
        }
    }
}



int main() {

    enqueue(10);
    enqueue(12);
    enqueue(33);
    enqueue(99);
    dequeue();
    display();
    
    return 0;
}
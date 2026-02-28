#include <stdio.h>
#define SIZE 5   // maximum size of the queue

// Global variables for queue
int queue[SIZE];
int front = -1, rear = -1;

// Function to check if queue is empty
int isEmpty() {
    return (front == -1 || front > rear);
}

// Function to check if queue is full
int isFull() {
    return (rear == SIZE - 1);
}

// Function to add an element to the queue (enqueue)
void enqueue(int value) {
    if (isFull()) {
        printf("Queue Overflow! Cannot insert %d\n", value);
    } else {
        if (front == -1) front = 0;  // first insertion
        rear++;
        queue[rear] = value;
        printf("%d enqueued\n", value);
    }
}

// Function to remove an element from the queue (dequeue)
void dequeue() {
    if (isEmpty()) {
        printf("Queue Underflow! Nothing to dequeue\n");
    } else {
        printf("%d dequeued\n", queue[front]);
        front++;
    }
}

// Function to display the queue
void display() {
    if (isEmpty()) {
        printf("Queue is empty\n");
    } else {
        printf("Queue elements: ");
        for (int i = front; i <= rear; i++) {
            printf("%d ", queue[i]);
        }
        printf("\n");
    }
}

// Main function to test queue
int main() {
    enqueue(10);
    enqueue(20);
    enqueue(30);
    display();
    
    dequeue();
    display();

    enqueue(40);
    enqueue(50);
    enqueue(60); // will overflow
    display();

    return 0;
}

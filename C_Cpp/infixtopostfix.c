#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

#define MAX 100

char stack[MAX];
int top = -1;

// Stack functions
void push(char c) { stack[++top] = c; }
char pop() { return stack[top--]; }
char peek() { return stack[top]; }
int isEmpty() { return top == -1; }

// Operator precedence
int precedence(char op) {
    if(op == '+' || op == '-') return 1;
    if(op == '*' || op == '/') return 2;
    return 0;
}

// Infix → Postfix conversion
void infixToPostfix(char* infix, char* postfix) {
    int k = 0;
    for(int i = 0; i < strlen(infix); i++) {
        char c = infix[i];

        if(isalnum(c)) { // operand
            postfix[k++] = c;
        }
        else if(c == '(') {
            push(c);
        }
        else if(c == ')') {
            while(!isEmpty() && peek() != '(')
                postfix[k++] = pop();
            pop(); // remove '('
        }
        else { // operator
            while(!isEmpty() && precedence(peek()) >= precedence(c))
                postfix[k++] = pop();
            push(c);
        }
    }
    while(!isEmpty())
        postfix[k++] = pop();

    postfix[k] = '\0';
}

// Evaluate postfix
int evaluatePostfix(char* postfix) {
    int stackVal[MAX];
    int topVal = -1;

    for(int i = 0; i < strlen(postfix); i++) {
        char c = postfix[i];

        if(isdigit(c)) {
            stackVal[++topVal] = c - '0'; // convert char to int
        } else {
            int val2 = stackVal[topVal--];
            int val1 = stackVal[topVal--];

            switch(c) {
                case '+': stackVal[++topVal] = val1 + val2; break;
                case '-': stackVal[++topVal] = val1 - val2; break;
                case '*': stackVal[++topVal] = val1 * val2; break;
                case '/': stackVal[++topVal] = val1 / val2; break;
            }
        }
    }
    return stackVal[topVal];
}

int main() {
    char infix[MAX], postfix[MAX];
    printf("Enter an infix expression (single-digit operands): ");
    scanf("%s", infix);

    infixToPostfix(infix, postfix);
    printf("Postfix expression: %s\n", postfix);

    int result = evaluatePostfix(postfix);
    printf("Evaluated result: %d\n", result);

    return 0;
}


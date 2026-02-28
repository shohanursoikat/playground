#include <stdio.h>

struct node{
    int data;
    int value;


};
int main() {
    struct node n;
    n.data=5; n.value = 33;
    printf("%d\n%d\n",n.data, n.value);
    
    return 0;
}
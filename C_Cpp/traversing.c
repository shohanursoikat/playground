#include <stdio.h>

int main() {
    int LA[] = {5, 10, 15, 20, 25};
    int n = sizeof(LA) / sizeof(LA[0]); // Size of array
    int K;

    // Traversing using a for loop
    for (K = 0; K < n; K++) {
        printf("Element at index %d is %d\n", K, LA[K]);
    }

    return 0;
}

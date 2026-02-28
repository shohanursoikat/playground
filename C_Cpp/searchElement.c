#include <stdio.h>

int main() {
    int DATA[100] = {10, 20, 30, 40, 50}; // hardcoded array
    int N = 5;                            // number of actual elements
    int ITEM = 30;                        // item to search
    int LOC = 0;

    // Place sentinel at the end
    DATA[N] = ITEM;

    // Search loop
    while (DATA[LOC] != ITEM) {
        LOC++;
    }

    // Check if it was found in original data
    if (LOC == N) {
        printf("Item %d not found in the array.\n", ITEM);
        LOC = 0; // mark not found
    } else {
        printf("Item %d found at position %d .\n", ITEM, LOC + 1);
    }

    return 0;
}

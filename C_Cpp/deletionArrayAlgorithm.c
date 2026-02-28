#include <stdio.h>
int main() {
    int la[10] = {10, 20, 30, 40, 50, 60};
    int n = 6;
    int k = 3; 

    for (int j = k; j < n; j++) {
        la[j] = la[j+1]; 
    }

    n--; 

    for (int i = 0; i < n; i++) {
        printf("%d ", la[i]);
    }

    return 0;
}

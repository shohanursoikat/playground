#include <stdio.h>

int main() {
    int arr[] = {2, 5, 8, 12, 16, 23, 38, 45, 56, 72};
    int size = sizeof(arr) / sizeof(arr[0]);
    int key;

    printf("Enter the number to search: ");
    scanf("%d", &key);

    int low = 0, high = size - 1;
    int found = -1;  

    while (low <= high) {
        int mid = low + (high - low) / 2;

        if (arr[mid] == key) {
            found = mid;
            break;
        } 
        else if (arr[mid] < key) {
            low = mid + 1;   
        } 
        else {
            high = mid - 1;  
        }
    }

    if (found != -1) {
        printf("Element %d found at index %d.\n", key, found);
    } else {
        printf("Element %d not found in the array.\n", key);
    }

    return 0;
}

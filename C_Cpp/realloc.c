#include <stdio.h>
#include <stdlib.h>

int main() {
    char *str = NULL;
    size_t size = 0;
    
    
    printf("Enter string: ");
    getline(&str, &size, stdin);  // dynamically allocates & resizes

    printf("You entered: %s", str);
    free(str);
    return 0;
}

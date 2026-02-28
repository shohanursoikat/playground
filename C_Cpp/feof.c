#include <stdio.h>
#include <stdlib.h>

int main() {
    char *str = NULL;
    size_t size = 0;
    size_t len;

    printf("Enter a line: ");
    len = getline(&str, &size, stdin);

    if (len == -1) {
        if (feof(stdin)) {
            printf("End of input (EOF) reached.\n");
        } else if (ferror(stdin)) {
            perror("Input error");
        }
        free(str);
        return 1;
    }

    printf("You entered: %s", str);
    free(str);
    return 0;
}

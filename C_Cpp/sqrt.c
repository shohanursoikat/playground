#include <stdio.h>
#include<stdlib.h>

int main() {
    char *str = malloc(100*(sizeof(char)));
    printf("Enter string: ");
    fgets(str,100,stdin);
    printf("%s",str);
    return 0;
}
#include <stdio.h>

int main() {
    FILE * fp = fopen("fprintf.c", "a+");
    if(!fp) return 1;
    fprintf(fp,"write successful");
    fclose(fp);
    return 0;
}
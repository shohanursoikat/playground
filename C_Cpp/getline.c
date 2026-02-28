#include <stdio.h>
#include <stdlib.h>

#ifndef _WIN32
  #include <sys/types.h>  // for ssize_t on POSIX
#else
  typedef long ssize_t;   // simple fallback typedef for Windows C
#endif

ssize_t my_getline(char **lineptr, size_t *cap, FILE *stream) {
    if (!lineptr || !cap || !stream) return -1;

    if (*lineptr == NULL || *cap == 0) {
        *cap = 128;
        *lineptr = (char*)malloc(*cap);
        if (!*lineptr) return -1;
    }

    size_t len = 0;
    for (;;) {
        int ch = fgetc(stream);
        if (ch == EOF) {
            if (len == 0) return -1;
            break;
        }

        if (len + 1 >= *cap) {
            size_t new_cap = (*cap < 1024) ? (*cap * 2) : (*cap + *cap/2); // grow
            char *tmp = (char*)realloc(*lineptr, new_cap);
            if (!tmp) return -1;
            *lineptr = tmp;
            *cap = new_cap;
        }

        (*lineptr)[len++] = (char)ch;
        if (ch == '\n') break;
    }

    (*lineptr)[len] = '\0';
    return (ssize_t)len;
}

int main(void) {
    char *buf = NULL;
    size_t cap = 0;

    printf("Type a line: ");
    ssize_t n = my_getline(&buf, &cap, stdin);
    if (n == -1) { puts("\nEOF or error"); free(buf); return 0; }

    printf("Read %zd chars (capacity %zu): %s", n, cap, buf);

    // Reuse the same buffer; it will grow if needed but won’t shrink.
    printf("Type another line: ");
    n = my_getline(&buf, &cap, stdin);
    if (n != -1) printf("Read %zd chars (capacity %zu): %s", n, cap, buf);

    free(buf);
    return 0;
}

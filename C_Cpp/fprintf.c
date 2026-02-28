#include <stdio.h>
#include<stdlib.h>
#include<string.h>

struct student {
    char name[100];
    int id;

};
int main() {
    struct student s;
    strcpy(s.name,"shoha nur");
    s.id = 11;
    printf("%s\n %d",s.name, s.id);

    struct student* ptr = &s;
    
    printf("%s\n %d",ptr->name, ptr->id);
    return 0;
}
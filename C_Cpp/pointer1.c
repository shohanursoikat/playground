#include <stdio.h>

struct student{
    char name[50];
    int age;
    int id;
};
int main() {
    
    struct student *s;

    s->age = 22;
    printf("%d",s->age); 


    return 0;
}
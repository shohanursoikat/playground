#include <stdio.h>

struct Student {
    int id;
    char name[20];
};

int main() {
    struct Student s1 = {101, "Shohanur"};
    struct Student *ptr = &s1; 

    
    printf("ID: %d, Name: %s\n", s1.id, s1.name);

    
    printf("ID: %d, Name: %s\n", ptr->id, ptr->name);

    return 0;
}

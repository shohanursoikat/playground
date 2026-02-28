#include <stdio.h>
#include "student.h"

int main() {
    int choice;
    struct Student s;

    do {
        printf("\n1. Add Student\n");
        printf("2. Show Students\n");
        printf("3. Exit\n");
        printf("Enter choice: ");
        scanf("%d", &choice);

        if (choice == 1) {
            printf("Enter name: ");
            scanf("%s", s.name);
            printf("Enter marks: ");
            scanf("%d", &s.marks);
            saveStudent(s);
        } 
        else if (choice == 2) {
            showStudents();
        }

    } while (choice != 3);

    return 0;
}

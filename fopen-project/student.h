#ifndef STUDENT_H
#define STUDENT_H

struct Student {
    char name[50];
    int marks;
};

// Function declarations
void saveStudent(struct Student s);
void showStudents();

#endif

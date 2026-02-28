#include <stdio.h>
#include "student.h"

void saveStudent(struct Student s) {
    FILE *fp = fopen("data.txt", "a");  // open file in append mode
    if (fp == NULL) {
        printf("Error opening file!\n");
        return;
    }
    fprintf(fp, "%s %d\n", s.name, s.marks);
    fclose(fp);
    printf("Student saved successfully!\n");
}

void showStudents() {
    FILE *fp = fopen("data.txt", "r");  // open file in read mode
    if (fp == NULL) {
        printf("No data found!\n");
        return;
    }

    struct Student s;
    printf("\n--- Student List ---\n");
    while (fscanf(fp, "%s %d", s.name, &s.marks) == 2) {
        printf("Name: %s | Marks: %d\n", s.name, s.marks);
    }
    fclose(fp);
}

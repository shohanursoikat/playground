#include <stdio.h>
#include <string.h>

typedef struct {
    char name[50];
    int age;
    double cgpa;
} Student;

int main() {
    int n;
    printf("Enter number of students: ");
    scanf("%d", &n);

    Student students[n];  // array of structs

    // Input student details
    for (int i = 0; i < n; i++) { 
        printf("\nEnter details for student %d\n", i + 1);

        printf("Name: ");
        getchar(); // clears the newline left by scanf
        fgets(students[i].name, sizeof(students[i].name), stdin);

        // remove newline from fgets
        students[i].name[strcspn(students[i].name, "\n")] = '\0';

        printf("Age: ");
        scanf("%d", &students[i].age);

        printf("CGPA: ");
        scanf("%lf", &students[i].cgpa);
    }

    // Display all student details
    printf("\n==== Student Database ====\n");
    for (int i = 0; i < n; i++) {
        printf("\nStudent %d:\n", i + 1);
        printf("Name: %s\n", students[i].name);
        printf("Age: %d\n", students[i].age);
        printf("CGPA: %.2f\n", students[i].cgpa);
    }

    return 0;
}

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct student {
    int id;
    char name[50];
    float grade;
    struct student* next;
};


struct student* Head = NULL;


struct student* createStudent(int id, char name[], float grade) {
    struct student* newStudent = (struct student*)malloc(sizeof(struct student)); 
    if (!newStudent) {  
        printf("Memory allocation failed!\n");
        exit(1);
    }
    newStudent->id = id;
    strcpy(newStudent->name, name);
    newStudent->grade = grade;
    newStudent->next = NULL;
    return newStudent;
}


void insertStudent(int id, char name[], float grade) {
    struct student* newStudent = createStudent(id, name, grade);

    if (Head == NULL) {
        Head = newStudent;
        return;
    }

    struct student* temp = Head;
    while (temp->next != NULL) {
        temp = temp->next;
    }
    temp->next = newStudent;
}


void displayStudents() {
    if (Head == NULL) {
        printf("No student records found.\n");
        return;
    }

    struct student* temp = Head;
    printf("\n Student Records \n");
    while (temp != NULL) {
        printf("ID: %d | Name: %s | Grade: %.2f\n", temp->id, temp->name, temp->grade);
        temp = temp->next;
    }
    printf("\n");
}


void searchStudent(int id) {
    struct student* temp = Head;
    while (temp != NULL) {
        if (temp->id == id) {
            printf("Student Found \n ID: %d | Name: %s | Grade: %.2f\n", temp->id, temp->name, temp->grade);
            return;
        }
        temp = temp->next;
    }
    printf("Student not found.\n");
}

void deleteStudent(int id) {
    struct student* temp = Head;
    struct student* prev = NULL;

    
    if (Head == NULL) {
        printf("No records to delete.\n");
        return;
    }

    
    if (temp != NULL && temp->id == id) {
        Head = temp->next;
        free(temp);
        printf("Student deleted.\n");
        return;
    }

   
    while (temp != NULL && temp->id != id) {
        prev = temp;
        temp = temp->next;
    }

    if (temp == NULL) {
        printf("Student not found.\n");
        return;
    }

    prev->next = temp->next;
    free(temp);
    printf("Student deleted.\n");
}

int main() {
    int choice, id;
    char name[50];
    float grade;

    while (choice!=5) {
        printf("\n Student Record Management \n");
        printf("1. Insert Student\n"); 
        printf("2. Display Students\n");
        printf("3. Search Student\n");
        printf("4. Delete Student\n");
        printf("5. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);
        getchar(); 

        switch (choice) {
            case 1:
                printf("Enter ID: ");
                scanf("%d", &id);
                getchar();
                printf("Enter Name: ");
                fgets(name, 50, stdin);
                name[strcspn(name, "\n")] = 0; 
                printf("Enter Grade: ");
                scanf("%f", &grade);
                insertStudent(id, name, grade);
                displayStudents();
                break;

            case 2:
                displayStudents();
                break;

            case 3:
                printf("Enter ID to search: ");
                scanf("%d", &id);
                searchStudent(id);                
                break;

            case 4:
                printf("Enter ID to delete: ");
                scanf("%d", &id);
                deleteStudent(id);
                displayStudents();
                break;

            case 5:
                printf("Exiting...\n");
                break;                

            default:
                printf("Invalid choice!\n");
        }
    }
    return 0;
}

#include <stdio.h>
#include<stdlib.h>
#include<string.h>

struct student{
    int id; char name[50];
    float grade;
    struct student* next;
};





int main() {
    int identitly=2414; char name[50]="shohanur rahman";
    float cgpa=3.22;
    struct student* s1 = malloc(sizeof(struct student));

strcpy(s1->name,name);
s1-> id = identitly;
    s1->grade=cgpa;
    printf("name: %s\n id: %d\n grade: %.2f\n",s1->name, s1->id, s1->grade);

    int id2=24144;
    char name2[50] = "rafsan bc";
    float cg = 3.33;
    struct student *s2 = malloc(sizeof(struct student));
    s2->id = id2;
    strcpy(s2->name, name2);
    s2->grade = cg;
        printf("name: %s\n id: %d\n grade: %.2f\n",s1->next, s1->next, s1->next);
    s1->next = s2;
    s2->next = NULL;

    return 0;
}
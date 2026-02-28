#include <stdio.h>
#include<string.h>
typedef struct {
    char name[50];
    int age;
    double cgpa;
}Student;
int main() {
     Student s[3];
    
   strcpy( s[0].name, "shohanur Rahman soikat");
    s[0].age = 22;
    s[0].cgpa = 2.78;
    
   strcpy( s[1].name, "Sukaanto Deb");
    s[1].age = 21;
    s[1]. cgpa = 3.13;

    strcpy(s[2].name, "Tanjim Rubayet");
    s[2].age = 23;
    s[2].cgpa = 3.67;

    printf("\tStudent DB\n");

    for(int i= 0; i<3; i++){
        
        printf("Student %d\n",i+1);
        printf(" Name: %s\n Age: %d\n CGPA: %.2f\n\n",s[i].name, s[i].age, s[i].cgpa);
    }

    return 0;
}

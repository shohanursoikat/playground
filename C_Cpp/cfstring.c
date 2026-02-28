#include<stdio.h>
#include<string.h>
int main(){
    int n;
    printf("Enter string length: ");
    scanf("%d",&n);
    getchar();

    char str[n];
    printf("Enter string: ");
    fgets(str, sizeof(str), stdin);

    int length = strlen(str);
    if(str[length - 1] == '\n'){
        str[length -1] = '\0';
        length--;
    }
    int count;
    count = length-2;
    

    return 0;
}
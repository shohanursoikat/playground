#include <iostream>
#include <string>
using namespace std;

int main() {
    char str[101];
    cout<<"Enter string: ";
    fgets(str, sizeof(str), stdin);
   
    cout <<str<< endl;
    return 0;
}

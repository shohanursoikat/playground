#include<iostream>
using namespace std;

int main(){  
int mark;
cout << "Enter mark: ";
cin>> mark; 
if(mark>=90 && mark<=100){
    cout<<"A+";
}else if( mark >=80 && mark<70){
    cout<<"B";
}else if(mark>=60 && mark<=70){
    cout<<"C";
}else if(mark>=50 && mark<60){
    cout<<"D";
}else{
    cout<<"Better luck.";
}


 }
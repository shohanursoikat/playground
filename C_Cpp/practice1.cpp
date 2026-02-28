#include<iostream>
using namespace std;
int main(){

    int rows[3];
    int clms[3];
    cout<<"Enter rows: ";
    for(int i=0; i<3; i++){
    cin>>rows[i];
    }

    cout<<"Enter coloum: ";
    for(int j=0; j<3; j++){
        cin>>clms[j];
    }
    int sumrows=0;
    for(int i=0; i<3; i++){
        sumrows+=rows[i];
    }

    int sumclms=0;
    for(int j=0; j<3; j++){
        sumclms+=clms[j];
    }
    printf("%d\n",sumrows);
    printf("%d\n",sumclms);

    return 0;
}
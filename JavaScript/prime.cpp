#include<iostream>
using namespace std;
int main(){
    int n = 123;
    bool prime = true;
    if(n<2){
        prime = false;
    }else{

    
    for(int i =2; i*i<=n; i++){
        if(n%i==0){
            prime = false;
        }
    }
}
    if(prime){
        cout<<"Prime.";
    }else{
        cout<<"Not prime.";
    }
}
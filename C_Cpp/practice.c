#include<stdio.h>
int main(){

  int rows[3]={22,33,432};
  int clms[3]={44,12,32};

  int sumrows=0;

  for (int i=0; i<3; i++){
    sumrows+=rows[i];

  }

  int sumclms=0;
  for(int j=0; j<3; j++){
    sumclms+=clms[j];
  }
  printf("%d\n",sumrows);
  printf("%d",sumclms);


  return 0;
}
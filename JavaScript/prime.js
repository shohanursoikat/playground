let n = 11;
let prime = true;
if(n<=2){
   prime = false;
}else{
    for(let i = 0; i*i<n; i++){
        if(n%2==0){
            prime = false;
        }
    }
}
if(prime){
    console.log("Prime.")
}else{
    console.log("Not prime.")
}
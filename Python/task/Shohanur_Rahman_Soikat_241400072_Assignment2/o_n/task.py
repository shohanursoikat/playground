task=1
while True:
    input_file=f"input{task}.txt"
    output_file=f"output{task}.txt"
    try:
        with open(input_file,"r")as file:
            N=int(file.readline())
            if N<1 or N>=105:
                break
            alice=list(map(int,file.readline().split()))
            M=int(file.readline())
            if M<1 or M>=105:
                break
            bob=list(map(int,file.readline().split()))
    except FileNotFoundError:
        break
    i=j=0
    merged=[]
    while i<N and j<M:
        if alice[i]<=bob[j]:
            merged.append(alice[i])
            i+=1
        else:
            merged.append(bob[j])
            j+=1
    with open(output_file,"w") as file:
        file.write(" ".join(map(str,merged)))
    task+=1
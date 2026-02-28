task=1
while True:
    input_file=f"input{task}.txt"
    output_file=f"output{task}.txt"
    try:
        with open(input_file,"r") as file:
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
    combined=alice+bob
    combined.sort()
    with open(output_file,"w") as file:
        file.write(" ".join(map(str,combined)))
    task+=1  


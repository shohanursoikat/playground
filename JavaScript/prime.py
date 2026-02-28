n = 123
prime = True
if n<2:
    prime = False
else:
    for i in range (2, int(n**0.5) ):
        if n%i == 0:
         prime = False

if prime:
    print("Prime.")
else:
    print("Not prime.")

    


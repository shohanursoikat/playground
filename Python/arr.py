size=int(input("Enter size: "))
arr=[]
for i in range(size):
    value=int(input("Enter values: "))
    arr.append(value)
print(*arr)
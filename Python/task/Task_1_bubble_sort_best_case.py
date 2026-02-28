n=int(input("Enter size: "))
array=list(map(int,input("Enter elements: ").split()))
for i in range(n-1):
    swapped=False
    for j in range(n-1-i):
        if array[j]>array[j+1]:
            array[j],array[j+1]=array[j+1],array[j]
            swapped=True
    if not swapped:
        break
for num in array:
    print(num,end=" ")

#A boolean variable  has assigned as False.
#If a sweaping occur to the inner loop, variable set as True and time complexity O(n^2).
#If no sweaping occur, the variable remains False and exit the loops instantly.
#So, the loops runs only once at best case, and time complexity O(n).
arr=[121,11,22,12,3,5]
n=len(arr)
for i in range (n-1):
    for j in range (n-1-i):
        if(arr[j]>arr[j+1]):
            arr[j],arr[j+1]=arr[j+1],arr[j]
print(arr)
arr=[111,22,11,23,21]
n=len(arr)
for i in range(n-1):
    for j in range(n-1-i):
        if arr[j]>arr[j+1]:
            arr[j],arr[j+1]=arr[j+1],arr[j]
print(arr)
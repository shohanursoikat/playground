arr=[111,23,32,44,5]
n=len(arr)

for i in range(1,n):
    key=i
    j=i-1
    while j>=0 and arr[j]>key:
        arr[j+1]=arr[j]
        j-=1
<<<<<<< HEAD
    array[j+1]=key
print(array)
  
=======
>>>>>>> 08e6d9a (updated)

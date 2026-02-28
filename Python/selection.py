array=[111,12,22,333,44,1]
length=len(array)
for i in range(length):
    min_index=i
    for j in range(i+1,length):
        if array[j]<array[min_index]:
           min_index=j
    array[i], array[min_index]=array[min_index],array[i]
print(array)


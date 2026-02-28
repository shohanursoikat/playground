n = int(input("Enter size: "))
arr = list(map(int, input("Enter elements: ").split()))

for i in range(n - 1):
    for j in range(n - 1 - i):
        if arr[j] > arr[j + 1]:
            arr[j], arr[j + 1] = arr[j + 1], arr[j]

for num in arr:
    print(num, end=" ")

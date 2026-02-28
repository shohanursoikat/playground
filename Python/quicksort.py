arr = [10, 7, 8, 9, 1, 5]
def quick_sort(arr):
    if len(arr) <= 1:  # Base case
        return arr

    pivot = arr[0]

    left = []
    right = []

    for x in arr[1:]:
        if x <= pivot:
            left.append(x)
        else:
            right.append(x)

    return quick_sort(left) + [pivot] + quick_sort(right)


print(quick_sort(arr))

with open("input_2.txt", "r") as file:
    n = int(file.readline().strip())
    ids = list(map(int, file.readline().split()))
    marks = list(map(int, file.readline().split()))

for i in range(n - 1):
    max_index = i

    for j in range(i + 1, n):
        if marks[j] > marks[max_index]:
            max_index = j

        elif marks[j] == marks[max_index] and ids[j] < ids[max_index]:
            max_index = j

    marks[i], marks[max_index] = marks[max_index], marks[i]
    ids[i], ids[max_index] = ids[max_index], ids[i]


for i in range(n):
    print("ID:", ids[i], "Mark:", marks[i])

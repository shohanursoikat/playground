with open("output.txt","a+") as file:

    file.write("write\n")
    file.seek(0)
    data = file.read()
    print(data)

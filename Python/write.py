with open("output.txt","w") as file:
    file.write("what's up bro...\n")
    file.write("yeah, good..\n")
    lines=["welcome to python\n","thanks bruh..\n","i am newbie\n","once i was too.\n"]
    file.writelines(lines)
    file.write("1 2 3")

fruits=["mango","banana","lichi"]
choice=input("Enter choice: ").lower()
if choice in fruits:
    print(f"{choice} available in fruits.")
else:
    print(f"{choice} is not availabe in fruits.")
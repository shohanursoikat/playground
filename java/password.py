with open("passwords.txt","r") as file:    
      passwords=[line.strip() for line in file]
      for i in range(0, len(passwords), 2):
        
         print('"'+passwords[i]+'",')
         if i+1<len(passwords):
          print('"'+passwords[i+1]+'",')
      
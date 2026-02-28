def check_password(password):
    score = 0

    # Check length
    if len(password) >= 8:
        score += 1

    # Check for lowercase letters
    has_lower = False
    for char in password:
        if char.islower():
            has_lower = True
            break
    if has_lower:
        score += 1

    # Check for uppercase letters
    has_upper = False
    for char in password:
        if char.isupper():
            has_upper = True
            break
    if has_upper:
        score += 1

    # Check for numbers
    has_number = False
    for char in password:
        if char.isdigit():
            has_number = True
            break
    if has_number:
        score += 1

    # Check for special characters
    special_characters = "!@#$%^&*()-_=+[]{};:,<.>/?"
    has_special = False
    for char in password:
        if char in special_characters:
            has_special = True
            break
    if has_special:
        score += 1

    # Rating based on score
    if score == 5:
        rating = "Strong "
    elif score >= 3:
        rating = "Medium "
    else:
        rating = "Weak "

    return rating


def main():
    print("Password Strength Checker")
    pwd = input("Enter your password: ")
    result = check_password(pwd)
    print("\nStrength:", result)


main()

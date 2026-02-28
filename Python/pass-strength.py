def pass_check(password):
    score = 0
    if len(password) >= 8:
        score += 1
    has_lower = False
    for char in password:
        if char.islower():
            has_lower = True
            break
    if has_lower:
        score += 1
    has_upper = True
    for char in password:
        if char.isupper():
            has_upper = True
    if has_upper:
        score += 1
    has_digit = False
    for char in password:
        if char.isdigit():
            has_digit = True
    if has_digit:
        score += 1
    special_char = "!@#$%^&*()_+?></.,"
    has_special = False
    for char in password:
        if char in special_char:
            has_special = True
    if has_special:
        score += 1
    if score == 5:
        rating = "Strong"
    elif score >= 3:
        rating = "Medium"
    else:
        rating = "Week"
    return rating


def main():
    pwd = input("Enter password: ")
    result = pass_check(pwd)
    print("Strength:", result)


main()

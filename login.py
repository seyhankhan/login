######## Seyhan Van Khan
######## Login System
######## A login system that securely stores sensible and strong usernames and passwords
######## Stops brute force attempts

from __future__ import print_function

from loginfunctions import *

################################### Constants ##################################


MIN_USERNAME_LENGTH = 5
MIN_PASSWORD_LENGTH = 8
# List of increasing time delays when user repeatedly gets login details wrong
times = [1, 2, 5, 15, 30, 45, 60] # minutes


######################## Deleting previous day attempts ########################


# Converts login attempts file into list
# Deletes all failed login attempt data from previous days from the list
att = listfile("loginattempts.txt")
while len(att) > 2 and att[1] != thedate():
    del att[0]
    del att[0]

# Writes the amended list back into the file (1 element - 1 line)
f = open("loginattempts.txt", 'w')
for i in range(len(att)):
    f.write(att[i])
    f.write("\n")
f.close()


################################ Login main page ###############################


# Opens file containing all current account details & converts it to a list
data = listfile('logindetails.txt')

while True:
    x = input("Would you like to register or login? ")
    if x.lower() == "register" or x.lower() == "r" or x.lower() == "login" or x.lower() == "l":
        # Ensures user types either register or login
        break


############################ Registering new account ###########################


if x.lower() == "register" or x.lower() == "r":
    username = input("New Username: ")
    while username in data or len(username) < MIN_USERNAME_LENGTH:
        # Checks if username already exists & is >= a suitable length
        if len(username) < MIN_USERNAME_LENGTH:
            print("Username must be at least", MIN_USERNAME_LENGTH, "characters.")
        else:
            print("Username taken")
        username = input("New Username: ")

    password = input("New Password: ")
    while (not hasnum(password)) or len(password) < MIN_PASSWORD_LENGTH or username in password:
        # Checks that pw has a number, doesn't contain username, and is long enough
        if username in password:
            print("Password must not contain the username")
        else:
            print("Password must contain at least", MIN_PASSWORD_LENGTH, "characters and a number")
        password = input("New Password: ")

    # Open current account details file & stores (registers) the new username & password (written in 1 way encryption)
    data = open('logindetails.txt', "a")
    data.write(username + "\n")
    data.write(hasher(password) + "\n")
    data.close()


################################## Logging in ##################################


else:
#if x.lower() == "login" or x.lower() == "l":
    username = input("Username: ")
    password = input("Password: ")

    checklogs(username)

    num_attempts = time_index = 0

    while not confirm(username, hasher(password), data):
        print("Username or password is incorrect. Please try again.")

        # Open txtfile containing all previous failed login attempts & writes down username & date attempt
        attempts = open('loginattempts.txt', "a")
        attempts.write(username + "\n")
        attempts.write(thedate() + "\n")
        attempts.close()
        checklogs(username)

        num_attempts += 1
        if num_attempts == 4:
            # If incorrect 4 times
            # Every incorrect login now incurs a time delay
            # Increasing time delays occur according to TIMES list
            num_attempts -= 1
            if time_index < len(times):
                # If no. attempts exceed len(TIMES), time delays increase by 1 hour
                delay_time = times[time_index]
                time_index += 1
            else:
                delay_time += 60

            print("Login access is denied for %s seconds." % (delay_time * 60))
            time.sleep(delay_time * 60)

        username = input("Username: ")
        password = input("Password: ")
        checklogs(username)

    # end = '' means the print function won't print a new line at the end
    print("\n\nAccess granted", end = '', flush = True)


################################ Welcoming page ################################


print("\nWelcome %s.\n" % username)

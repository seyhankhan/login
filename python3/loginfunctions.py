######## Seyhan Van Khan
######## Login System
######## A login system that securely stores sensible and strong usernames and passwords
######## Stops brute force attempts

import datetime
import time
import hashlib
import os

""" FUNCTIONS:
        listfile(name) - Converts a file into list, each line = 1 element
        confirm(user, password) - Confirms unique username
        hasnum(password) - Checks if password has a number
        thedate(date_format) - Return the current date
        checklogs(username) - Stops brute force attacks & blocks users attempting it for rest of day
        hasher(password) - Encrypts password using md5 based hash function (1 way function)

"""

def listfile(name):
    # Opens file in read mode and returns it as a list (each line = 1 element)
    file = open(name, "r")
    file = (file.read()).split("\n")
    file.pop()
    return file

def confirm(user, pw, data):
    # Checks if username is in the login account details file
    # Ensures unique usernames
    if user in data:
        if data[data.index(user) + 1] == pw:
            return True
    return False

def hasnum(pw):
    # Checks if the given string (password) has a number
    match = False
    pw = list(pw)
    for i in range(len(pw)):
        if pw[i].isdigit():
            match = True
            break
    return match

def thedate(string="%d-%m-%Y"):
    # Returns the current Day/Month/Year unless overridden for other dates (time, hour)
    return (datetime.datetime.now()).strftime(string)

def checklogs(username):
    # Stops brute force attacks of 1 username
    # Checks the number of times the username has failed to login today
    # If same username fails login >= 10 times
    # - Account is locked for rest of day
    attempts = listfile('loginattempts.txt')
    T = thedate()

    n = 0
    for i in range(0, len(attempts), 2):
        if attempts[i] == username and attempts[i + 1] == T:
            n += 1
    if n >= 10:
        # Exits python to stop brute force cracking 1 account
        # Prints hour difference between now and end of day
        # User is now unable to log back in with that username for that day
        time_d = 24 - int(thedate("%H"))
        print("""Too many login attempts have been made for this account.
        It is now blocked until the end of the day (%s hour%s left).""" % (time_d, ("" if time_d == 1 else "s")))
        exit()
    return 0

def hasher(pw):
    # Encrypts the password using md5 based hash function (1 way function)
    # Password is encoded in sequence of bytes and then hashed
    # Returns string with a double length (fixed length, only hexadecimal digits)
    hashed = hashlib.md5(pw.encode())
    return (hashed.hexdigest())

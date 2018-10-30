# Login
An anti brute force login system that securely stores sensible and strong usernames and passwords

### loginattempts.txt
Contains all failed user login attempts. Username + date. Refreshes every day. Stops brute force attempts by tracking failed logins.

### logindetails.txt
Contains all current account user details. Username + hashed password.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for usage.


### Running the program
* Execute `login.py` in Python 3.


### Constants
- `int MIN_USERNAME_LENGTH` - minimum possible length for acceptable username
- `int MIN_PASSWORD_LENGTH` - minimum possible length for acceptable password
- `int list times` - List of increasing time delays when user repeatedly gets login details wrong

## Author

* **Seyhan Van Khan**

/* Seyhan Van Khan
 * Login (java)
 * A login system that securely stores sensible and strong usernames and passwords
 * January 2019
 * Register functions
 */
/* public static void register()
 */

import java.util.*;

public class Register
{
  public static void register()
  {
    List<String> data = file.ListFile("data.txt");

    String username;
    while (true)
    {
      username = Main.input("New username: ");
      if (data.contains(username))
      {
        System.out.println("Username taken");
      }
      else if (username.length() < Consts.MIN_USERNAME_LENGTH)
      {
        System.out.println("Username must be at least " + Consts.MIN_USERNAME_LENGTH + " characters");
      }
      else
      {
        break;
      }
    }

    String password;
    while (true)
    {
      password = Main.input("New password: ");
      if (password.matches(username))
      {
        System.out.println("Password must not contain the username");
      }
      else if (password.length() < Consts.MIN_PASSWORD_LENGTH)
      {
        System.out.println("Password must be at least " + Consts.MIN_PASSWORD_LENGTH + " characters");
      }
      else if (!password.matches(".*\\d+.*"))
      {
        System.out.println("Password must have a number");
      }
      else
      {
        break;
      }
    }

    file.EditFile("data.txt", username + "\n" + Main.hash(password) + "\n", "append");
  }
}

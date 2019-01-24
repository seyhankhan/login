/* Seyhan Van Khan
 * Login System (java)
 * A login system that securely stores sensible and strong usernames and passwords
 * January 2019
 * Login functions
 */
/* public static void login()
 * public static boolean ConfirmDetails(String username, String password)
 */
import java.util.*;

public class Login
{
  public static void login()
  {
    int num_attempts, time_index, delay_time;
    num_attempts = time_index = delay_time = 0;
    String username, password;

    while (true)
    {
      username = Main.input("Username: ");
      password = Main.hash(Main.input("Password: "));

      file.Checklogs(username);

      if (ConfirmDetails(username, password))
      {
        break;
      }
      System.out.println("Username or password is incorrect. Please try again.");

      file.EditFile("loginattempts.txt",
                    username + "\n" + Main.thedate("dd-MM-yyyy") + "\n",
                    "append"
                    );
      file.Checklogs(username);

      num_attempts++;
      if (num_attempts == Consts.BEGIN_DELAY)
      {
        num_attempts--;
        if (time_index < Consts.TIMES.length)
        {
          delay_time = Consts.TIMES[time_index];
          time_index++;
        }
        else
        {
          delay_time += 60;
        }

        System.out.println("Login access is denied for "+ delay_time * 60 +" seconds.");
        try {
          Thread.sleep(delay_time * 60 * 1000);
        } catch (InterruptedException ex) {}

      }
    }
  }

  public static boolean ConfirmDetails(String username, String password)
  {
    List<String> data = file.ListFile("data.txt");

    if (data.contains(username)
        && data.contains(password)
        && data.indexOf(username) % 2 == 0
        && data.indexOf(password) == data.indexOf(username) + 1)
    {
      return true;
    }
    return false;
  }
}

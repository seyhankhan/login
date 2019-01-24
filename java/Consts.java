/* Seyhan Van Khan
 * Login (java)
 * A login system that securely stores sensible and strong usernames and passwords
 * January 2019
 * Constants
 */

public class Consts
{
  public static final int MIN_USERNAME_LENGTH = 5;
  public static final int MIN_PASSWORD_LENGTH = 8;
  public static final int BEGIN_DELAY = 4; // attempts
  public static final int MAX_ATTEMPTS_1USER = 10;
  public static final int[] TIMES = {1, 2, 5, 15, 30, 45, 60}; // minutes
}

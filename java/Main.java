/* Seyhan Van Khan
 * Login (java)
 * A login system that securely stores sensible and strong usernames and passwords
 * January 2019
 */

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main
{
  public static void main()
  {
    file.UpdateLoginAttempts();

    String choice;
    do
    {
      choice = svk.input("Register or Login? ").toLowerCase();
    } while (!choice.equals("register") && !choice.equals("login"));


    if (choice.equals("register"))
    {
      Register.register();
    }
    else if (choice.equals("login"))
    {
      Login.login();
    }
  }


  // SHA 256 Hash
  public static String hash(String text)
  {
    try
    {
      // Static getInstance method is called with hashing SHA
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      // digest() method called to calculate message digest of an input
      // Return array of byte
      byte[] messageDigest = md.digest(text.getBytes());
      // Convert byte array into signum representation
      BigInteger no = new BigInteger(1, messageDigest);
      // Convert message digest into hex value
      String hashtext = no.toString(16);

      while (hashtext.length() < 32)
      {
          hashtext = "0" + hashtext;
      }
      return hashtext;
    }
    // For specifying wrong message digest algorithms
    catch (NoSuchAlgorithmException e) {
      System.out.println("Exception thrown"
                         + " for incorrect algorithm: " + e);
      return null;
    }
  }


  // Returns the current datetime (exact format must be specified)
  public static String thedate(String format)
  {
    return new SimpleDateFormat(format).format(new Date());
  }


  public static String input(String prompt)
  {
    System.out.print(prompt);
    Scanner scan = new Scanner(System.in);
    return scan.nextLine();
  }
}

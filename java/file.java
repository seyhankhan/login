/* Seyhan Van Khan
 * Login (java)
 * A login system that securely stores sensible and strong usernames and passwords
 * January 2019
 * File functions
 */
/* public static void Checklogs(String username)
 * public static void UpdateLoginAttempts()
 * public static void EditFile(String filename, String text, String Write_or_Append)
 * public static List<String> ListFile(String filename)
 */

import java.io.*;
// IOException, FileWriter, BufferedWriter, FileReader, BufferedReader

import java.util.*;

public class file
{
	/* Stops brute force attacks of 1 username
	 * Checks the number of times the username failed to login today
	 * If same username fails login >= 10 times
	 * - Account is locked for the rest of the day
	 */
	public static void Checklogs(String username)
	{
		List<String> attempts = ListFile("loginattempts.txt");
		String T = Main.thedate("dd-MM-yyyy");

		int n = 0;
		for (int i = 0; i < attempts.size(); i += 2)
		{
			if (attempts.get(i).equals(username) && attempts.get(i + 1).equals(T))
			{
				n++;
			}
		}

		if (n >= Consts.MAX_ATTEMPTS_1USER)
		{
			int time_left = 24 - Integer.parseInt(Main.thedate("H"));
			System.out.println("\n"
					+ "Too many login attempts have been made for this account.\n"
					+ "It is now blocked until the end of the day.\n"
					+ "(" + time_left + " hour" + (time_left == 1 ? "" : "s") + " left)");

			System.exit(1);
		}
	}


	// Deletes old error logs in loginattempts.txt that aren't from today
	public static void UpdateLoginAttempts()
	{
		List<String> attempts = ListFile("loginattempts.txt");
		while (attempts.size() > 2 && attempts.get(1) != Main.thedate("dd-MM-yyyy"))
		{
			attempts.remove(0);
			attempts.remove(0);
		}

		EditFile("loginattempts.txt", "", "overwrite");
		for (String element : attempts)
		{
			EditFile("loginattempts.txt", element + "\n", "append");
		}
	}


	// Writes / Appends a string of text to a file
	// Defaults to append if (write or append) not specified
  public static void EditFile(String filename, String text, String Write_or_Append)
  {
		boolean overwrite = false;
		if (Write_or_Append.toLowerCase().equals("write")
				|| Write_or_Append.toLowerCase().equals("overwrite"))
		{
			overwrite = true;
		}
		else
		{
			overwrite = false;
		}

    FileWriter fw;
    try {
      BufferedWriter data = new BufferedWriter(new FileWriter(filename, !overwrite));
      data.write(text);
      data.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }


	// Opens file and returns it as an arraylist (each line 1 element)
  public static List<String> ListFile(String filename)
  {
    String line;

    try {
      BufferedReader datafile = new BufferedReader(new FileReader(filename));

      List<String> datalist = new ArrayList<String>();

      while ((line = datafile.readLine()) != null)
      {
          datalist.add(line);
      }
      return datalist;
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return null;
  }
}

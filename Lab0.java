/**
* @author Rebecca Lin
* @version 1.0
*/

import java.util.Scanner;
public class Lab0
{
  /**
  * This method returns whether or not the password meets the following
  * criteria: 8 characters long, contains an uppercase letter,
  * lowercase letter, number, and symbol.
  * @param pw The user's password
  * @return A boolean displaying whether or not the user's password pw is complex
  */
  public static boolean checkComplexity(String pw)
  {
    boolean complex = true;
    // Check the length of the password
    // If the string has less than 8 characters, pw is not complex enough
    if (pw.length() < 8)
    {
      complex = false;
    }

    // Call booleans for each category and set them all to false
    // As long as pw meets the complexity requirements, each boolean will switch to true
    boolean upper = false;
    boolean lower = false;
    boolean digit = false;
    boolean symbol = false;

    // Check for uppercase letters
    if (upperCase(pw) == true)
    {
      upper = true;
    }
    // Check for lowercase letters
    if (lowerCase(pw) == true)
    {
      lower = true;
    }
    // Check for digits
    if (digits(pw) == true)
    {
      digit = true;
    }
    // Check for symbols
    if (symbols(pw) == true)
    {
      symbol = true;
    }

    // If any of the booleans remains false, pw is not complex enough
    if (upper == false || lower == false || digit == false || symbol == false)
    {
      complex = false;
    }
    return complex;
  }

  /**
	 * Helper method that checks if a password contains at least one uppercase letter
	 * @param pw The given password
	 * @return A boolean representing whether or not the password has an uppercase letter
	 */
  public static boolean upperCase(String pw)
  {
    for (int i = 0; i < pw.length(); i++)
		{
			char c = pw.charAt(i);
      int ascii = (int) c;
			if (ascii > 64 && ascii < 91)
      {
        return true;
      }
		}
		return false;
  }

  /**
   * Helper method that checks if a password contains at least one lowercase letter
   * @param pw The given password
   * @return A boolean representing whether or not the password has a lowercase letter
   */
  public static boolean lowerCase(String pw)
  {
    for (int i = 0; i < pw.length(); i++)
    {
      char c = pw.charAt(i);
      int ascii = (int) c;
      if (ascii > 96 && ascii < 123)
      {
        return true;
      }
    }
    return false;
  }

  /**
	 * Helper method that checks if a password contains at least one digit
	 * @param pw The given password
	 * @return A boolean representing whether or not the password has a digit
	 */
  public static boolean digits(String pw)
  {
    for (int i = 0; i < pw.length(); i++)
    {
      char c = pw.charAt(i);
      int ascii = (int) c;
      if (ascii > 47 && ascii < 58)
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Helper method that checks if a password contains at least one symbol
   * @param pw The given password
   * @return A boolean representing whether or not the password has a symbol
   */
  public static boolean symbols(String pw)
  {
    for (int i = 0; i < pw.length(); i++)
    {
      char c = pw.charAt(i);
      int ascii = (int) c;
      if((ascii > 32 && ascii < 48) || (ascii > 58 && ascii < 65) || (ascii > 90 && ascii < 97) || (ascii > 122 && ascii < 127))
      {
        return true;
      }
    }
    return false;
  }

  /**
  * Generates a random password that meets the complexity requirement
  * recommended: a password with at least 90 in strength.
  * @return A new random, complex password
  */
  public static String generatePW()
  {
    String pw = "";
    // Create a string of possible characters
    String pc = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890`~!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";

    // Need a length of at least 8 for complexity; cap the length at 15
    int l = (int) (Math.random() * 8 + 8);
    int c = 0;
    while (pw.length() < l)
    {
      // Get a random int from 0 - 93, inclusive
      c = (int) (Math.random() * 94);
      // Add a random character from the 126 charactes in pc to the new password
      pw += pc.charAt(c);
    }
    // Generate a new password if it doesn't meet the complexity requirements or have a strength of at least 90
    if (checkComplexity(pw) == false || getPWStrength(pw) < 90.0)
    {
      return generatePW();
    }
    else
    {
      return pw;
    }
  }

  /**
  * This function will do a rough calculation of password strength.
  * @param pw A password to be checked for strength
  * @return The strength of pw in bits/entropy
  */
  public static double getPWStrength(String pw)
  {
    // Keep a counter on consecutive character types
		int consecutive = 0;

		//Test for consecutive characters
		for (int i = 0; i < pw.length(); i++)
		{
			char c = pw.charAt(i);
      int asciic = (int) c;
			if (i > 0)
			{
				char previous = pw.charAt(i - 1);
        int asciip = (int) previous;

        // Check if the current character and the character before it are the same type
        // If both characters are uppercase, add to consecutive counter
        if ((asciic > 64 && asciic < 91) && (asciip > 64 && asciip < 91))
        {
          consecutive++;
        }
				// If both characters are lowercase, add to consecutive counter
				if ((asciic > 96 && asciic < 123) && (asciip > 96 && asciip < 123))
        {
          consecutive++;
        }
        // If both characters are digits, add to consecutive counter
				if ((asciic > 47 && asciic < 58) && (asciip > 47 && asciip < 58))
        {
          consecutive++;
        }

        // Create strings for convenience
        String charStr = c + "";
				String previousStr = previous + "";

        // If both characters are symbols, add to consecutive counter
				if (symbols(charStr) && symbols(previousStr))
        {
          consecutive++;
        }
			}
		}

    // Keep track of the types of characters that are in the password (character set) to determine the bits/entropy
		int typeOfChar = 0;
		if (lowerCase(pw))
			typeOfChar += 26;
		if (upperCase(pw))
			typeOfChar += 26;
		if (digits(pw))
			typeOfChar += 10;
		if (symbols(pw))
			typeOfChar += 33;

    // Formula for the bits of the character set
		double entropyChar = (Math.log10(typeOfChar))/(Math.log10(2));

    // Formula for the final entropy
		double entropy = (pw.length() * entropyChar) - consecutive;

		return entropy;
  }

  /**
	 * Uses the Vigenere cipher to encrypt a string
	 * @param key The encryption key
	 * @param password The given password
	 * @return The encrypted version of the password
	 */
	public static String encryptPW(String key, String password)
	{

		int max = 127;
		int min = 32;

		// Make an index for the key
		int indexK = 0;

		String encrypted = "";

		for (int i = 0; i < password.length(); i++)
		{
      // Reset index if it is longer than the key
			if (indexK > key.length() - 1)
      {
        indexK = 0;
      }

      // Convert characters to ascii
			char currentK = key.charAt(indexK);
      int asciiK = (int) currentK;

			char currentP = password.charAt(i);
			int asciiP = (int) currentP;

      // Formula for the encrypted ascii character
			int asciiEncrypt = currentK + currentP - min;

      // Loop around if asciiEncrypt is outside the range
			if (asciiEncrypt > max)
      {
				asciiEncrypt = asciiEncrypt - max + min;
      }

      // Convert the ascii back to a character
			char encrypt = (char) asciiEncrypt;

      // Add the character to the encrypt string
			encrypted += encrypt;

      // Increment the key index
			indexK++;
		}
		return encrypted;
	}
}

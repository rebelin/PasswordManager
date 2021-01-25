/**
* @author Rebecca Lin
* @version 1.0
*/

import java.util.Scanner;
import java.text.DecimalFormat;
public class Lab0Runner
{
  public static void main(String args[])
  {
    // Ask the user for a (complex) password
    Scanner kboard = new Scanner(System.in);
    System.out.print("Please type in your password: ");
    String pw = kboard.nextLine();
    while (Lab0.checkComplexity(pw) == false)
    {
      System.out.println("Make sure your password is 8 characters long, contains an uppercase letter and a lowercase letter, a number, and a symbol.");
      System.out.print("Type in your password: ");
      pw = kboard.nextLine();
    }

    //Spacer
    System.out.println();

    // Round entropy to the hundredth place
    DecimalFormat df = new DecimalFormat("##########.##");


    // Generate a random complex, strong password
    String randp = Lab0.generatePW();
    System.out.println("This is a random, complex and strong password: " + randp);
    System.out.println("The password above has a strength of " + df.format(Lab0.getPWStrength(randp)));

    //Spacer
    System.out.println();

    // Test for passwords strength
    System.out.println("The strength of 'pingry' is " + df.format(Lab0.getPWStrength("pingry")));
    System.out.println("The strength of 'GoBigBlue' is " + df.format(Lab0.getPWStrength("GoBigBlue")));
    System.out.println("The strength of 'goBIGBLUE!!1!' is " + df.format(Lab0.getPWStrength("goBIGBLUE!!1!")));
    System.out.println("The strength of 'B$e@k!n&b@d5<A>' is " + df.format(Lab0.getPWStrength("B$e@k!n&b@d5<A>")));

    //Spacer
    System.out.println();

    // Encrypt password using Vinegere code
    // Use a user inputed password and a key for testing
    String key = "!g4Sz~#5";
    System.out.print("Please type in your password: ");
    String password = kboard.nextLine();
    System.out.println("Encrypted version: " + Lab0.encryptPW(key, password));

    //Spacer
    System.out.println();

    // Use a user inputed key and a random password
    System.out.print("Enter an encryption key: ");
    String userKey = kboard.nextLine();
    String random = Lab0.generatePW();
    System.out.println("Random password: " + random);
    System.out.println("Encrypted version: " + Lab0.encryptPW(userKey, random));
  }
}

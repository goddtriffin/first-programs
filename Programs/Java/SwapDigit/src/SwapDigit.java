import java.util.Scanner;

/**
 * The SwapDigit class asks for 2 digit number, just to output the number with the digits exchanged.
 *
 * @author Todd Griffin, griff170@purdue.edu
 * @version 9/9/2016
 */
public class SwapDigit {

    /**
     * @param args Unused
     */
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter number: ");
        String num = s.nextLine();

        System.out.println("Swapped: " + num.charAt(1) + num.charAt(0));
    }
}
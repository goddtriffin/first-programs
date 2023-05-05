import java.util.Scanner;

/**
 * The CompoundInterest class asks for principal, interestRate, and year just to output the annually compounded interest.
 *
 * @author Todd Griffin, griff170@purdue.edu
 * @version 9/9/2016
 */
public class CompoundInterest {

    /**
     * @param args Unused
     */
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter the principal: ");
        double principle = s.nextDouble();

        System.out.println("Enter the interest rate: ");
        double interestRate = s.nextDouble() / 100;

        System.out.println("Enter the years: ");
        int year = s.nextInt();

        System.out.println("Interest: " + (int)(principle * Math.pow((1 + interestRate), year) - principle) );
    }
}
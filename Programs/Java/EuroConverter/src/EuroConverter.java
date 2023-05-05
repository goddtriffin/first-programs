import java.util.Scanner;

/**
 * The EuroConverter class asks for an amount in USD, and the number of $ in 100 Eur, just to output the conversion to Eur from the first stated amount in USD.
 *
 * @author Todd Griffin, griff170@purdue.edu
 * @version 9/9/2016
 */
public class EuroConverter {

    /**
     * @param args Unused
     */
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter amount in USD: ");
        double usd = s.nextDouble();

        System.out.println("Enter number of $ in 100 Eur: ");
        double numInEur = s.nextDouble();

        System.out.println("Number of euros = " + (usd / (numInEur / 100)));
    }
}
import java.util.Scanner;

/**
 * The Pythagoras class asks for an 'a', and a 'b', just to output the length of a hypotenuse.
 *
 * @author Todd Griffin, griff170@purdue.edu
 * @version 9/9/2016
 */
public class Pythagoras {

    /**
     * @param args Unused
     */
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter a: ");
        double a = s.nextDouble();

        System.out.println("Enter b: ");
        double b = s.nextDouble();

        System.out.println("Hypotenuse: " + (Math.sqrt( Math.pow(a,2) + Math.pow(b,2) )) );
    }
}
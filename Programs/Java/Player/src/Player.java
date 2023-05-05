import java.util.Scanner;

/**
 * The Player class when given a name, cartesian coordinates, and movement commands, can output the desired actions and the updated cartesian coordinates.
 *
 * @author Todd Griffin, griff170@purdue.edu
 * @version 9/9/2016
 */
public class Player {

    private String name; //contains the name of the player
    private double positionX; //Contains the horizontal distance from the origin to the position of the player
    private double positionY; //Contains the vertical distance from the origin to the position of the player

    /**
     * @param name Name of the player.
     */
    public Player (String name) {
        this(name, 0, 0);
    }

    /**
     * @param name Name of the player.
     * @param positionX X position of the player.
     * @param positionY Y position of the player.
     */
    public Player (String name , double positionX , double positionY) {
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     *
     */
    public String getName () {
        return this.name;
    }

    /**
     *
     */
    public double getPositionX () {
        return this.positionX;
    }

    /**
     *
     */
    public double getPositionY () {
        return this.positionY;
    }

    /**
     * @param name Name of the player.
     */
    public void setName (String name) {
        this.name = name;
    }

    /**
     * @param offsetX Value to offset the player's positionX.
     */
    public void moveX (double offsetX) {
        this.positionX += offsetX;
    }

    /**
     * @param offsetY Value to offset the player's positionY.
     */
    public void moveY (double offsetY) {
        this.positionY += offsetY;
    }

    /**
     * @param theta Angle, in degrees measured in a counter-clockwise manner, gives the direction of movement.
     * @param distance Total moved in the direction.
     */
    public void moveInDirection (double theta , double distance) {
        this.positionX += Math.cos(Math.toRadians(theta)) * distance;
        this.positionY += Math.sin(Math.toRadians(theta)) * distance;
    }

    /**
     * @param player Player instance in which to compare positions.
     */
    public boolean hasSamePositionAs (Player player) {
        return (distanceFrom(player) < 0.001);
    }

    /**
     * @param player Player instance in which to find distance from.
     */
    public double distanceFrom (Player player) {
        return Math.sqrt( Math.pow(this.getPositionX() - player.getPositionX(), 2) + Math.pow(this.getPositionY() - player.getPositionY(), 2) );
    }

    /**
     * @param args Unused
     */
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("What is the name of player 1: ");
        String p1name = s.nextLine();

        System.out.println("Enter the starting xPosition of " + p1name + ": ");
        double p1XPosition = s.nextDouble();

        System.out.println("Enter the starting yPosition of " + p1name + ": ");
        double p1YPosition = s.nextDouble();
        s.nextLine();

        System.out.println("What is the name of player 2: ");
        String p2name = s.nextLine();

        System.out.println("Enter the starting xPosition of " + p2name + ": ");
        double p2XPosition = s.nextDouble();

        System.out.println("Enter the starting yPosition of " + p2name + ": ");
        double p2YPosition = s.nextDouble();

        Player p1 = new Player( p1name , p1XPosition , p1YPosition );
        Player p2 = new Player( p2name , p2XPosition , p2YPosition );

        System.out.println("Enter " + p1.getName() + "'s horizontal move offset: ");
        p1.moveX(s.nextDouble());

        System.out.println("Enter " + p1.getName() + "'s vertical move offset: ");
        p1.moveY(s.nextDouble());

        System.out.println("Enter " + p1.getName() + "'s diagonal move angle degrees: ");
        double p1Theta = s.nextDouble();

        System.out.println("Enter " + p1.getName() + "'s diagonal move distance: ");
        p1.moveInDirection(p1Theta, s.nextDouble());

        System.out.println("Enter " + p2.getName() + "'s horizontal move offset: ");
        p2.moveX(s.nextDouble());

        System.out.println("Enter " + p2.getName() + "'s vertical move offset: ");
        p2.moveY(s.nextDouble());

        System.out.println("Enter " + p2.getName() + "'s diagonal move angle degrees: ");
        double p2Theta = s.nextDouble();

        System.out.println("Enter " + p2.getName() + "'s diagonal move distance: ");
        p2.moveInDirection(p2Theta, s.nextDouble());

        //System.out.printf("Rounded double: %.5f", myDouble);
        System.out.printf(p1.getName() + "'s position: (%.5f, %.5f)\n", p1.getPositionX(), p1.getPositionY());
        System.out.printf(p2.getName() + "'s position: (%.5f, %.5f)\n", p2.getPositionX(), p2.getPositionY());
        System.out.printf("Distance between players: %.5f\n", p1.distanceFrom(p2));
        System.out.println("Same position: " + p1.hasSamePositionAs(p2));
    }
}
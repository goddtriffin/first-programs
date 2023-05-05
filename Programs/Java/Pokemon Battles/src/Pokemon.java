import java.util.Scanner;

public class Pokemon {

    private String name;
    private int ID;
    private String type;
    private int healthPower;
    private double baseAttackPower;

    private static int NUM_POKEMONS = 0;

    public Pokemon (String name, String type, int healthPower, double baseAttackPower) {
        this.name = MyUtils.formatStr(name);
        this.ID = NUM_POKEMONS++;
        this.type = (type.toLowerCase().equals("fire") || type.toLowerCase().equals("water") || type.toLowerCase().equals("grass") || type.toLowerCase().equals("electric"))? MyUtils.formatStr(type) : "Fire";
        this.healthPower = (healthPower < 0)? 0 : healthPower;
        this.baseAttackPower = (baseAttackPower < 1)? 1 : baseAttackPower;
    }

    public String getName () {
        return this.name;
    }

    public int getId () {
        return this.ID;
    }

    public String getType () {
        return this.type;
    }

    public int getHealthPower () {
        return this.healthPower;
    }

    public double getBaseAttackPower () {
        return this.baseAttackPower;
    }

    public boolean setType (String type) {
        if (type.length() <= 2) {
            return false;
        }

        type = type.toUpperCase().charAt(0) + type.toLowerCase().substring(1);

        if (type.equals("Fire") || type.equals("Water") || type.equals("Grass") || type.equals("Electric")) {
            this.type = type;
            return true;
        }

        return false;
    }

    public boolean setHealthPower (int healthPower) {
        if (healthPower < 0) {
            return false;
        }

        this.healthPower = healthPower;
        return true;
    }

    public boolean setBaseAttackPower (double baseAttackPower) {
        if (baseAttackPower < 1) {
            return false;
        }

        this.baseAttackPower = baseAttackPower;
        return true;
    }

    public String toString () {
        String s = "";

        s += "Name: "+ this.getName() +"\n";
        s += "ID: "+ this.getId() +"\n";
        s += "Type: "+ this.getType() +"\n";
        s += "Health Power (HP): "+ this.getHealthPower() +"\n";
        s += "Attack Power: "+ this.getBaseAttackPower();

        return s;
    }

    public boolean isDead () {
        return (this.healthPower == 0);
    }

    public void boostHealthPower (int healthPower) {
        this.healthPower += healthPower;
    }

    public void reduceHealthPower (int healthPower) {
        this.healthPower -= healthPower;

        if (this.healthPower < 0) {
            this.healthPower = 0;
        }
    }

    public static int battle (Pokemon p1, Pokemon p2) {
        while (!p1.isDead() && !p2.isDead()) {
            int p1Punch = (int)(p1.getBaseAttackPower() * Pokemon.getAttackMultiplier(p1,p2));
            int p2Punch = (int)(p2.getBaseAttackPower() * Pokemon.getAttackMultiplier(p2,p1));

            System.out.println("Reducing by "+ p2Punch);
            p1.reduceHealthPower(p2Punch);
            System.out.println(p1.getHealthPower());

            System.out.println("Reducing by "+ p1Punch);
            p2.reduceHealthPower(p1Punch);
            System.out.println(p2.getHealthPower());
        }

        if (p1.getHealthPower() >= p2.getHealthPower()) {
            return 1;
        } else {
            return 2;
        }
    }

    public static double getAttackMultiplier (Pokemon attacker, Pokemon defender) {
        if (attacker.getType().equals("Grass")) { //p1=GRASS
            if (defender.getType().equals("Electric")) {
                return 1;
            } else if (defender.getType().equals("Water")) {
                return 2;
            }
        } else if (attacker.getType().equals("Electric")) { //p1=ELECTRIC
            if (defender.getType().equals("Water")) {
                return 2;
            } else if (defender.getType().equals("Fire")) {
                return 1;
            }
        } else if (attacker.getType().equals("Water")) { //p1=WATER
            if (defender.getType().equals("Electric")) {
                return 1;
            } else if (defender.getType().equals("Fire")) {
                return 2;
            }
        } else if (attacker.getType().equals("Fire")) { //p1=FIRE
            if (defender.getType().equals("Grass")) {
                return 2;
            } else if (defender.getType().equals("Electric")) {
                return 1;
            }
        }

        return 0.5;
    }

    public static int battleOracle (Pokemon p1, Pokemon p2) {

        Pokemon test1 = new Pokemon(p1.getName(),p1.getType(),p1.getHealthPower(),p1.getBaseAttackPower());
        Pokemon test2 = new Pokemon(p2.getName(), p2.getType(), p2.getHealthPower(),p2.getBaseAttackPower());

        while (!test1.isDead() && !test2.isDead()) {
            int test1Punch = (int)(test1.getBaseAttackPower() * Pokemon.getAttackMultiplier(test1,test2));
            int test2Punch = (int)(test2.getBaseAttackPower() * Pokemon.getAttackMultiplier(test2,test1));

            System.out.println("Reducing by "+ test2Punch);
            test1.reduceHealthPower(test2Punch);
            System.out.println(test1.getHealthPower());

            System.out.println("Reducing by "+ test1Punch);
            test2.reduceHealthPower(test1Punch);
            System.out.println(test2.getHealthPower());
        }

        if (test1.getHealthPower() >= test2.getHealthPower()) {
            return 1;
        } else {
            return 2;
        }
    }

    private static String choose (String[] options) {
        Scanner s = new Scanner(System.in);

        String choice;
        boolean allowed = false;

        do {
            choice = s.nextLine();

            if (choice != null && choice.length() > 0) { //if choice exists and contains characters

                choice = choice.trim(); //get rid of leading/trailing spaces

                if (options.length > 0) { //if there are pre-determined choices for input
                    for (int i=0; i<options.length; i++) { //check to see if choice is allowed in predetermined input
                        if (choice.trim().toLowerCase().equals(options[i])) {
                            allowed = true;
                        }
                    }
                } else {
                    allowed = true;
                }

                //checks if it entirely consists of spaces
                int numSpaces = 0;
                for (int i=0; i<choice.length(); i++) {
                    if (choice.charAt(i) == ' ') {
                        numSpaces++;
                    }
                }
                if (numSpaces == choice.length()) {
                    allowed = false;
                }

                //if choice failed any of the requirements
                if (!allowed) {
                    System.out.println("Invalid input entered. Please re-enter.");

                    if (options.length > 0) { //if there are predetermined options for input
                        String str = "Your options include: [";
                        for (int i=0; i<options.length-1; i++) {
                            str += MyUtils.formatStr(options[i]) +", ";
                        }
                        str += MyUtils.formatStr(options[options.length-1]) +"]";
                        System.out.println(str);
                    }
                }
            }

        } while (!allowed);

        return MyUtils.formatStr(choice.trim());
    }

    private static double choose (double min) {
        Scanner s = new Scanner(System.in);

        String choice;
        boolean allowed = false;

        do {
            choice = s.nextLine();

            if (!MyUtils.isNumeric(choice)) { //if choice is a number
                System.out.println("Invalid input entered. Please enter numeric input.");
            } else {
                if (Double.parseDouble(choice) < min) { //if choice meets required min value
                    System.out.println("Invalid input entered. Please enter a value larger than "+ min +".");
                } else {
                    allowed = true;
                }
            }

        } while (!allowed);

        return Double.parseDouble(choice);
    }

    public static void main (String[] args) {

        //first Pokemon
        System.out.println("Enter the first Pokemon's Name: ");
        String name1 = choose(new String[]{});

        System.out.println("Enter the first Pokemon's Type: ");
        String type1 = choose(new String[]{"grass","electric", "water", "fire"});

        System.out.println("Enter the first Pokemon's Health Power (HP): ");
        int healthPower1 = (int)choose(0);

        System.out.println("Enter the first Pokemon's Base Attack Power: ");
        double baseAttackPower1 = choose(0);

        Pokemon p1 = new Pokemon(name1, type1, healthPower1, baseAttackPower1);

        //second Pokemon
        System.out.println("Enter the second Pokemon's Name: ");
        String name2 = choose(new String[]{});

        System.out.println("Enter the second Pokemon's Type: ");
        String type2 = choose(new String[]{"grass","electric", "water", "fire"});

        System.out.println("Enter the second Pokemon's Health Power (HP): ");
        int healthPower2 = (int)choose(0);

        System.out.println("Enter the second Pokemon's Base Attack Power: ");
        double baseAttackPower2 = choose(0);

        Pokemon p2 = new Pokemon(name2, type2, healthPower2, baseAttackPower2);

        int winner = Pokemon.battle(p1, p2);

        System.out.println("First Pokemon's Stats after the battle: \n");
        System.out.println(p1.toString());
        System.out.println("----------------\n");

        System.out.println("Second Pokemon's Stats after the battle: \n");
        System.out.println(p2.toString());
        System.out.println("============================\n");

        if (winner == 1) {
            System.out.println("The winner of battle is "+ p1.getName());
        } else {
            System.out.println("The winner of battle is "+ p2.getName());
        }
    }
}

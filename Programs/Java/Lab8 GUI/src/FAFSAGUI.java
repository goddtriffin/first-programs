import javax.swing.*;

public class FAFSAGUI {

    // Three basic requirements
    static boolean isAcceptedStudent;
    static boolean isSSregistered;
    static boolean hasSSN;

    // One of four required
    static boolean hasValidResidency;

    // Student demographics
    static boolean isDependent;
    static int age;
    static int creditHours;
    static double studentIncome;
    static double parentIncome;
    static String classStanding;

    public static void getMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean getConfirm (String title, String message) {
        boolean done = false;
        boolean action = false;

        while (!done) {
            int ans = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (ans == JOptionPane.YES_OPTION) {
                action = true;
                done = true;
            } else if (ans == JOptionPane.NO_OPTION) {
                action = false;
                done = true;
            }
        }

        return action;
    }

    public static int getRange (String title, String message, int min, int max) {
        boolean done = false;
        String input;

        do {
            input = JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);

            if (MyUtils.isNumeric(input)) {
                int num = Integer.parseInt(input);

                if (max < min) { //only a min (inclusive) imposed, no max
                    if (num >= min) {
                        done = true;
                    } else {
                        JOptionPane.showMessageDialog(null, title +" has to be larger than, or equal to "+ min +".", "Error: "+ title, JOptionPane.ERROR_MESSAGE);
                    }
                } else if (min > max) { //only a max (inclusive) imposed, no min
                    if (num <= max) {
                        done = true;
                    } else {
                        JOptionPane.showMessageDialog(null, title +" has to be less than, or equal to "+ max +".", "Error: "+ title, JOptionPane.ERROR_MESSAGE);
                    }
                } else { //inclusive range imposed
                    if (num >= min && num <= max) {
                        done = true;
                    } else {
                        JOptionPane.showMessageDialog(null, title +" has to be between "+ min +" and "+ max +", inclusive.", "Error: "+ title, JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, title +" has to be numeric.", "Error: "+ title, JOptionPane.ERROR_MESSAGE);
            }
        } while (!done);

        return Integer.parseInt(input);
    }

    public static String getDropDown (String title, String message, String[] options) {
        boolean done = false;
        String choice;

        do {
            choice = (String) JOptionPane.showInputDialog(null, message, title, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            for (int i = 0; i<options.length; i++) {
                if (choice.equals(options[i])) {
                    done = true;
                }
            }
        } while (!done);

        return choice;
    }

    public static void main (String[] args) {
        boolean running = true;

        while (running) {
            getMessage("Welcome", "Welcome to the FAFSA!");

            isAcceptedStudent = getConfirm("Program Acceptance", "Have you been accepted into a degree or certificate program?");

            isSSregistered = getConfirm("Selective Service", "Are you registered for the selective service?");

            hasSSN = getConfirm("Social Security Number", "Do you have a social security number?");

            hasValidResidency = getConfirm("Residency Status", "Do you have valid residency status?");

            age = getRange("Age", "How old are you?", 0, -1);

            creditHours = getRange("Credit Hours", "How many credit hours do you plan on taking?", 1, 24);

            studentIncome = getRange("Student Income", "What is your total yearly income?", 0, -1);

            parentIncome = getRange("Parent Income", "What is your parent's total yearly income?", 0, -1);

            isDependent = getConfirm("Dependency", "Are you a dependent?");

            classStanding = (getDropDown("Class Standing", "What is your current class standing?", new String[]{"Freshman","Sophomore","Junior","Senior","Graduate"}).equals("Graduate"))? "Graduate" : "Undergraduate";

            FAFSA fafsa = new FAFSA(isAcceptedStudent, isSSregistered, hasSSN, hasValidResidency, isDependent, age, creditHours, studentIncome, parentIncome, classStanding);
            getMessage("FAFSA Results", "Loans: $"+ fafsa.calcStaffordLoan() +"\nGrants: $"+ fafsa.calcFederalGrant() +"\nWork Study: $"+ fafsa.calcWorkStudy() +"\n------------\nTotal: $"+ fafsa.calcFederalAidAmount());

            if (!getConfirm("Continue", "Would you like to complete another application?")) {
                running = false;
            }
        }
    }
}

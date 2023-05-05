import javax.swing.*;

/**
 * The BusinessCard class asks for a user's name, major, and email, and outputs it back.
 *
 * @author Todd Griffin, griff170@purdue.edu
 * @version 9/2/2016
 */
public class BusinessCard {

    /**
     * @param args Unused
     */
    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog(null , "Enter your name: ");
        String major = JOptionPane.showInputDialog(null , "Enter your major: ");
        String email = JOptionPane.showInputDialog(null , "Enter your e-mail: ");

        JOptionPane.showMessageDialog(null , "Name: " + name + "\nMajor: " + major + "\nE-mail: " + email);
    }
}
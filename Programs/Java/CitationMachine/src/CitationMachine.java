import javax.swing.*;

/**
 * The CitationMachine class asks for an author's name, year of publication, title of work, publisher, and location only to submit it back in APA style of citation.
 *
 * @author Todd Griffin, griff170@purdue.edu
 * @version 9/9/2016
 */
public class CitationMachine {

    /**
     * @param args Unused
     */
    public static void main (String[] args) {
        JOptionPane.showMessageDialog(null, "Enter book details to generate APA format citation.");

        String[] author = JOptionPane.showInputDialog(null , "Author name: ").split(" ");
        String year = JOptionPane.showInputDialog(null , "Year of publication: ");
        String title = JOptionPane.showInputDialog(null , "Title of work: ");
        String publisher = JOptionPane.showInputDialog(null , "Publisher: ");
        String location = JOptionPane.showInputDialog(null , "Location: ");

        JOptionPane.showMessageDialog(null, author[author.length-1] + ", " + author[0].charAt(0) + ". (" + year + "). " + title + ". " + location + ": " + publisher);
    }
}
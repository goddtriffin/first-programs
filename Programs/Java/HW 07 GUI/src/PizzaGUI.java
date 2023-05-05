import javax.swing.*;

public class PizzaGUI extends JFrame {

    public PizzaGUI () {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,350);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Pizza Shop");

        add(new ContentPanel());

        setVisible(true);
    }

    public static void main (String[] args) {
        new PizzaGUI().pack();
    }
}

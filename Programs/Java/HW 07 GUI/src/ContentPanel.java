import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContentPanel extends JPanel {

    //content panel
    private JButton bSubmit;

    //options panel
    private JPanel pOptions;

    //pizza size
    private JPanel pSize;

    private ButtonGroup bgSize;

    private JRadioButton rbSmall;
    private JRadioButton rbMedium;
    private JRadioButton rbLarge;

    //pizza style
    private JPanel pStyle;

    private JComboBox cbStyle;

    //pizza toppings
    private JPanel pToppings;

    private JCheckBox cbGarlic;
    private JCheckBox cbJalapeno;
    private JCheckBox cbCheese;
    private JCheckBox cbBacon;

    //output
    private JPanel pOutput;

    private JTextArea taOutput;

    public ContentPanel () {
        //options panel
        pOptions = new JPanel(new BorderLayout());
        pOptions.setBorder(BorderFactory.createTitledBorder("Options"));

        //pizza size
        pSize = new JPanel();
        pSize.setBorder(BorderFactory.createTitledBorder("Select your pizza size"));

        bgSize = new ButtonGroup();

        rbSmall = new JRadioButton("Small");
        rbSmall.setSelected(true);
        bgSize.add(rbSmall);
        pSize.add(rbSmall);

        rbMedium = new JRadioButton("Medium");
        bgSize.add(rbMedium);
        pSize.add(rbMedium);

        rbLarge = new JRadioButton("Large");
        bgSize.add(rbLarge);
        pSize.add(rbLarge);

        pOptions.add(pSize, BorderLayout.NORTH);

        //pizza style
        pStyle = new JPanel();
        pStyle.setBorder(BorderFactory.createTitledBorder("Select your pizza style"));

        cbStyle = new JComboBox(new String[]{"Margherita", "Prosciutto", "Diavola", "Verdure", "Calzone"});
        pStyle.add(cbStyle);

        pOptions.add(pStyle, BorderLayout.CENTER);

        //pizza toppings
        pToppings = new JPanel();
        pToppings.setBorder(BorderFactory.createTitledBorder("Choose your toppings"));

        cbGarlic = new JCheckBox("Garlic");
        pToppings.add(cbGarlic);

        cbJalapeno = new JCheckBox("Jalapeno");
        pToppings.add(cbJalapeno);

        cbCheese = new JCheckBox("Extra Cheese");
        pToppings.add(cbCheese);

        cbBacon = new JCheckBox("Bacon");
        pToppings.add(cbBacon);

        pOptions.add(pToppings, BorderLayout.SOUTH);

        //output
        pOutput = new JPanel();
        pOutput.setBorder(BorderFactory.createTitledBorder("Output"));

        taOutput = new JTextArea();
        taOutput.setPreferredSize(new Dimension(150,150));
        taOutput.setLineWrap(true);
        pOutput.add(taOutput);

        //content panel
        setLayout(new BorderLayout());
        add(pOptions, BorderLayout.CENTER);
        add(pOutput, BorderLayout.EAST);

        bSubmit = new JButton("Submit");
        bSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent ae) {
                //clear output
                taOutput.setText("Your custom pizza\n");

                //size
                if (rbSmall.isSelected()) taOutput.append("Size: Small\n");
                if (rbMedium.isSelected()) taOutput.append("Size: Medium\n");
                if (rbLarge.isSelected()) taOutput.append("Size: Large\n");

                //style
                taOutput.append("Style: "+ cbStyle.getSelectedItem().toString() +"\n");

                //toppings
                String toppings = "Toppings: ";
                if (cbGarlic.isSelected()) toppings += "Garlic ";
                if (cbJalapeno.isSelected()) toppings += "Jalapeno ";
                if (cbCheese.isSelected()) toppings += "Extra Cheese ";
                if (cbBacon.isSelected()) toppings += "Bacon";
                if (toppings.equals("Toppings: ")) toppings += "None";
                taOutput.append(toppings);
            }
        });
        bSubmit.setSize(100,30);
        add(bSubmit, BorderLayout.SOUTH);
    }
}

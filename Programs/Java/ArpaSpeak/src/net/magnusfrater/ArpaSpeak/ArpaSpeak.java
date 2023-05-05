package net.magnusfrater.ArpaSpeak;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ArpaSpeak extends JFrame {

    String disclosure =
                    " CMUdict  --  Major Version: 0.07\n" +
                    " ========================================================================\n" +
                    " Copyright (C) 1993-2015 Carnegie Mellon University. All rights reserved.\n" +
                    " \n" +
                    " Redistribution and use in source and binary forms, with or without\n" +
                    " modification, are permitted provided that the following conditions\n" +
                    " are met:\n" +
                    " \n" +
                    "  1. Redistributions of source code must retain the above copyright\n" +
                    "     notice, this list of conditions and the following disclaimer.\n" +
                    "     The contents of this file are deemed to be source code.\n" +
                    " \n" +
                    "  2. Redistributions in binary form must reproduce the above copyright\n" +
                    "     notice, this list of conditions and the following disclaimer in\n" +
                    "     the documentation and/or other materials provided with the\n" +
                    "     distribution.\n" +
                    " \n" +
                    " This work was supported in part by funding from the Defense Advanced\n" +
                    " Research Projects Agency, the Office of Naval Research and the National\n" +
                    " Science Foundation of the United States of America, and by member\n" +
                    " companies of the Carnegie Mellon Sphinx Speech Consortium. We acknowledge\n" +
                    " the contributions of many volunteers to the expansion and improvement of\n" +
                    " this dictionary.\n" +
                    " \n" +
                    " THIS SOFTWARE IS PROVIDED BY CARNEGIE MELLON UNIVERSITY ``AS IS'' AND\n" +
                    " ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,\n" +
                    " THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR\n" +
                    " PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL CARNEGIE MELLON UNIVERSITY\n" +
                    " NOR ITS EMPLOYEES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,\n" +
                    " SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT\n" +
                    " LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,\n" +
                    " DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY\n" +
                    " THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT\n" +
                    " (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE\n" +
                    " OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\n" +
                    " \n" +
                    " ========================================================================\n" +
                    "\n" +
                    "  NOTES\n" +
                    "\n" +
                    "- [20080401] (air)  New dict file format introduced\n" +
                    "- comments (like this section) are allowed\n" +
                    "- file name is major version; vers/rev information is now in the header\n";

    //FRAME
    private Dimension size = new Dimension(1000,600);

    //TTS
    private Speak speak = new Speak();

    public ArpaSpeak () {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(size);
        setResizable(true);
        setLocationRelativeTo(null);
        setTitle("ArpaSpeak: by Todd Griffin");

        speak = new Speak();

        //initComponent();
        showImage();

        setVisible(true);
    }

    private void initComponents () {
        //panels
        JPanel north = new JPanel();
        north.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel center = new JPanel(new FlowLayout());
        center.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel south = new JPanel();
        south.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //north
        JButton jbDisclosure = new JButton("Disclosure");
        jbDisclosure.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                JOptionPane.showMessageDialog(null, disclosure);
            }
        });
        north.add(jbDisclosure);

        //center
        JTextArea jtaInput = new JTextArea("I am the input box.");
        jtaInput.setLineWrap(true);
        jtaInput.setSize(center.getWidth()/2,center.getHeight());
        jtaInput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        center.add(jtaInput);

        JTextArea jtaOutput = new JTextArea("I am the output box.");
        jtaOutput.setLineWrap(true);
        jtaOutput.setSize(center.getWidth()/2,center.getHeight());
        jtaOutput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        center.add(jtaOutput);

        //south
        JButton jbSpeak = new JButton("Speak");
        jbSpeak.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                //speak here
                //speak.say( jtfInput.getText() );
            }
        });
        south.add(jbSpeak);

        //frame
        add(north, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);
    }

    private void initComponent(){
        //panel
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton jbDisclosure = new JButton("Disclosure");
        jbDisclosure.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                JOptionPane.showMessageDialog(null, disclosure);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
    }

    private void showImage(){
        try {
            InputStream is = new BufferedInputStream(ResourceLoader.getInputStream("Toad.png"));
            Image image = ImageIO.read(is);

            add(new JLabel(new ImageIcon( image )));
        } catch (IOException ioe) {
            System.out.println("IOE: " + ioe);
        }
    }

    public static void main (String[] args) {
        ArpaSpeak tts = new ArpaSpeak();
    }
}

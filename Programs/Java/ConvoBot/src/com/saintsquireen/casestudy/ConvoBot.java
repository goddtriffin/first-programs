package com.saintsquireen.casestudy;

import java.util.Scanner;

public class ConvoBot {

    private String directory;
    private String inDatabase,outDatabase,database;

    ConvoTool ct;

    private boolean debug;

    private boolean running;

    public ConvoBot(){ //declares directory housing 'questions.txt' and 'answers.txt'
        Scanner s = new Scanner(System.in);

        System.out.print("Enter in directory for the folder housing the \'questions.txt\' and \'answers.txt\': ");
        this.directory = s.nextLine();
        this.inDatabase = this.directory + "\\inDatabase.txt";
        this.outDatabase = this.directory + "\\outDatabase.txt";
        this.database = this.directory + "\\database.txt";

        this.ct = new ConvoTool(inDatabase,outDatabase);

        this.debug = false;

        this.running = true;
    }

    public static void main(String[] args) {
        ConvoBot Neil = new ConvoBot();

        Scanner s = new Scanner(System.in);

        if (Neil.debug) Neil.ct.displayAllFileStats(Neil.debug);
        if (Neil.debug) Neil.ct.displayAllFileContent(Neil.debug);

        while (Neil.running){
            String output = Neil.ct.output(s.nextLine(),Neil.debug);

            if (output.substring(0,2).equals("~~")){ //if output is command
                Neil.doCommand(output.substring(2));
            }else{
                System.out.println(output); //if not command, display actual response to input
            }
        }
    }

    private void doCommand(String command){
        switch (command){
            case "quit": this.running = false;
                break;
        }
    }
}

/*
Home Database Directory:
D:\Code\Programs\Java\ConvoBot\res

School Database Directory:
C:\Users\tgri1515\IdeaProjects\ConvoBot\res

IOFile Website:
https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
*/
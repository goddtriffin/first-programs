package com.saintsquireen.casestudy;

import java.util.Random;

public class ConvoTool {

    private DatabaseService ds;

    private String inDatabase,outDatabase;

    public ConvoTool(String inDatabase, String outDatabase){
        ds = new DatabaseService();

        this.inDatabase = inDatabase;
        this.outDatabase = outDatabase;
    }

    protected String output(String userInput,boolean debug){
        if (userInput.substring(0,2).equals("~~")) return userInput;

        String output = null;



        return output;
    }

    protected String returnRndInLine(boolean debug){ //returns random line from inDatabase
        int randomIndex = rndInt(0,this.ds.returnLineCount(inDatabase,debug) -1);

        return this.ds.returnLineAtIndex(outDatabase,randomIndex,debug);
    }
    protected String returnRndOutLine(boolean debug){ //returns random line from outDatabase
        int randomIndex = rndInt(0,this.ds.returnLineCount(inDatabase,debug) -1);

        return this.ds.returnLineAtIndex(outDatabase,randomIndex,debug);
    }
    protected String returnRndLine(boolean debug){
        if (rndInt(0,1) == 0){
            return returnRndInLine(debug);
        }
        return returnRndOutLine(debug);
    }

    protected void displayAllFileStats(boolean debug){ //displays all stats from inDatabase & outDatabase
        this.ds.returnAllStats(inDatabase, debug);
        this.ds.returnAllStats(outDatabase, debug);
    }

    protected void displayAllFileContent(boolean debug){ //returns contents of 'questions.txt & 'answers.txt'
        this.ds.displayContent(inDatabase, debug);
        this.ds.displayContent(outDatabase, debug);
    }

    private int rndInt(int low, int high){
        Random r = new Random();

        return r.nextInt(high) + low;
    }
}
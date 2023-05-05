package com.saintsquireen.casestudy;

import java.io.*;

public class DatabaseService {

    protected void appendLine(String file, String line, boolean debug){ //appends a line to a given database
        String fileName = "\'"+ file.substring(file.lastIndexOf('\\')+1) +"\'";

        if (debug) System.out.println("Attempting to Append \""+ line +"\" to "+ fileName +"...");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(line + "\n");

            fileWriter.close();
            if (debug) System.out.println("Successfully Appended \""+ line +"\" to " + file + "!\n");
        }
        catch(FileNotFoundException ex) {
            if (debug) System.out.println("Unable to open file \'" + file + "\'!!!\n");
        }
        catch(IOException ex) {
            if (debug) System.out.println("Error writing to file '" + file + "'\n");
        }
    }

    protected int returnLineCount(String file, boolean debug){ //returns number of lines in specified database
        String fileName = "\'"+ file.substring(file.lastIndexOf('\\')+1) +"\'";

        String line;

        int lines = 0;

        if (debug) System.out.println("Reading "+ fileName +" Contents: ");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                lines++; //adds # of lines
            }

            bufferedReader.close();

            if (debug) System.out.println("Finished Reading " + fileName + " Contents!\n");
        }
        catch(FileNotFoundException ex) {
            if (debug) System.out.println("Unable to open file \'" + file + "\'!!!\n");
            lines = -1;
        }
        catch(IOException ex) {
            if (debug) System.out.println("Error reading file \'" + file + "\'!!!\n");
            lines = -1;
        }

        return lines;
    }

    protected void returnAllStats(String file, boolean debug){ //displays # of lines,words,chars,& size(bytes) in specified database
        String fileName = "\'"+ file.substring(file.lastIndexOf('\\')+1) +"\'";

        String line;

        int chars,words,lines;
        chars = words = lines = 0;

        if (debug) System.out.println("Reading "+ fileName +" Contents: ");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                String[] lineSplitted = line.split("\\s+");

                lines++; //adds # of lines
                words += lineSplitted.length; //adds # of words per line
                for (int i=0;i<lineSplitted.length;i++) chars += lineSplitted[i].length(); //adds # of chars per word
            }

            bufferedReader.close();

            System.out.println("\t# of Lines: " + lines); //displays stats
            System.out.println("\t# of Words: "+ words);
            System.out.println("\t# of Chars: " + chars);
            System.out.println("\tSize: " + (new File(file).length()) + " bytes");

            if (debug) System.out.println("Finished Reading " + fileName + " Contents!\n");
        }
        catch(FileNotFoundException ex) {
            if (debug) System.out.println("Unable to open file \'" + file + "\'!!!\n");
        }
        catch(IOException ex) {
            if (debug) System.out.println("Error reading file \'" + file + "\'!!!\n");
        }
    }

    protected void displayContent(String file, boolean debug){ //displays every line in a given database
        String fileName = "\'"+ file.substring(file.lastIndexOf('\\')+1) +"\'";

        String line;

        if (debug) System.out.println("Reading "+ fileName +" Contents: ");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println("\t"+ line);
            }

            bufferedReader.close();
            if (debug) System.out.println("Finished Reading " + fileName + " Contents!\n");
        }
        catch(FileNotFoundException ex) {
            if (debug) System.out.println("Unable to open file \'" + file + "\'!!!\n");
        }
        catch(IOException ex) {
            if (debug) System.out.println("Error reading file \'" + file + "\'!!!\n");
        }
    }

    protected int findLine(String file, String lineToFind, int startLoc, boolean debug){ //finds and returns index of a specified line's located
        String fileName = "\'"+ file.substring(file.lastIndexOf('\\')+1) +"\'";

        String line;

        int index = 0;
        int lineIndex = -1;

        if (debug) System.out.println("Reading "+ fileName +" Contents: ");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                if (index >= startLoc){
                    if (line.equals(lineToFind)){
                        lineIndex = index;
                        break;
                    }
                }

                index++;
            }

            bufferedReader.close();
            if (debug) System.out.println("Finished Reading " + fileName + " Contents!\n");
        }
        catch(FileNotFoundException ex) {
            if (debug) System.out.println("Unable to open file \'" + file + "\'!!!\n");
        }
        catch(IOException ex) {
            if (debug) System.out.println("Error reading file \'" + file + "\'!!!\n");
        }

        return lineIndex;
    }

    protected String returnLineAtIndex(String file, int specifiedIndex, boolean debug){ //returns line located at specified index in specified database
        String fileName = "\'"+ file.substring(file.lastIndexOf('\\')+1) +"\'";

        String line;

        int loopIndex = 0;
        String specifiedLine = null;

        if (debug) System.out.println("Reading "+ fileName +" Contents: ");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                if (loopIndex == specifiedIndex){
                    specifiedLine = line;
                    break;
                }

                loopIndex++;
            }

            bufferedReader.close();
            if (debug) System.out.println("Finished Reading " + fileName + " Contents!\n");
        }
        catch(FileNotFoundException ex) {
            if (debug) System.out.println("Unable to open file \'" + file + "\'!!!\n");
        }
        catch(IOException ex) {
            if (debug) System.out.println("Error reading file \'" + file + "\'!!!\n");
        }

        return specifiedLine;
    }

    protected int returnLineFirstIndex(String file, String line, boolean debug){ //searches specified database for first occurrence of specified line
        return findLine(file, line, 0, debug);
    }

    protected int returnLineInstanceCount(String file, String line, boolean debug){ //counts number of times specified line appears in specified database
        int lineIndex = returnLineFirstIndex(file, line, debug);

        if (lineIndex == -1) return 0;

        int count = 0;

        while(lineIndex != -1){
            count++;

            lineIndex = findLine(file,line,lineIndex +1, debug);
        }

        return count;
    }

    protected void clear(String file, boolean debug){ //clears all contents in a given database
        String fileName = "\'"+ file.substring(file.lastIndexOf('\\')+1) +"\'";

        if (debug) System.out.println("Attempting to Clear "+ fileName +"...");
        try{
            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            raf.setLength(0);

            raf.close();
            if (debug) System.out.println("Successfully Cleared " + file + "!\n");
        }
        catch(FileNotFoundException ex){
            if (debug) System.out.println("Unable to open file \'" + file + "\'!!!\n");
        }
        catch(IOException ex) {
            if (debug) System.out.println("Error reading file \'" + file + "\'!!!\n");
        }
    }
}
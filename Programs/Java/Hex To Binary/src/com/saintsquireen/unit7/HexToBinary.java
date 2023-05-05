package com.saintsquireen.unit7;

public class HexToBinary {

    private void calculate(char hexChar){
        switch(hexChar){
            case 'A': System.out.println("A is 1010 in binary!");
                break;
            case 'B': System.out.println("B is 1011 in binary!");
                break;
            case 'C': System.out.println("C is 1100 in binary!");
                break;
            case 'D': System.out.println("D is 1101 in binary!");
                break;
            case 'E': System.out.println("E is 1110 in binary!");
                break;
            case 'F': System.out.println("F is 1111 in binary!");
                break;
            default : System.out.println(hexChar +" is ERROR in binary!");
                break;
        }
    }

    public static void main(String[] args){
        HexToBinary htb = new HexToBinary();

        char[] hexChars = {'A','B','C','D','E','F','X'};

        for (int x=0; x<7; x++){
            htb.calculate(hexChars[x]);
        }
    }
}
package com.saintsquireen.unit7;

public class MorseCode {

    private String calculate(char letter){
        String str;

        switch(letter){
            case 'a': str = ".-";
                break;
            case 'b': str = "-...";
                break;
            case 'c': str = "-.-.";
                break;
            case 'd': str = "-..";
                break;
            case 'e': str = ".";
                break;
            case 'f': str = "..-.";
                break;
            case 'g': str = "--.";
                break;
            case 'h': str = "....";
                break;
            case 'i': str = "..";
                break;
            case 'j': str = ".---";
                break;
            case 'k': str = "-.-";
                break;
            case 'l': str = ".-..";
                break;
            case 'm': str = "--";
                break;
            case 'n': str = "-.";
                break;
            case 'o': str = "---";
                break;
            case 'p': str = ".--.";
                break;
            case 'q': str = "--.-";
                break;
            case 'r': str = ".-.";
                break;
            case 's': str = "...";
                break;
            case 't': str = "-";
                break;
            case 'u': str = "..-";
                break;
            case 'v': str = "...-";
                break;
            case 'w': str = ".--";
                break;
            case 'x': str = "-..-";
                break;
            case 'y': str = "-.--";
                break;
            case 'z': str = "--..";
                break;
            case '0': str = "-----";
                break;
            case '1': str = ".----";
                break;
            case '2': str = "..---";
                break;
            case '3': str = "...--";
                break;
            case '4': str = "....-";
                break;
            case '5': str = ".....";
                break;
            case '6': str = "-....";
                break;
            case '7': str = "--...";
                break;
            case '8': str = "---..";
                break;
            case '9': str = "----.";
                break;
            default: str = "**NOT IN DATABASE**";
        }

        return str;
    }

    public static void main(String[] args){
        MorseCode mc = new MorseCode();

        char[] set = {'a','b','3','z','8','f','0'};

        for (int x= 0; x < 7; x++) {
            System.out.println(set[x] + " is "+ mc.calculate(set[x]) +" in Morse Code.");
        }
    }
}
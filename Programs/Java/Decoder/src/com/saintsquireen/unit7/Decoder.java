package com.saintsquireen.unit7;

public class Decoder {

    private char calculate(char c){
        if (c>=65&&c<=90) return (char)(c+32);
        if (c>=97&&c<=122) return (char)(c-32);
        if (c>=48&&c<=57) return (char)(c+17);
        return (char)35;
    }

    public static void main(String[] args){
        Decoder d = new Decoder();

        char[] hexChars = {'a','A','b','0','t','*','h','T'};

        for (int x=0; x<8; x++){
            System.out.println(hexChars[x] + " decodes to " + d.calculate(hexChars[x]));
        }
    }
}
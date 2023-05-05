package com.saintsquireen.unit7;

public class MonsterCheck {

    private void calculate(String m1Name, int m1Size, String m2Name, int m2Size){
        if (m1Size>m2Size){
            System.out.println(m1Name +" is bigger than "+ m2Name);
        }else{
            System.out.println(m1Name +" is smaller than "+ m2Name);
        }
    }

    public static void main(String[] args){
        MonsterCheck mc = new MonsterCheck();

        String[] set = {"Tom","2", "Bob","4", "Sally","7", "Fred","2", "Ann","1", "Ann","4"};

        for (int x=0; x<3; x++){
            mc.calculate(set[4*x],Integer.parseInt(set[4*x+1]),set[4*x+2],Integer.parseInt(set[4*x+3]));
        }
    }
}
package com.saintsquireen.unit7;

public class GradeCheck {

    private char calculate(int grade){
        if (grade >= 90) return 'A';
        if (grade >= 80 && grade < 90) return 'B';
        if (grade >= 75 && grade < 80) return 'C';
        if (grade >= 70 && grade < 75) return 'D';
        return 'F';
    }

    public static void main(String[] args){
        GradeCheck gc = new GradeCheck();

        int[] grades = {78,92,31,82,77,73,55,65};

        for (int x=0; x<8; x++){
            System.out.println(grades[x] +" is a(n) "+ gc.calculate(grades[x]) +"\n");
        }
    }
}
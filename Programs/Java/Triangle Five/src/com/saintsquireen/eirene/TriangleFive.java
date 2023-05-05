package com.saintsquireen.eirene;

public class TriangleFive {

    public void print(int amount, char letter){
        for (int i=0; i<amount; i++){
            char temp = letter;

            for(int j=0; j<amount - i; j++){
                for(int k=0; k<amount - j; k++){
                    System.out.print(temp);
                }
                System.out.print(" ");

                int ascii = (int)temp + 1;
                if (ascii>90) ascii = 65;
                temp = (char)ascii;
            }

            System.out.println();
        }

        System.out.println("\n");
    }

    public static void main(String args[]){
        TriangleFive run = new TriangleFive();

        int[] nums = {4,8,1,3,5,1,52};
        char[] let = {'C','H','Y','T','O','R','A'};

        for(int n=0; n<nums.length; n++){
            run.print(nums[n],let[n]);
        }
    }
}
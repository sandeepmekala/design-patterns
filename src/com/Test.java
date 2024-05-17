package com;

public class Test {
    public static void main(String[] args) {
        //get random number
        int random = (int) (Math.random() * 6)+1;

        // get a random number between 5 to 10
        random = (int) (Math.random() * 6)+5;

        // get a random number between 1 to 6
        random = (int) (Math.random() * 6)+1; 

        System.out.println(random);
    }
}

package com.learning.DSA.ArraysPractice;

import java.util.Arrays;

public class Array2 {


    static int[] myArray = {7, 8, 6, 2, 5,};
    static int[] RevArray = new int[myArray.length];

    public static void main(String[] args) {

        FindArrayElement();
        System.out.println(Arrays.toString(myArray));
        ArrayReverse();
        System.out.println(Arrays.toString(RevArray));

    }

    public static void FindArrayElement() {

        for (int item : myArray) {
            int x = 2;
            if (x == item) {
                System.out.println("The given integer present in the array");

            }
        }
    }

    public static void ArrayReverse() {

        for (int i = myArray.length - 1; i >= 0; i--) {
            for (int j = 0; j > myArray.length; j++) {
                RevArray[j] = myArray[i];

            }

        }


    }
}
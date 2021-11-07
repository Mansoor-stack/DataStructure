package com.learning.DSA.ArraysPractice;

/*

1. For each loop in array
2. Print Array
3. Declare Array and Initialize Array

 */
public class Array1 {

    public static void main(String[] args) {
        int[] myArray = new int[7];

        myArray[0] = 2;
        myArray[1] = 3;
        myArray[2] = 4;
        myArray[3] = 5;
        myArray[4] = 6;
        myArray[5] = 7;
        myArray[6] = 8;

        for (int i = 0; i < myArray.length; i++) {
            System.out.println(myArray[i]);
        }
        System.out.println("**********************");
        for (int item : myArray) {
            System.out.println(item);
        }
        System.out.println("**********************");
        float[] FloatArray = {0.20f, 0.30f, 0.40f, 0.50f, 0.60f};
        float sum = FloatArray[0] + FloatArray[1] + FloatArray[2] + FloatArray[3] + FloatArray[4];
        System.out.println(sum);
        System.out.println("**********************");
        int a = 2;
        for (int i = 0; i < myArray.length; i++) {
            if (a == myArray[i]) {
                System.out.printf("The integer %d is present in an MyArray ", a);

            }
        }


    }


}

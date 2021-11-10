package com.learning.DSA.InsertionSort;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int myArray[] = {12, 11, 13, 5};

        for (int i = 1; i < myArray.length; i++) {
            for (int j = 0; j < i && myArray[i] < myArray[j]; j++) {


                int temp = myArray[j];
                myArray[j] = myArray[i];
                myArray[i] = temp;


            }

        }
        System.out.println(Arrays.toString(myArray));

    }
}

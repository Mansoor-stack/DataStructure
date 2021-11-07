package com.learning.DSA.SelectionSort;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int myArray[] = {14, 33, 27, 10, 35, 9, 42, 44};

        for (int i = 0; i < myArray.length; i++) {
            for (int j = i + 1; j < myArray.length; j++) {
                if (myArray[i] > myArray[j]) {
                    int temp = myArray[i];
                    myArray[i] = myArray[j];
                    myArray[j] = temp;

                }

            }

        }
        System.out.println(Arrays.toString(myArray));
    }
}

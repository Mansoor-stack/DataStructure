package com.learning.DSA.BubbleSort;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {

        int myArray[] = {20, 35, -15, 7, 55, 1, -22};

        int n = myArray.length;
///Approach 1

        for (int i = n - 1; i > 0; i--) {

            for (int j = 0; j < n - 1; j++) {
                if (myArray[j] > myArray[j + 1]) {
                    int temp = myArray[j];
                    myArray[j] = myArray[j + 1];
                    myArray[j + 1] = temp;
                }
            }


        }
        for (int j = 0; j < n; j++) {
            System.out.println(myArray[j]);
        }

        System.out.println(Arrays.toString(myArray));


    }
}


package com.test.temp;

import java.util.*;

public class CyclicRotation {
    public static void main(String args[]) {
        /*int n = 5;
        int arr[] = new int[n];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 3;
        arr[3] = 4;
        arr[4] = 5;
        int k = 2;
        int te1=0;
        int[] temp = new int[k];
        for(int i=n-k,j=0; i<n; i++,j++) {
            temp[j] = arr[i];
        }
        for(int l=0,o=k;l<k;l++,o++){
            if(l==0) {
                te1 = arr[o];
            }
            arr[o] = arr[l];
        }
        arr[n-1] = te1;
        for(int m=0; m<k;m++) {
            arr[m] = temp[m];
        }*/
        System.out.println("args... " +args[0]);
        //Map<Character,Integer> mpp = new HashMap<>();
        //mpp.getOrDefault(mpp, null);
        


     }
     static void reverse(int[] arr, int left, int right) {
        while(left<right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
     }
     static int[] cyclicRotation1(int n, int[] arr, int k) {
        k = k % n;
        reverse(arr, 0, n-k-1);
        reverse(arr, n-k, n-1);
        reverse(arr, 0, n-1);
        return arr;
     }


}

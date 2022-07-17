

import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;



public class Sorting {

    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr.length <= 1) {
            return;
        }


        int length = arr.length;
        int mid = length / 2;
        int size2 = length - mid;
        int size1 = length - size2;  
        T[] left = (T[])new Object[size1];
        T[] right = (T[])new Object[size2];

        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }
        for (int i = mid; i < length; i++) {
            right[i] = arr[i];
        }

        mergeSort(left, comparator);
        mergeSort(right, comparator);
        int i = 0;
        int j = 0;
        while (i < left.length && j < right.length) {

            if (comparator.compare(left[i],right[j]) <= 0) {
                arr[i + j] = left[i];
                i++;
            }
            else {
                arr[i + j] = right[j];
                j++;
            }
        }
        while (i < left.length) {
            arr[i + j] = left[i];
            i++;
        }
        while (j < right.length) {
            arr[i + j] = right[j];
            j++;
        }


    }


    public static void lsdRadixSort(int[] arr) {
        LinkedList[] buckets = new LinkedList[20];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<Integer>();
        }
        int k = getMaxDigits(arr);

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < arr.length; j++) {
                int current_digit = getCurrentDigit(arr[j], i);
                if (current_digit < 0) {
                    int index = (10 - (-1 * current_digit));
                    buckets[index].add(arr[j]);
                }
                else if (current_digit > 0) {
                    buckets[current_digit + 10].add(arr[j]);
                }
                else {
                    buckets[10].add(arr[j]);
                }
            }

             int idx = 0;
            for (int m = 0; m < buckets.length; m++) {
                LinkedList bucket = buckets[m]; 
                while (bucket.size() != 0) {
                    arr[idx] = (int)bucket.remove();
                    idx++;
                }
            }




        }






    }


    private static int getCurrentDigit(int number, int index) {
        
        String s = String.valueOf(number);
        int idx = s.length() - 1 - index;
        if (idx < 0) {
            return 0;
        }
        String tmp = String.valueOf(s.charAt(idx));
        if (tmp.equals("-")) {
            return 0;
        }

        if (number < 0) {
            return -Integer.parseInt(tmp);
        }
        else {
            return Integer.parseInt(tmp);
        }

    }




    private static int getNumDigits(int num) {
        if (num < 0) {
            num *= -1;
        }



        if (num < 10) {
            return 1;
        }
        else {
            return getNumDigits((num / 10) + num % 10) + 1;
        }
    }



    private static int getMaxDigits(int[] arr) {

        int max = 0;


        for (int i = 0; i < arr.length; i++) {
            int tmp = getNumDigits(arr[i]);
            if (tmp > max) {
                max = tmp;
            } 
        }

        return max;

    }



    




}
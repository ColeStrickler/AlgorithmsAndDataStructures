import java.util.Comparator;


public class Sorting {

    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
       int stop = arr.length - 1;
       int lastSwapped = stop;
       boolean go = false;

       while (stop != 0) {

        int i = 0;
        go = false;
        while (i < stop) {

            if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                T temp = arr[i];;
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                lastSwapped = i; // changed from i + 1
                go = true;
            }
            i++;

        }
        if (!go) {
            return;
        }
        stop = lastSwapped;

        
       }



    }

    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            T temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;

        }
    }


    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        for (int i = 1; i < arr.length; i ++) {
            T key = arr[i];
            int j = i - 1;

            while(j >= 0 && comparator.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }

            arr[j + 1] = key;
        }
    }







}
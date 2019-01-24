import java.util.Random;

public class QuickSort {
    /**
     * Demonstrates the quicksorting algorithm by starting with an array of 100 random elements (0-100) and sorting it
     * @param args
     */
    public static void main(String[] args){
        int[] randArray = new int[100];
        Random rand = new Random();

        for (int i=0; i < randArray.length; i++){
            randArray[i] = rand.nextInt(101);
        }
        printArray(randArray);
        sort(randArray, 0, randArray.length-1);
        printArray(randArray);
    }

    /**
     * implements a dual pivot quicksort algorithm to sort an array
     * @param arr unsorted array of ints
     * @param lo first point in the array to be sorted
     * @param hi last point in the array to be sorted
     */
    public static void sort(int[] arr, int lo, int hi) {
        if (hi <= lo) return;

        if (arr[hi]< arr[lo]) swap(arr, lo, hi);

        int pt1 = lo + 1;
        int pt2 = hi - 1;
        int i = lo + 1;

        while (i <= pt2)
        {
            if (arr[i] < arr[lo]) swap(arr, pt1++, i++);
            else if (arr[hi] < arr[i]) swap(arr, i, pt2--);
            else i++;
        }

        swap(arr, lo, --pt1);
        swap(arr, hi, ++pt2);
        sort(arr, lo, pt1-1);
        if (arr[pt1]<arr[pt2]) sort(arr,pt1+1,pt2-1);
        sort(arr, pt2+1, hi);
    }

    /**
     * Helper function to swap two items in an array
     * @param arr array input
     * @param i index of item 1
     * @param j index of item 2
     */
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Helper function to display array
     * @param arr array to be displayed
     */
    public static void printArray(int[] arr){
        System.out.print('[');
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println(']');
    }
}

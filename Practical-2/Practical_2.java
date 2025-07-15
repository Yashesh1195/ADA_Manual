import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Practical_2 {
    static long steps = 0;

    public static void selectionSort(int[] arr) {
        steps = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            steps++; // outer loop
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                steps++; // inner loop
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                    steps++;
                }
            }
            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
                steps += 3; // swaps
            }
        }
    }

    public static void bubbleSort(int[] arr) {
        steps = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            steps++;
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                steps++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    steps += 4;
                    swapped = true;
                }
            }
            if (!swapped) {
                steps++;
                break;
            }
        }
    }

    public static void insertionSort(int[] arr) {
        steps = 0;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            steps++;
            int key = arr[i];
            int j = i - 1;
            steps++;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                steps += 2;
            }
            arr[j + 1] = key;
            steps++;
        }
    }

    public static void mergeSort(int[] arr) {
        steps = 0;
        divide(arr, 0, arr.length - 1);
    }

    private static void divide(int[] arr, int low, int high) {
        if (low < high) {
            steps++;
            int mid = low + (high - low) / 2;
            divide(arr, low, mid);
            divide(arr, mid + 1, high);
            conquer(arr, low, mid, high);
        }
    }

    private static void conquer(int[] arr, int low, int mid, int high) {
        int[] merged = new int[high - low + 1];
        int idx1 = low, idx2 = mid + 1, x = 0;

        while (idx1 <= mid && idx2 <= high) {
            steps++;
            if (arr[idx1] <= arr[idx2]) merged[x++] = arr[idx1++];
            else merged[x++] = arr[idx2++];
        }
        while (idx1 <= mid) merged[x++] = arr[idx1++];
        while (idx2 <= high) merged[x++] = arr[idx2++];
        for(int i=0, j=low; i<merged.length; i++, j++) {
            arr[j] = merged[i];
        }
        steps += (high - low + 1); // copying back
    }

    public static void quickSort(int[] arr) {
        steps = 0;
        quickSortHelper(arr, 0, arr.length - 1);
    }

    private static void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            steps++;
            int pivotIdx = partition(arr, low, high);
            quickSortHelper(arr, low, pivotIdx - 1);
            quickSortHelper(arr, pivotIdx + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low-1;

        for(int j=low; j<high; j++) {
            if(arr[j] < pivot) {
                i++;

                // swap
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                steps += 3;
            }
        }
        i++;
        int temp = arr[i];
        arr[i] = pivot;
        arr[high] = temp;
        steps += 3;
        return i; // pivot index
    }

    // Generate arrays
    public static int[] generateRandomArray(int n) {
        int[] arr = new int[n];
        Random rand = new Random();
        for(int i=0;i<n;i++) arr[i] = rand.nextInt(n);
        return arr;
    }

    public static int[] generateAscendingArray(int n) {
        int[] arr = new int[n];
        for(int i=0;i<n;i++) arr[i] = i;
        return arr;
    }

    public static int[] generateDescendingArray(int n) {
        int[] arr = new int[n];
        for(int i=0;i<n;i++) arr[i] = n-i;
        return arr;
    }

    public static void main(String[] args) throws IOException {
        int[] sizes = {1000, 2000, 3000, 4000, 5000};
        String[] types = {"Random", "Ascending", "Descending"};

        for (String type : types) {
            System.out.println("\n--- " + type + " Data ---");
            System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s\n", "Input", "Selection", "Bubble", "Insertion", "Merge", "Quick");

            String fileName = type.toLowerCase() + ".txt";

            try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Input,Selection,Bubble,Insertion,Merge,Quick\n");  // CSV-style header

                for (int size : sizes) {
                int[] original = switch (type) {
                    case "Ascending" -> generateAscendingArray(size);
                    case "Descending" -> generateDescendingArray(size);
                    default -> generateRandomArray(size);
                };

                    int[] a, b, c, d, e;

                    a = Arrays.copyOf(original, size);
                    selectionSort(a);
                    long sel = steps;

                    b = Arrays.copyOf(original, size);
                    bubbleSort(b);
                    long bub = steps;

                    c = Arrays.copyOf(original, size);
                    insertionSort(c);
                    long ins = steps;

                    d = Arrays.copyOf(original, size);
                    mergeSort(d);
                    long mer = steps;

                    e = Arrays.copyOf(original, size);
                    quickSort(e);
                    long qui = steps;

                    System.out.printf("%-10d %-10d %-10d %-10d %-10d %-10d\n", size, sel, bub, ins, mer, qui);

                    // Write to file
                    writer.write(size + "," + sel + "," + bub + "," + ins + "," + mer + "," + qui + "\n");
                }
            }
        }
    }
}
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Practical_6 {
    static long basicSteps = 0;
    static long randomSteps = 0;

    // ------------------- BASIC QUICK SORT -------------------
    public static void basicQuickSort(int[] arr) {
        basicSteps = 0;
        basicQuickSortHelper(arr, 0, arr.length - 1);
    }

    private static void basicQuickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            basicSteps++;
            int pivotIdx = basicPartition(arr, low, high);
            basicQuickSortHelper(arr, low, pivotIdx - 1);
            basicQuickSortHelper(arr, pivotIdx + 1, high);
        }
    }

    private static int basicPartition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
                basicSteps += 3;
            }
        }
        i++;
        swap(arr, i, high);
        basicSteps += 3;
        return i;
    }

    // ------------------- RANDOMIZED QUICK SORT -------------------
    public static void randomizedQuickSort(int[] arr) {
        randomSteps = 0;
        randomizedQuickSortHelper(arr, 0, arr.length - 1);
    }

    private static void randomizedQuickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            randomSteps++;
            int pivotIdx = randomizedPartition(arr, low, high);
            randomizedQuickSortHelper(arr, low, pivotIdx - 1);
            randomizedQuickSortHelper(arr, pivotIdx + 1, high);
        }
    }

    private static int randomizedPartition(int[] arr, int low, int high) {
        Random rand = new Random();
        int randIdx = low + rand.nextInt(high - low + 1);
        swap(arr, randIdx, high);  // Move random pivot to end
        randomSteps++;
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
                randomSteps += 3;
            }
        }
        i++;
        swap(arr, i, high);
        randomSteps += 3;
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    // ------------------- INPUT GENERATORS -------------------
    public static int[] generateRandomArray(int n) {
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rand.nextInt(n);
        return arr;
    }

    public static int[] generateAscendingArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        return arr;
    }

    public static int[] generateDescendingArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = n - i;
        return arr;
    }

    // ------------------- MAIN DRIVER -------------------
    public static void main(String[] args) throws IOException {
        int[] sizes = {1000, 2000, 3000, 4000, 5000};
        String[] types = {"Random", "Ascending", "Descending"};

        for (String type : types) {
            String fileName = type.toLowerCase() + ".txt";
            System.out.println("\n====================== " + type.toUpperCase() + " DATA ======================");
            System.out.printf("%-10s %-20s %-20s\n", "Input", "BasicQuickSteps", "RandomQuickSteps");

            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write("Input,BasicQuickSteps,RandomQuickSteps\n");

                for (int size : sizes) {
                    int[] original = switch (type) {
                        case "Ascending" -> generateAscendingArray(size);
                        case "Descending" -> generateDescendingArray(size);
                        default -> generateRandomArray(size);
                    };

                    int[] arr1 = Arrays.copyOf(original, original.length);
                    int[] arr2 = Arrays.copyOf(original, original.length);

                    basicQuickSort(arr1);
                    randomizedQuickSort(arr2);

                    writer.write(size + "," + basicSteps + "," + randomSteps + "\n");
                    System.out.printf("%-10d %-20d %-20d\n", size, basicSteps, randomSteps);
                }
            }
        }

        // System.out.println("All data saved to random.txt, ascending.txt, and descending.txt");
    }
}

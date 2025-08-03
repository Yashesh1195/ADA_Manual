import java.io.FileWriter;
import java.io.IOException;

public class Practical_4 {
    static int linearSteps = 0;
    static int binarySteps = 0;

    // Linear Search (Iterative)
    public static int linearSearch(int[] arr, int key) {
        linearSteps = 0;
        for (int i = 0; i < arr.length; i++) {
            linearSteps++;
            if (arr[i] == key)
                return i;
        }
        return -1;
    }

    // Binary Search (Recursive)
    public static int binarySearch(int arr[], int key, int start, int end) {
        binarySteps++;
        if(start <= end) {
            int mid = (start + end) / 2;
            
            if(arr[mid] == key) {
                return mid;
            } else if(arr[mid] > key) {
                return binarySearch(arr, key, 0, mid - 1);
            } else {
                return binarySearch(arr, key, mid + 1, end);
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        // int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        // int key = 6;
        // int start = 0;
        // int end = arr.length - 1;

        // int target = binarySearch(arr, key, start, end);
        
        // if(target == -1) {
        //     System.out.println("Element in not found");
        // } else {
        //     System.out.println("Element is found at index : " + target);
        // }

        int[] sizes = {100, 200, 300, 400, 500};

        try {
            FileWriter bestWriter = new FileWriter("best_case.txt");
            FileWriter avgWriter = new FileWriter("average_case.txt");
            FileWriter worstWriter = new FileWriter("worst_case.txt");

            // Write headers to all files
            bestWriter.write("Input Size,Linear Steps,Binary Steps\n");
            avgWriter.write("Input Size,Linear Steps,Binary Steps\n");
            worstWriter.write("Input Size,Linear Steps,Binary Steps\n");

            // ---------- BEST CASE ----------
            System.out.println("\nNumber of Steps Executed (Best Case)");
            System.out.println("Inputs\tLinear Search\tBinary Search");

            for (int size : sizes) {
                int[] arr = new int[size];
                for (int i = 0; i < size; i++) arr[i] = i + 1;

                int key = arr[0]; // Best case
                linearSearch(arr, key);
                binarySteps = 0;
                binarySearch(arr, key, 0, size - 1);

                System.out.printf("%-7d%-16d%-15d\n", size, linearSteps, binarySteps);
                bestWriter.write(size + "," + linearSteps + "," + binarySteps + "\n");
            }
            // bestWriter.write("Time Complexity,O(1),O(1)\n");

            // ---------- AVERAGE CASE ----------
            System.out.println("\nNumber of Steps Executed (Average Case)");
            System.out.println("Inputs\tLinear Search\tBinary Search");

            for (int size : sizes) {
                int[] arr = new int[size];
                for (int i = 0; i < size; i++) arr[i] = i + 1;

                int key = arr[size / 2]; // Middle
                linearSearch(arr, key);
                binarySteps = 0;
                binarySearch(arr, key, 0, size - 1);

                System.out.printf("%-7d%-16d%-15d\n", size, linearSteps, binarySteps);
                avgWriter.write(size + "," + linearSteps + "," + binarySteps + "\n");
            }
            // avgWriter.write("Time Complexity,O(n),O(log n)\n");

            // ---------- WORST CASE ----------
            System.out.println("\nNumber of Steps Executed (Worst Case)");
            System.out.println("Inputs\tLinear Search\tBinary Search");

            for (int size : sizes) {
                int[] arr = new int[size];
                for (int i = 0; i < size; i++) arr[i] = i + 1;

                int key = arr[size - 1]; // Worst case
                linearSearch(arr, key);
                binarySteps = 0;
                binarySearch(arr, key, 0, size - 1);

                System.out.printf("%-7d%-16d%-15d\n", size, linearSteps, binarySteps);
                worstWriter.write(size + "," + linearSteps + "," + binarySteps + "\n");
            }
            // worstWriter.write("Time Complexity,O(n),O(log n)\n");

            // Close files
            bestWriter.close();
            avgWriter.close();
            worstWriter.close();

            // System.out.println("\nCSV data written to best_case.txt, average_case.txt, worst_case.txt successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred while writing files: " + e.getMessage());
        }
    }
}

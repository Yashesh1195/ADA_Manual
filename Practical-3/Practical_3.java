// public class Practical_3 {
//     public static int sequentialSearch(int arr[], int key) {
//         int pos = -1;

//         for(int i=0; i<arr.length; i++) {
//             if(arr[i] == key) {
//                 pos = i;
//                 break;
//             }
//         }

//         return pos;
//     }
//     public static void main(String[] args) {
//         int arr[] = {18, 12, 9, 14, 33, 16, 71, 45};
//         int key = 33;

//         int pos = sequentialSearch(arr, key);

//         if(pos == -1) {
//             System.out.println("Element not found");
//         } else {
//             System.out.println("Element is found at index : " + pos);
//         }
//     }
// }

import java.io.FileWriter;
import java.io.IOException;

public class Practical_3 {
    static int steps = 0;

    public static int sequentialSearch(int arr[], int key) {
        steps = 0;
        int pos = -1;
        for (int i = 0; i < arr.length; i++) {
            steps++;
            if (arr[i] == key) {
                pos = i;
                break;
            }
        }
        return pos;
    }
    public static void main(String[] args) {
        int[] sizes = {1000, 2000, 3000, 4000, 5000};

        try {
            FileWriter writer = new FileWriter("SequentialSearchSteps.txt");

            // Writing Header
            writer.write("Inputs"+","+"Best Case"+","+"Average Case"+","+"Worst Case\n");

            // Print Console Table Header
            System.out.printf("%-10s %-15s %-15s %-15s\n", "Inputs", "Best Case", "Average Case", "Worst Case");
            System.out.println("-------------------------------------------------------------");

            for (int size : sizes) {
                int[] arr = new int[size];
                for (int i = 0; i < size; i++) {
                    arr[i] = i + 1;
                }

                // Best Case
                sequentialSearch(arr, arr[0]);
                int best = steps;

                // Average Case
                sequentialSearch(arr, arr[size / 2]);
                int avg = steps;

                // Worst Case
                sequentialSearch(arr, arr[size - 1]);
                int worst = steps;

                writer.write(size + "," + best + "," + avg + "," + worst + ","+"\n");

                // Print to console
                System.out.printf("%-10d %-15d %-15d %-15d\n", size, best, avg, worst);
            }

            // Time Complexity row
            System.out.println("-------------------------------------------------------------");
            System.out.printf("%-10s %-15s %-15s %-15s\n", "", "O(1)", "O(n)", "O(n)");

            writer.close();
            // System.out.println("Data written to SequentialSearchSteps.txt successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
    }
}

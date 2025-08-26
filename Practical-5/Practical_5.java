import java.io.FileWriter;
import java.io.IOException;

public class Practical_5 {
    static int stepsIter = 0;
    static int stepsRec = 0;

    // Iterative Approach
    public static int fibonacci_iter(int n) {
        stepsIter = 0;
        if (n <= 0) return 0;
        if (n == 1 || n == 2) {
            stepsIter++;
            return 1;
        }

        int f_0 = 0, f_1 = 1, f_2 = 0;
        for (int i = 2; i < n; i++) {
            f_2 = f_0 + f_1;
            f_0 = f_1;
            f_1 = f_2;
            stepsIter++;
        }
        return f_1;
    }

    // Recursive Approach
    public static int fibonacci_rec(int n) {
        stepsRec++;
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacci_rec(n - 1) + fibonacci_rec(n - 2);
    }

    public static void main(String[] args) {
        String filename = "fibonacci_steps_comparison.txt";

        try (FileWriter writer = new FileWriter(filename)) {
            // Write CSV header
            writer.write("Fibonacci Number,Iterative Steps,Recursive Steps\n");

            System.out.println("Fibonacci Number | Iterative Steps | Recursive Steps");
            System.out.println("----------------------------------------------------");

            for (int n = 10; n <= 40; n += 5) {
                // Iterative
                fibonacci_iter(n);

                // Recursive
                stepsRec = 0;
                fibonacci_rec(n);

                // Console Output
                System.out.printf("%17d | %15d | %14d\n", n, stepsIter, stepsRec);

                // File Output
                writer.write(n + "," + stepsIter + "," + stepsRec + "\n");
            }

            // System.out.println("\nData successfully written to: " + filename);

        } catch (IOException e) {
            System.out.println("An error occurred while writing the file: " + e.getMessage());
        }
    }
}

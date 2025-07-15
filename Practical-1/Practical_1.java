import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Practical_1 {
    // counter variables for each function
    static int loopCount = 0;
    static int eqCount = 0;
    static int recCount = 0;

    // Sum of 1 to N num. using loop
    public static void sumLoop(int n) {
        loopCount = 0;
        if(n<=0) {
            System.out.println("Enter valid number!");
            return;
        }
        int sum = 0; 
        loopCount++;
        for(int i=1; i<=n; i++) {
            loopCount++;
            sum+=i;
            loopCount++;
        }
        loopCount++;

        // System.out.println("Sum : " + sum);
        return;
    }

    // Sum of 1 to N num. using eaquation
    public static void sumEquation(int n) {
        eqCount = 0;
        if(n<=0) {
            System.out.println("Enter valid number!");
            return;
        }
        int sum = n * (n + 1) / 2; eqCount++;
        // System.out.println("Sum : " + sum);
        return;
    }

    // Sum of 1 to N num. using recursion
    public static int sumRecursion(int n) {
        recCount++;
        if(n<=0) {
            return 0;
        }
        return n + sumRecursion(n-1);
    }
    public static void main(String[] args) throws IOException{
        // Scanner sc = new Scanner(System.in);
        // System.out.print("Enter the number n : ");
        // int n = sc.nextInt();

        // System.out.println("Sum of 1 to n numbers using loop is : ");
        // sumLoop(n);

        // System.out.println("Sum of 1 to n numbers using equation is : ");
        // sumEquation(n);
        
        // System.out.println("Sum of 1 to n numbers using recursion is : ");
        // if(n<=0) {
        //     System.out.println("Enter valid number!");
        // } else {
        //     int sum = sumRecursion(n);
        //     System.out.println("Sum : " + sum);
        // }

        // sc.close();

        int[] inputs = {100, 200, 300, 400, 500};

        FileWriter writer = new FileWriter("steps_output.txt");
        writer.write("Input,Loop,Equation,Recursion\n");

        System.out.printf("%-10s %-15s %-15s %-15s", "Input", "Loop Method", "Equations", "Recursion");
        System.out.println();

        for(int n : inputs) {
            sumLoop(n);
            sumEquation(n);
            recCount = 0;
            sumRecursion(n);

            writer.write(n + "," + loopCount + "," + eqCount + "," + recCount + "\n");

            System.out.printf("%-10d %-15d %-15d %-15d", n, loopCount, eqCount, recCount);
            System.out.println();
        }

        writer.close();
    }
}
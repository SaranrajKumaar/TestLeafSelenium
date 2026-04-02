package Week1.Day2.HomeAssignmentDay2;

import java.util.Scanner;

public class FibonacciSeries {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of terms for Fibonacci series: ");
        int range = sc.nextInt();
        sc.close();

         System.out.println("Fibonacci Series up to " + range + ":");

         //handle edge cases 
         if(range >=0) {
             System.out.print("0 ");
         }  
         if(range >=1){
            System.out.println(", 1");
         }

        // Generate Fibonacci series using for loop
        int first = 0, second = 1;
          for (int i = 2; i <= range; i++) {
            int next = first + second;
            System.out.print(", " + next);
            first = second;
            second = next;
          }
        System.out.println(); // Print new line at the end
    }


}

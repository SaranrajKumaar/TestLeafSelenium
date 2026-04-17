package Week3.Day1.HomeAssignment;

public class PalindromeNumber {
    public static void main(String[] args) {

        int input = 121;
        int output = 0;
        for (int i = input; i > 0; i = i / 10) {
            int rem = i % 10; // get last digit
            System.out.println(rem);
            output = (output * 10) + rem;
            System.out.println(output);

        }
        if (input == output) {
            System.out.println("The given number is a palindrome");
        } else {
            System.out.println("The given number is not a palindrome");
        }

    }

}

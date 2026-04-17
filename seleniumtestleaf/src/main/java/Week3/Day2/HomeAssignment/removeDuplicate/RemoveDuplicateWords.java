package Week3.Day2.HomeAssignment.removeDuplicate;


public class RemoveDuplicateWords {

    public static void main(String[] args) {

        String text = "We learn Java basics as part of java sessions in java week1";

        int count = 0; // Track duplicates

        // Step 1: Split string into words
        String[] words = text.split(" ");

        // Step 2: Nested loops to compare words
        for (int i = 0; i < words.length; i++) {

            for (int j = i + 1; j < words.length; j++) {

                // Step 3: Case-insensitive comparison
                if (words[i].equalsIgnoreCase(words[j])) {

                    words[j] = ""; // Remove duplicate
                    count++;
                }
            }
        }

        // Step 4: Print result if duplicates found
        if (count > 0) {
            System.out.print("Output: ");
            for (int i = 0; i < words.length; i++) {
                if (!words[i].equals("")) {
                    System.out.print(words[i] + " ");
                }
            }
        } else {
            System.out.println("No duplicate words found");
        }
    }
}

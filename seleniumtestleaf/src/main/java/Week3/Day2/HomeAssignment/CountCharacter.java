package Week3.Day2.HomeAssignment;

public class CountCharacter {

    public static void main(String[] args) {
        String text = "TestLeaf";
        char target = 'e';

        int count = 0;

        char[] charAr = text.toCharArray();
        System.out.println("charAr:" + charAr);

        for (int i = 0; i < charAr.length; i++) {
            if (charAr[i] == target) {
                count++;
            }
        }
        System.out.println("Count of '" + target + "' is: " + count);
    }
}

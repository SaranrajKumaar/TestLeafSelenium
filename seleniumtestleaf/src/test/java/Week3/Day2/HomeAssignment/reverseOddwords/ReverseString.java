package Week3.Day2.HomeAssignment.reverseOddwords;

public class ReverseString {
    public static void main(String[] args) {
        String companyName= "TestLeaf";
        String reversedName="";

        char[] ch = companyName.toCharArray();

          for (int i = ch.length - 1; i >= 0; i--) {
            reversedName =reversedName+ch[i];
            System.out.println(ch[i]);
        }
         System.out.println(reversedName);


    }

}

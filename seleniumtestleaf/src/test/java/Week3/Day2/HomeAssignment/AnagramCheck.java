package Week3.Day2.HomeAssignment;

import java.util.Arrays;

public class AnagramCheck {

    public static void main(String[] args) {
        String text1 = "stops";
        String text2 = "potss";

        if( text1.length() != text2.length()){
            System.out.println("Lengths mismatch, therefore the strings are not an Anagram");
        }

        char[] chararray1 = text1.toCharArray();
        char[] chararray2 = text2.toCharArray();

        //sort 
        Arrays.sort(chararray1);
        Arrays.sort(chararray2);

        if(Arrays.equals(chararray1, chararray2)){
            System.out.println("The given strings are Anagrams");
        } else {
            System.out.println("The given strings are not Anagrams");
        }

 
    }

}

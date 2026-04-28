package Week3.Day2.HomeAssignment.reverseOddwords;

public class ReverseOddWords {

    public static void main(String[] args) {
         String test = "I am a software tester";

         String[] words =test.split(" ");

         for( int i=0 ; i<words.length; i++) {
            if(i%2 !=0){

                char[] charAr = words[i].toCharArray();

                for(int j=charAr.length-1; j>=0; j--){
                    System.out.print(charAr[j]);
                }
            }else {
            System.out.println(words[i]);
         }
         System.out.println(" ");
         }
    }

}

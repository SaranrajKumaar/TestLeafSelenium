package Week1.Day2;

public class FindOddNumbers {

   public static void main(String[] args) {

        int maxRange =10;
        for (int i=1 ; i<=maxRange; i++){
            if( i%2 ==1 ){
                System.out.println("Odd number: "+i);
            }else {
                System.out.println("Even number: " + i);
            }
        }
    }

}

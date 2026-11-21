import java.util.Scanner;
public class wordStats {
    public static void main(String[] args) {
       try (Scanner input = new Scanner(System.in)) {
        System.out.print("Enter a word:");
           String word = input.nextLine();
           word = word.toLowerCase();

           int vowels = 0;
           int consonants = 0;

           for (int i = 0; i < word.length(); i++) {
            String letter = word.substring(i, i + 1);
            if(letter.equals("a") || letter.equals("e") || letter.equals("i") || letter.equals("o") || letter.equals("u")){
                vowels++;
            }
            else {
                consonants++;
            }
           }
           System.out.println("Word: " + word);
           System.out.println("Length: " + word.length());
           System.out.println("Vowels " + vowels);
           System.out.println("Consonants: " + consonants);
       }
    }

}

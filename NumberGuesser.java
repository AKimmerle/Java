import java.util.Scanner;

public class NumberGuesser {
    public static void main(String[] args) {
       try (Scanner scanner = new Scanner(System.in)) {
        int secret = (int) (Math.random() * 100) + 1;
        int guess = -1;
        int counter = 0;
        System.out.println("Please input a number between 1 to 100.");
        while (secret != guess && scanner.hasNextInt()) {
          guess = scanner.nextInt();
          counter++;
          if (guess == secret) {
            System.out.println("Correct");
          }
          else if (guess < secret) {
            System.out.println("Higher");
          }
          else {
            System.out.println("Lower");
        }
        }
        if (secret == guess) {
          System.out.println("It took " + counter + " guesses to get the correct number.");
        }
       }
   
    
}
}
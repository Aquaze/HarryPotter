import java.util.Scanner;

public class InputHandler {

    public static String getUserInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String userInput = input.nextLine();
        return userInput;
    }

    public static int getValidInput(int min, int max) {
        Scanner input = new Scanner(System.in);
        int userInput = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.printf("Enter an integer between %d and %d: ", min, max);
            try {
                userInput = Integer.parseInt(input.nextLine());
                if (userInput >= min && userInput <= max) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input! Please enter an integer between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer.");
            }
        }
        return userInput;
    }

}

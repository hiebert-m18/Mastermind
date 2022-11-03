import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/*****************************************************
 * Name: Malacai Hiebert
 * Class: CS40S
 * 
 * Assignment: Assignment 1: Mastermind
 * 
 * Description: A text-based version of Mastermind
 *****************************************************/

public class Mastermind {
    public static void main(String[] args) throws IOException {
        // ***** Constants *******

        // ***** Variables *****
        String nl = System.lineSeparator();
        int row;
        int col;
        int numValues;
        boolean quit = false;

        // ***** Objects *****

        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        Scores s = new Scores("log.txt");

        // ***** Print Banner *****

        printBanner();

        // ***** Get Input (Set up the game) *****

        System.out.println(nl + "***** Game Notes *****");
        System.out.println("1. 'O' means that the letter is in the right place.");
        System.out.println("2. '?' means that the letter is not in the right place.");
        System.out.println("3. 'Ø' means that the letter is not in the solution.");
        System.out.println("4. These symbols are not in order.");

        System.out.println(nl + "***** Game Setup *****");
        System.out.print("Length of solution (4-8): ");
        try {
            col = Integer.parseInt(scanner.next());

            if (col < 4) {
                col = 4;
            } else if (col > 8) {
                col = 8;
            } // end else if
        } catch (Exception e) {
            col = 4;
        } // end try catch

        System.out.print("Number of guesses (10-20): ");
        try {
            row = Integer.parseInt(scanner.next());

            if (row < 10) {
                row = 10;
            } else if (row > 20) {
                row = 20;
            } // end else if
        } catch (Exception e) {
            row = 10;
        } // end try catch

        System.out.print("Number of possible colour values (4-8): ");
        try {
            numValues = Integer.parseInt(scanner.next());

            if (numValues < 4) {
                numValues = 4;
            } else if (numValues > 8) {
                numValues = 8;
            } // end else if
        } catch (Exception e) {
            numValues = 4;
        } // end try catch

        // Initialize Game
        Game game = new Game(row, col, numValues);

        // full game loop
        while (quit == false) {
            // reset variables
            game.reset();

            System.out.println(nl + "***** Play *****" + nl);
            
            System.out.println("Games Played: " + s.get()[0]);
            System.out.println("Games Won: " + s.get()[1]);
            System.out.println("Highest Score: " + s.get()[2]);
            System.out.println("Average Score: " + s.get()[3] + nl);

            game.loop();

            // Game Over
            System.out.println("***** Game Over *****");

            if (game.hasWon == true) {
                System.out.println("You won!!! Score: " + game.roundNum + " guesses.");
            } else {
                System.out.println("Better luck next time! Score: " + game.roundNum + " guesses.");
            } // end else if

            String fAnswer = "";
            for (int i = 0; i < game.solution.length; i++) {
                fAnswer += game.solution[i];
            } // end for
            System.out.println("The solution was " + fAnswer + nl);

            // Update score system
            s.update(game.roundNum, game.hasWon);

            // check for play again
            System.out.print("Do you want to play again (y/n): ");
            String playAgain = scanner.next().toLowerCase().strip();
            if (playAgain.charAt(0) != 'y') {
                quit = true;
            } // end if
        } // end full game loop

        scanner.close();
    } // end main

    /***********************************************************
     * Purpose: print a banner to the console window
     * Interface: no parameters
     * Returns: no return
     ************************************************************/
    public static void printBanner() {
        System.out.println("*******************************************");
        System.out.println("Name:           Malacai Hiebert");
        System.out.println("Class:          CS40S");
        System.out.println("Assignment:     Assignment 1: Mastermind");
        System.out.println("*******************************************");
    } // end printBanner
} // end Mastermind

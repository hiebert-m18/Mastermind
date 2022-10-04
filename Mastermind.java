import java.util.Scanner;
import javax.swing.*;
import java.io.*;
import java.text.NumberFormat;
import java.util.Random;

/*****************************************************
 *  Name:           Malacai Hiebert
 *  Class:          CS40S
 * 
 *  Assignment:     Assignment 1: Mastermind
 * 
 *  Description:    A text-based version of Mastermind
 *****************************************************/

public class Mastermind {
    public static void main(String[] args) throws IOException{
        // ***** Constants *******
    
        // ***** Variables *****
    
        String nl = System.lineSeparator();
        
        // Booleans
        boolean gameOver = false;
        boolean hasWon = false;
        boolean quit = false;
        
        // Game Board
        int roundNum = 0;
        int row = 10;
        int col = 4;
        char[][] board;
        
        // Solution Check Board
        String[] solBoard;
        
        // Solution and Guess Arrays
        char[] solution;
        char[] guess;
        String g = "";
        
        // Possible Guess Values
        int numValues = 8;
        String valString = "";
        char[] allValues = {'R', 'O', 'Y', 'G', 'B', 'I', 'V', 'W'};
        
        // Temporary Holder for scores
        int[] tempScores = new int[4];
        
        // Endgame variables
        int n = 0;
    
        // ***** Objects *****
    
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
    
        // ***** Print Banner *****
    
        printBanner();
    
        // ***** Get Input (Set up the game) *****
        
        System.out.println(nl + "***** Game Setup *****");
        System.out.print("Length of solution (4-8): ");
        col = scanner.nextInt();
        
        System.out.print("Number of guesses (10-20): ");
        row = scanner.nextInt();
        
        System.out.print("Number of possible colour values (4-8): ");
        numValues = scanner.nextInt();
        
        // reset arrays to new values
        board = new char[row][col];
        solBoard = new String[row];
        solution = new char[col];
        guess = new char[col];
    
        // ***** Main Processing *****
        
        // full game loop
        
        while (quit == false) {
            // reset variables
            gameOver = false;
            hasWon = false;
            roundNum = 0;
            g = "";
            valString = "";
            solution = new char[col];
            guess = new char[col];
            
            
            System.out.println(nl + "***** Play *****" + nl);
            
            tempScores = getScores();
            System.out.println("Games Played: " + tempScores[0]);
            System.out.println("Games Won: " + tempScores[1]);
            System.out.println("Highest Score: " + tempScores[2]);
            System.out.println("Average Score: " + tempScores[3] + nl);
            
            // Fill Out Board
            fillBoard(board, '_');
            fillBoard(solBoard, "");
            
            // String of all possible colour values
            for (int i = 0; i < numValues; i++) {
                valString += allValues[i];
            }// end for
            
            // Generate Solution
            for (int i = 0; i < solution.length; i++) {
                solution[i] = allValues[rand.nextInt(numValues)];
            }// end for
            
            while (gameOver == false) {
                System.out.print("Guess a sequence (" + valString + "): ");
                g = scanner.next().toUpperCase();
                for (int i = 0; i < guess.length; i++) {
                    guess[i] = g.charAt(i);
                }// end for
                
                for (int i = 0; i < guess.length; i++) {
                    if (guess[i] == solution[i]) {
                        board[roundNum][i] = guess[i];
                    } else if (checkArray(solution, guess[i]) == true) {
                        board[roundNum][i] = guess[i];
                    } else {
                        board[roundNum][i] = guess[i];
                    }// end if else
                }// end for
                
                // check rows
                checkRows(solution, guess, solBoard, roundNum);
                
                //check for endgame
                n = 0;
                for (int i = 0; i < solution.length; i++) {
                    if (solution[i] == guess[i]) {
                        n += 1;
                    }
                }// end for
                if (n == solution.length) {
                    gameOver = true;
                    hasWon = true;
                }
                if (roundNum == (row-1)) {
                    gameOver = true;
                }// end else if
                
                roundNum += 1;
                printBoard(board, solBoard);
                System.out.println();
            }// end game loop
        
            // ***** Print Formatted Output *****
            
            System.out.println("***** Game Over *****");
            
            if (hasWon == true) {
                System.out.println("You won!!! Score: " + roundNum + "guesses.");
            } else {
                System.out.println("Better luck next time!");
            }// end else if
            
            String fAnswer = "";
            for (int i = 0; i < solution.length; i++) {
                fAnswer += solution[i];
            }// end for
            System.out.println("The solution was " + fAnswer + nl);
            
            // Update score system
            updateScores(roundNum, hasWon);
            
            // check for play again
            System.out.print("Do you want to play again (y/n): ");
            String playAgain = scanner.next().toLowerCase().strip();
            if (playAgain.charAt(0) == 'n') {
                quit = true;
            }// end if
        }// end full game loop
        
        // ADD THE OPTION FOR THE PLAYER TO QUIT OR PLAY AGAIN
        
    } // end main
    
    public static void updateScores(int score, boolean hasWon) throws IOException {
        int[] scores = getScores();
        
        // Update Games Played
        scores[0] += 1;
        
        // Update number of wins
        if (hasWon == true) {
            scores[1] += 1;
        }// end if
        
        // Check for higher scores
        if (scores[2] < score) {
            scores[2] = score;
        }// end if
        
        // update average score
        if (scores[3] == 0) {
            scores[3] = score;
        } else {
            scores[3] = ((scores[3] * (scores[0] - 1)) + score) / scores[0];
        }// end else if
        
        PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("log.txt")));
        
        // Print to file
        for (int i : scores) {
            fout.println(i);
        }// end for
        
        fout.close();
    }// end updateScores
    
    public static int[] getScores() throws IOException {
        // set up reader
        BufferedReader fin = null;
        try {
            fin = new BufferedReader(new FileReader("log.txt"));
        } catch(Exception e) {
            System.out.println("File does not exist");
        }// end try catch
        
        String strin;
        int[] scores = new int[4];
        int i = 0;
        while ((strin = fin.readLine()) != null) {
            scores[i] = Integer.parseInt(strin);
            i++;
        }// end while
        
        fin.close();
        
        return scores;
    }// end getScores
    
    public static boolean checkArray(char[] arr, char toCheckValue) {
        boolean test = false;
        for (char element : arr) {
            if (element == toCheckValue) {
                test = true;
                break;
            }// end if
        }// end for
 
        return test;
    }// end checkArray
    
    public static void checkRows(char[] a, char[] g, String[] s, int r) {
        s[r] = "";
        String rightSpot = "";
        String rightColor = "";
        String dne = "";
        for (int i = 0; i < a.length; i++) {
            if (a[i] == g[i]) {
                rightSpot += "O";
            } else if (checkArray(a, g[i])) {
                rightColor += "?";
            } else {
                dne += "Ø";
            }// end else if
        }// end for
        
        s[r] = rightSpot + rightColor + dne;
    }// end checkRows
    
    public static void fillBoard(char[][] b, char v) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                b[i][j] = v;
            }// end for j
        }// end for i
    }// end fillBoard with char
    
    public static void fillBoard(String[] s, String c) {
        for (int i = 0; i < s.length; i++) {
            s[i] = c;
        }// end for i
    }// end fillBoard with string
    
    public static void printBoard(char[][] b, String[] s) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                System.out.print(b[i][j] + " ");
            }// end for j
            System.out.print("-> " + s[i] + "\n");
        }// end for i
    }// end printBoard
    
    /***********************************************************
    *   Purpose:    print a banner to the console window
    *   Interface:  no parameters
    *   Returns:    no return
    ************************************************************/    
    public static void printBanner() {
        System.out.println("*******************************************");
        System.out.println("Name:           Malacai Hiebert");
        System.out.println("Class:          CS40S");
        System.out.println("Assignment:     Assignment 1: Mastermind");
        System.out.println("*******************************************");        
    } // end printBanner
} // end Mastermind

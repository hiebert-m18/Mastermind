import java.util.Random;
import java.util.Scanner;

/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game
{
    // Random object
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    
    private String nl = System.lineSeparator();

    // Booleans
    public boolean gameOver = false;
    public boolean hasWon = false;

    // Game Board
    public int roundNum = 0;
    int row;
    int col;
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
    char[] allValues = { 'R', 'O', 'Y', 'G', 'B', 'I', 'V', 'W' };

    // Temporary Holder for scores
    int[] tempScores = new int[4];

    // Endgame variables
    int n = 0;

    /**
     * Constructor for objects of class Game
     */
    public Game(int row, int col, int numValues)
    {
        this.row = row;
        this.col = col;
        this.numValues = numValues;
        
        this.board = new char[row][col];
        this.solBoard = new String[row];
        this.solution = new char[col];
        this.guess = new char[col];
    }
    
    public void loop() {
        while (gameOver == false) {
            System.out.print("Guess a sequence (" + valString + "): ");
            g = scanner.next().toUpperCase();
            // error catching
            if (g.length() < 4 || g.length() > 4 || checkArray(allValues, g) == false) {
                System.out.println("Try again!" + nl);
                continue;
            } // end if

            for (int i = 0; i < guess.length; i++) {
                guess[i] = g.charAt(i);
            } // end for

            for (int i = 0; i < guess.length; i++) {
                if (guess[i] == solution[i]) {
                    board[roundNum][i] = guess[i];
                } else if (checkArray(solution, guess[i]) == true) {
                    board[roundNum][i] = guess[i];
                } else {
                    board[roundNum][i] = guess[i];
                } // end if else
            } // end for

            // check rows
            checkRows(solution, guess, solBoard, roundNum);

            // check for endgame
            n = 0;
            for (int i = 0; i < solution.length; i++) {
                if (solution[i] == guess[i]) {
                    n += 1;
                }
            } // end for
            if (n == solution.length) {
                gameOver = true;
                hasWon = true;
            }
            if (roundNum == (row - 1)) {
                gameOver = true;
            } // end else if

            roundNum += 1;
            printBoard(board, solBoard);
            System.out.println();
        } // end game loop
    }
    
    public void reset() {
        gameOver = false;
        hasWon = false;
        roundNum = 0;
        g = "";
        valString = "";
        solution = new char[col];
        guess = new char[col];
        
        this.fillBoard(board, '_');
        this.fillBoard(solBoard, "");
        
        // String of all possible colour values
        for (int i = 0; i < numValues; i++) {
            valString += allValues[i];
        } // end for

        // Generate Solution
        for (int i = 0; i < solution.length; i++) {
            solution[i] = allValues[rand.nextInt(numValues)];
        } // end for
    }
    
    /***********************************************************
     * Purpose: fill out the 2d board array with chars
     * Interface: 2d board array, char value
     * Returns: no return
     ************************************************************/
    public static void fillBoard(char[][] b, char v) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                b[i][j] = v;
            } // end for j
        } // end for i
    }// end fillBoard with char

    /***********************************************************
     * Purpose: fill out the solution checking board array with strings
     * Interface: solution checking board array, string value
     * Returns: no return
     ************************************************************/
    public static void fillBoard(String[] s, String c) {
        for (int i = 0; i < s.length; i++) {
            s[i] = c;
        } // end for i
    }// end fillBoard with string
    
    /***********************************************************
     * Purpose: check array for items in a string
     * Interface: array, string value
     * Returns: boolean
     ************************************************************/
    public static boolean checkArray(char[] arr, String value) {
        boolean test = false;
        int n = 0;
        for (int i = 0; i < value.length(); i++) {
            for (int j = 0; j < arr.length; j++) {
                if (value.charAt(i) == arr[j]) {
                    n++;
                } // end if
            } // end for
        } // end for

        if (n == 4) {
            test = true;
        } // end if

        return test;
    }// end checkArray

    /***********************************************************
     * Purpose: check array for a character
     * Interface: array, char value
     * Returns: boolean
     ************************************************************/
    public static boolean checkArray(char[] arr, char toCheckValue) {
        boolean test = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == toCheckValue) {
                test = true;
                break;
            } // end if
        } // end for

        return test;
    }// end checkArray

    /***********************************************************
     * Purpose: solution checking function
     * Interface: solution array, guess array, solution checking array, roundNum
     * Returns: no return
     ************************************************************/
    public static void checkRows(char[] a, char[] g, String[] s, int r) {
        s[r] = "";
        String[] hint = { "Ø", "Ø", "Ø", "Ø" };
        int index = 0;

        // make temporary guess and answer arrays
        char[] aTemp = new char[4];
        for (int i = 0; i < a.length; i++) {
            aTemp[i] = a[i];
        } // end for
        char[] gTemp = new char[4];
        for (int i = 0; i < g.length; i++) {
            gTemp[i] = g[i];
        } // end for

        // Check for exact
        for (int i = 0; i < aTemp.length; i++) {
            if (aTemp[i] == gTemp[i]) {
                hint[index++] = "O";
                aTemp[i] = '0';
                gTemp[i] = '1';
            }
        }
        // Check for correct
        for (int i = 0; i < aTemp.length; i++) {
            if (checkArray(aTemp, gTemp[i])) {
                hint[index++] = "?";
                gTemp[i] = '0';
            }
        }

        // add hint string to the hint array
        for (String i : hint) {
            s[r] += i;
        }
    }// end checkRows

    /***********************************************************
     * Purpose: print the game board to the console window
     * Interface: 2d board array, solution checking board array
     * Returns: no return
     ************************************************************/
    public static void printBoard(char[][] b, String[] s) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                System.out.print(b[i][j] + " ");
            } // end for j
            System.out.print("-> " + s[i] + "\n");
        } // end for i
    }// end printBoard
}

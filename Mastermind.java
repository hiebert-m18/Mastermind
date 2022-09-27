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
        
        // Game Board
        int roundNum = 0;
        int row = 10;
        int col = 4;
        char[][] board = new char[row][col];
        
        // Solution Check Board
        String[] solBoard = new String[row];
        
        // Solution and Guess Arrays
        char[] solution = new char[col];
        char[] guess = new char[col];
        
        // Possible Guess Values
        int numValues = 8;
        char[] allValues = {'R', 'O', 'Y', 'G', 'B', 'I', 'V', 'W'};
    
        // ***** Objects *****
    
        //Scanner scanner = new Scanner(System.in);
        //NumberFormat currency = NumberFormat.getCurrencyInstance();
        
        //BufferedReader fin = new BufferedReader(new FileReader("filename.txt"));
        //PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("outfle.txt")));
        
        Random rand = new Random();
    
        // ***** Print Banner *****
    
        printBanner();
    
        // ***** Get Input *****
    
        // ***** Main Processing *****
        
        // Fill Out Board
        fillBoard(board, '_');
        
        // Generate Solution
        for (int i = 0; i < solution.length; i++) {
            solution[i] = allValues[rand.nextInt(allValues.length)];
        }// end for
    
        // ***** Print Formatted Output *****
        
        printBoard(board, solBoard);
        
        // ***** Close IO Buffers *****
    
        //fin.close();
        //fout.close();
        
    } // end main
    
    static boolean checkArray(char[] arr, char toCheckValue) {
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
            System.out.print(s[i] + "\n");
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

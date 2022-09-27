import java.util.Scanner;
import javax.swing.*;
import java.io.*;
import java.text.NumberFormat;

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
    
        // ***** Objects *****
    
        //Scanner scanner = new Scanner(System.in);
        //NumberFormat currency = NumberFormat.getCurrencyInstance();
        
        //BufferedReader fin = new BufferedReader(new FileReader("filename.txt"));
        //PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("outfle.txt")));
    
        // ***** Print Banner *****
    
        printBanner();
    
        // ***** Get Input *****
    
        // ***** Main Processing *****
    
        // ***** Print Formatted Output *****
        
        // ***** Close IO Buffers *****
    
        //fin.close();
        //fout.close();
        
    } // end main
    
    /***********************************************************
    *   Purpose:    print a banner to the console window
    *   Interface:  no parameters
    *   Returns:    no return
    ************************************************************/    
    public static void printBanner(){
        System.out.println("*******************************************");
        System.out.println("Name:           Malacai Hiebert");
        System.out.println("Class:          CS40S");
        System.out.println("Assignment:     Assignment 1: Mastermind");
        System.out.println("*******************************************");        
    } // end print banner
} // end Mastermind

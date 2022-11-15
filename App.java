import java.util.Scanner;
import javax.swing.*;
import java.io.*;
import java.text.NumberFormat;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

/** ***************************************************
 *  Name:           Hiebert
 *  Class:          CS40S
 * 
 *  Assignment:     Ax Qy
 * 
 *  Description:    Place a short description of your program here
 * 
 *************************************************************/

public class App {

    public static void main(String[] args) throws IOException{
        // ***** constants *******

        // ***** variables *****

        String banner = "";             // output banner
        String prompt = "";             // prompt for user input

        String strin = "";              // string fro keyboard input
        String strout = "";             // string for formatted output

        String delim = "[ ]+";          // delimiter for splitting input records
        String[] tokens = null;         // used to split input records

        // a new line character that works on every computer system
        String nl = System.lineSeparator();

        // ***** objects *****

        //Scanner scanner = new Scanner(System.in);
        //NumberFormat currency = NumberFormat.getCurrencyInstance();

        // file io buffers for reading and writing to text files

        //BufferedReader fin = new BufferedReader(new FileReader("filename.txt"));
        //PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("outfle.txt")));

        // ***** print banners *****

        banner = "*****************************" + nl;
        banner += "Name:        Hiebert" + nl;
        banner += "Class:       CS40S" + nl;
        banner += "Assignment:  Ax Qy" + nl;
        banner += "*****************************" + nl + nl;

        System.out.println(banner);
        //fout.print(banner);

        // ***** Get Input *****

        // prompt for input
        // read input from keyboard
        // echo input back to console window

        // ***** Main Processing *****
        JFrame frame = new JFrame("Mastermind");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 800);
        frame.setResizable(false);
        GridLayout mainGrid = new GridLayout(2, 1);
        frame.setLayout(mainGrid);

        int i = 10;
        int j = 4;
        JPanel[][] panelHolder = new JPanel[i][j];
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(i, j));
        GridLayout fillGrid = new GridLayout(1, 1);

        for(int m = 0; m < i; m++) {
            for(int n = 0; n < j; n++) {
                panelHolder[m][n] = new JPanel();
                panelHolder[m][n].setLayout(fillGrid);
                JButton button = new JButton("Blue");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("you clicked me!");
                    }
                });
                button.setForeground(Color.RED);
                button.setOpaque(true);
                panelHolder[m][n].add(button);
                gridPanel.add(panelHolder[m][n]);
            }
        }

        JPanel control = new JPanel();
        control.add(new Button("Click"));

        frame.add(gridPanel);
        frame.add(control);

        // ***** Print Formatted Output *****

        // ***** Closing Message *****

        System.out.println();
        System.out.println("end of processing");
        //fout.println("End of Processing");

        // **** close io buffers *****

        //fin.close();
        //fout.close();
    } // end main
} // end FormatTemplate

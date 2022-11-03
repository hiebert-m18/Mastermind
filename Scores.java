import java.io.*;

/**
 * Write a description of class Scores here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Scores
{
    // instance variables - replace the example below with your own
    private String fileName = "";

    /**
     * Constructor for objects of class Scores
     */
    public Scores(String f)
    {
        // initialise instance variables
        this.fileName = f;
    }

    /***********************************************************
     * Purpose: update the scores in the log.txt file
     * Interface: score, hasWon
     * Returns: no return
     ************************************************************/
    public void update(int score, boolean hasWon) throws IOException {
        int[] scores = this.get();

        // Update Games Played
        scores[0] += 1;

        // Update number of wins
        if (hasWon == true) {
            scores[1] += 1;
        } // end if

        // Check for higher scores
        if (scores[2] > score || scores[2] == 0) {
            scores[2] = score;
        } // end if

        // update average score
        if (scores[3] == 0) {
            scores[3] = score;
        } else {
            scores[3] = ((scores[3] * (scores[0] - 1)) + score) / scores[0];
        } // end else if

        PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter(this.fileName)));

        // Print to file
        for (int i : scores) {
            fout.println(i);
        } // end for

        fout.close();
    }// end updateScores

    /***********************************************************
     * Purpose: get score values from the log.txt file
     * Interface: no parameters
     * Returns: scores in an array
     ************************************************************/
    public int[] get() throws IOException {
        // set up reader
        BufferedReader fin = null;
        try {
            fin = new BufferedReader(new FileReader(this.fileName));
        } catch (Exception e) {
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter(this.fileName)));
            fout.close();
            fin = new BufferedReader(new FileReader(this.fileName));
        } // end try catch

        String strin;
        int[] scores = new int[4];
        int i = 0;
        while ((strin = fin.readLine()) != null) {
            try {
                scores[i] = Integer.parseInt(strin);
            } catch (Exception e) {
                scores[i] = 0;
            } // end try catch
            i++;
        } // end while

        fin.close();

        return scores;
    }// end getScores
}

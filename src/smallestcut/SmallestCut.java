package smallestcut;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmallestCut {

    public static void main(String[] args) {
        int[][] intArray;
        try {
            intArray = FileArrayProvider.readLines("C:/kargerAdj.txt");
        } 
        catch (IOException ex) {
            Logger.getLogger(SmallestCut.class.getName()).log(Level.SEVERE, null, ex);
            intArray = null;
        }
        int x = minCut.calcMinCut(intArray);
        System.out.println(x);     
    }
}

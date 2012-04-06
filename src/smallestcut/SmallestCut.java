/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smallestcut;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davidson_b
 */
public class SmallestCut {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        int[][] intArray;
//        try {
//            intArray = FileArrayProvider.readLines("C:/kargerAdj.txt");
//
//        } catch (IOException ex) {
//            Logger.getLogger(SmallestCut.class.getName()).log(Level.SEVERE, null, ex);
//            intArray = null;
//        }
        int[][] intArray  = {
            {-1,1,1,1},
            {1,-1,0,1},
            {1,0,-1,1},
            {1,1,1,-1}
        };
        int x = minCut.calcMinCut(intArray);
        System.out.println(x);
        
        
        
    }
}

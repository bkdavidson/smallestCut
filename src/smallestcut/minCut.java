/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smallestcut;

/**
 *
 * @author davidson_b
 */
public class minCut {
    public static int calcMinCut(int[][] arraylist){
        int answer = -1;
        
        if (arraylist.length >= 2){
            for(int i = arraylist.length; i < (java.lang.Math.pow(arraylist.length,2)); i++){
                graphCutter aGraphCutter = new graphCutter(arraylist);
                aGraphCutter.randomContraction();
                if (answer == -1 || answer > aGraphCutter.mincut())
                    answer = aGraphCutter.mincut();
            }

           
        }
         return java.lang.Math.max(answer,0);
}
}

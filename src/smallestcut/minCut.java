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
        int answer = -1; // needed for test below
        
        if (arraylist.length >= 2){ 
            for(int i = arraylist.length; i < (java.lang.Math.log(arraylist.length)*java.lang.Math.pow(arraylist.length*arraylist.length,2)); i++){
                // run this bad boy log(n)*n^2 times, remember n = edge count, so n = arraylistLength squared as worst case
                graphCutter aGraphCutter = new graphCutter(arraylist);
                aGraphCutter.randomContraction();
                if (answer == -1 || answer > aGraphCutter.mincut()) //retain the smallest calculated mincut
                    answer = aGraphCutter.mincut();
            }

           
        }
         return java.lang.Math.max(answer,0); //in case arraylist is size 1, then it has zero min cut
}
}

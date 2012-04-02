/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smallestcut;

import java.util.*;

/**
 *
 * @author davidson_b
 */
public class graphCutter {
    ArrayList<ArrayList<Integer>> arraylist;
    // designed for only a 40 by 40 list
    
    public graphCutter(int[][] arraylist){
        for (int i = 0; i < arraylist.length; i++){
            for(int j = 0; j < arraylist[i].length; j++){
                ArrayList<Integer> alist = new ArrayList<>();
                alist.add(arraylist[i][j]);
                this.arraylist.add(alist);
            }

        }
    }
    
//TODO: Recursive cutting function
    
// TODO: property to count min cut

    private void mergeNode(int a, int b){
        for (int i = 0; i < arraylist.get(a).size(); i++){
            int avalue = arraylist.get(a).get(i);
            int bvalue =  arraylist.get(b).get(i);
            avalue = java.lang.Math.max(avalue, 0);
            bvalue = java.lang.Math.max(bvalue, 0);
            int newvalue = avalue + bvalue;
            arraylist.get(a).set(i, newvalue);       
        }
        arraylist.get(a).set(a,-1);
        arraylist.get(a).set(b,0);
        arraylist.remove(b);
        Iterator<ArrayList<Integer>> itr = arraylist.iterator();
        while (itr.hasNext()){
            ArrayList<Integer> workit = itr.next();
            workit.set(a, workit.get(b)+workit.get(a));
            workit.remove(b);
        }
    }
        
}
    
    


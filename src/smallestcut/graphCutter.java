/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smallestcut;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author davidson_b
 */
public class graphCutter {
    ArrayList<ArrayList<Integer>> arraylist;
    private Random generator = new Random();
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
    
    public void randomContraction(){
        int a = 0;
        int b;
        if (arraylist.size() > 2){
        // base case
        }
        else
            a = generator.nextInt(arraylist.size()-1);
            b = a;
            while (a == b && (a!=b && !edgeExists(a,b))){
                b = generator.nextInt(arraylist.size()-1);
            }
            mergeNode(a,b);
            randomContraction();
    }
    
    public int mincut(){
        if (arraylist.size() > 2 || arraylist.size() == 0){
            return -1;
        }
        else
            return arraylist.get(0).get(0);
    }


    private boolean edgeExists(int a, int b){
        if (a < arraylist.size() || b < arraylist.size())
            return false;

        else if (arraylist.get(a).get(b) > 0)
            return true;
        else
    return false;
    }
    
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
    
    


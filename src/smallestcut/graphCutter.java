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
        this.arraylist = new ArrayList<>();
        for (int i = 0; i < arraylist.length; i++){
            ArrayList<Integer> alist = new ArrayList<>();
            for(int j = 0; j < arraylist[i].length; j++)
                alist.add(arraylist[i][j]);
            this.arraylist.add(alist);
        }
    }
    
    public void randomContraction(){
        int a = 0;
        int b = 0;
        boolean flag = true;
        if(arraylist.size() > 2){
            a = generator.nextInt(arraylist.size()-1);
            b = Integer.valueOf(a);
            while ((flag || a == b)){
                flag = false;
                b = generator.nextInt(arraylist.size()-1);
            }
            mergeNode(a,b);
            randomContraction();
        }
    }
    
    public int mincut(){
        if (arraylist.size() > 2 || arraylist.isEmpty()){
            return -1;
        }
        else
            return arraylist.get(0).get(1);
    }


    private boolean edgeExists(int a, int b){
        if (arraylist.get(a).get(b) > 0)
            return true;
        else
            return false;
    }
    
    private void mergeNode(int a, int b){
        for (int i = 0; i < arraylist.get(a).size(); i++){
            int avalue = arraylist.get(a).get(i);
            int bvalue =  arraylist.get(b).get(i);
            if (avalue  == -1 || bvalue == -1)
                avalue = bvalue = 0;
            avalue = java.lang.Math.max(avalue, 0);
            bvalue = java.lang.Math.max(bvalue, 0);
            int newvalue = avalue + bvalue;
            arraylist.get(a).set(i, newvalue);       
        }
        arraylist.get(a).set(a,-1);
        arraylist.get(a).set(b,0);
        Iterator<ArrayList<Integer>> itr = arraylist.iterator();
        while (itr.hasNext()){ //remove b from all nodes
            ArrayList<Integer> workit = itr.next();
            //workit.set(a, workit.get(b)+workit.get(a));
            workit.remove(b);    
        }
        arraylist.remove(b);
    }
        
}
    
    


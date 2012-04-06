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
        this.arraylist = new ArrayList<>(); //initialize
        for (int i = 0; i < arraylist.length; i++){ //copy the array into arraylist
            ArrayList<Integer> alist = new ArrayList<>(); //initialize inner arraylist to go inside outer arraylist
            for(int j = 0; j < arraylist[i].length; j++)
                alist.add(arraylist[i][j]); //populate with values
            this.arraylist.add(alist); //tag it onto outer arraylist
        }
    }
    
    public void randomContraction(){
        int a = 0;
        int b = 0;
        boolean flag = true; // used for while loop
        if(arraylist.size() > 2){ 
            a = generator.nextInt(arraylist.size()-1); //randon number for a
            b = Integer.valueOf(a); //initialize b
            while ((flag || !edgeExists(a,b))){ //always run at least once (for flag), and keep running if a and b share an edge
                flag = false;
                b = generator.nextInt(arraylist.size()-1);
            }
            mergeNode(a,b); //merge A and B since there are different (while loop) and share an edge
            randomContraction(); //recursively run me
        }
    }
    
    public int mincut(){
        if (arraylist.size() > 2 || arraylist.isEmpty()){
            return -1; // there can't be a min cut unless you have 2 nodes
        }
        else
            return arraylist.get(0).get(1); // the number of edges referring to node at position 1
    }


    private boolean edgeExists(int a, int b){
        if (arraylist.get(a).get(b) > 0) // if they have an edge
            return true;
        else //otherwise you don't have an edge
            return false;
    }
    
    private void mergeNode(int a, int b){ //merges a and b in place at A
        for (int i = 0; i < arraylist.get(a).size(); i++){
            int avalue = arraylist.get(a).get(i); // number of edges to node i in a
            int bvalue =  arraylist.get(b).get(i); // as above, but in b
            if (avalue  == -1 || bvalue == -1) // if node references self
                avalue = bvalue = 0; // set to zero to remove self-referencing edge
            int newvalue = avalue + bvalue; //sum values to account for parrallel edges
            arraylist.get(a).set(i, newvalue); // set the number of edges in 'A' to 'i' as the number of edges
            arraylist.get(i).set(a,newvalue); // set the number of edges in 'I' to 'a'as the number of edges
        }
        arraylist.get(a).set(a,-1); // self reference is -1
        Iterator<ArrayList<Integer>> itr = arraylist.iterator();
        while (itr.hasNext()){ //remove b from all nodes
            ArrayList<Integer> workit = itr.next();
            workit.remove(b);    // removal of references to b from each arraylist in this.arraylist
        }
        arraylist.remove(b); // remove of the arraylist for b
    }
        
}
    
    


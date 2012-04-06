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
    private ArrayList<ArrayList<Integer>> arraylist;
    private ArrayList<edge> edges;
    private Random generator = new Random();
    // designed for only a 40 by 40 list
    
    public graphCutter(int[][] arraylist){
        this.arraylist = new ArrayList<>(); //initialize
        edges = new ArrayList<>();
        for (int i = 0; i < arraylist.length; i++){ //copy the array into arraylist
            ArrayList<Integer> alist = new ArrayList<>(); //initialize inner arraylist to go inside outer arraylist
            for(int j = 0; j < arraylist[i].length; j++){
                alist.add(arraylist[i][j]); //populate with values
            }                
            this.arraylist.add(alist); //tag it onto outer arraylist
        }
        Iterator<ArrayList<Integer>> itr = this.arraylist.iterator();
                while (itr.hasNext()){ //iterate through the arraylists (inner ones)
                    ArrayList<Integer> temp = itr.next();
                    for (int k = 0; k < temp.size(); k ++){//iterate through the integer values inside each arraylist
                        if (temp.get(k) > 0) // if there is an edge, make an edge!
                            addEdge(temp,this.arraylist.get(k));
                    }
                }
    }
    
    private void addEdge(ArrayList<Integer> a, ArrayList<Integer> b){
        if(this.arraylist.indexOf(a)<this.arraylist.indexOf(b) ) //order by index
            if (!edgeExists(a,b))
                edges.add(new edge(a,b));
        else //make sure the smaller index is first in edge constructor
            if (!edgeExists(b,a))
            edges.add(new edge(b,a));
    }
     
    
    public void randomContraction(){
        int a = 0;
        int b = 0;
        if(arraylist.size() > 2){ 
            edge randomEdge = edges.get(generator.nextInt(edges.size())); //random edge picker
            mergeNode(randomEdge.a,randomEdge.b); //merge A and B since there are different (while loop) and share an edge
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


    private boolean edgeExists(ArrayList<Integer> a, ArrayList<Integer> b){
        boolean answer = false;
        Iterator<edge> itr = edges.iterator();
                while(itr.hasNext()){
                    edge anEdge = itr.next();
                    if ((anEdge.a.equals(a) && anEdge.b.equals(b)) || anEdge.a.equals(b) && anEdge.b.equals(a) )
                        answer = true;
                }
        return answer;
    }
    private edge getEdge(ArrayList<Integer> a, ArrayList<Integer> b){
        edge answer = null;
        if (edgeExists(a,b)){ //proper order is reason for min and max
                    Iterator<edge> itr = edges.iterator();
                        while(itr.hasNext()){
                            edge anEdge = itr.next();
                            if ((anEdge.a == a && anEdge.b == b)||(anEdge.a == b && anEdge.b == a))
                                answer = anEdge;
                }
        }
        return answer;                        
    }
    
    private void mergeNode(ArrayList<Integer> a, ArrayList<Integer> b){ //merges a and b in place at A
        edges.remove(getEdge(a,b)); //remove the self reference
        Iterator<edge> itr2 = edges.iterator();
            while(itr2.hasNext()){ //iterate through all edges and remove references to b, replacing them with a
                edge anEdge = itr2.next();
                if (anEdge.a == b ) // if node b is in edge spot 1
                    anEdge.a = a; //replace reference to b with a reference to a
                else if (anEdge.b == b ) //if node b is in edge spot 2
                    anEdge.b = a; //replace reference to b with a reference to a                        
            } 
            
        for (int i = 0; i < a.size(); i++){
            int avalue = a.get(i); // number of edges to node i in a
            int bvalue =  b.get(i); // as above, but in b
            if (avalue  == -1 || bvalue == -1) // if node references self
                avalue = bvalue = 0; // set to zero to remove self-referencing edge
            int newvalue = avalue + bvalue; //sum values to account for parrallel edges
            a.set(i, newvalue); // set the number of edges in 'A' to 'i' as the number of edges
            arraylist.get(i).set(this.arraylist.indexOf(a),newvalue); // set the number of edges in 'I' to 'a'as the number of edges
        }
        a.set(this.arraylist.indexOf(a),-1); // self reference is -1
        Iterator<ArrayList<Integer>> itr = arraylist.iterator();
        while (itr.hasNext()){ //remove b from all nodes
            ArrayList<Integer> workit = itr.next();
            workit.remove(this.arraylist.indexOf(b));    // removal of references to b from each arraylist in this.arraylist
        }
        arraylist.remove(b); // remove of the arraylist for 
   
    }
        

    private class edge{
        public ArrayList<Integer> a;
        public ArrayList<Integer> b;
        //public int count;
        
        public edge(ArrayList<Integer> a, ArrayList<Integer> b){
            this.a = a;
            this.b = b;
            //count = 1;
        }
    }
}
    
    


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smallestcut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileArrayProvider {

    public static int[][] readLines(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        int[][] intArray = new int[40][40];
        
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String stringline = null;
            int i = 0;
            int k = 0;
            while ((stringline = bufferedReader.readLine()) != null) {
                boolean amIN = false;
                int exp = 0; // exponent;
                int temp = 0;
                for (int j = 0; j < stringline.length(); j++){
                    if (amIN == false && Character.isWhitespace(stringline.charAt(j))){}
                    else if (amIN == false && (!Character.isWhitespace(stringline.charAt(j)) || j == stringline.length())){
                        amIN = true;
                        temp = Character.getNumericValue(stringline.charAt(j));
                        exp++;
                        i++; 
                        if (j == stringline.length()-1){
                            if (temp!= 0){
                                    intArray[k][temp-1] = 1;
                                    intArray[k][k] = -1;
                            }  
                        }
                    }
                    else if (amIN  && !Character.isWhitespace(stringline.charAt(j))){
                        temp = Character.getNumericValue(stringline.charAt(j)) + temp *(int) java.lang.Math.pow(10,exp);
                        exp++;
                        i++;
                        if (j+1 == stringline.length()){
                            if (temp!= 0){
                                    intArray[k][temp-1] = 1;
                                    intArray[k][k] = -1;
                            }  
                        }
                    }
                    else if (amIN  && Character.isWhitespace(stringline.charAt(j))){
                        exp = 0;
                        if (temp!= 0){
                                intArray[k][temp-1] = 1;
                                intArray[k][k] = -1;
                        }
                        temp = 0;
                        amIN = false;
                    }
                    else if(j == stringline.length() &&  amIN){
                        
                    }
                }
                k++;
            }
        }
        return intArray;
    }
}
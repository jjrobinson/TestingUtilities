/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursivealgorithmtest;


import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Robinso3
 */
public class RecursiveAlgorithmTest {
    private static int recursiveCallsCounter = 0;
    public static ArrayList<ArrayList<String>> testCases = new ArrayList<ArrayList<String>>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //set values for ArrayOfArrays
        ArrayList<String> VariableA = new ArrayList<String>(Arrays.asList("red", "green"));
        ArrayList<String> VariableB = new ArrayList<String>(Arrays.asList("A", "B", "C"));
        ArrayList<String> VariableC = new ArrayList<String>(Arrays.asList("1", "2", "3", "4"));
        
        ArrayList<ArrayList<String>> AofA = new ArrayList<ArrayList<String>>();
        AofA.add(VariableA); AofA.add(VariableB); AofA.add(VariableC);
        
        System.out.println("Array of Arrays: ToString(): " +AofA.toString());
        
        ArrayList<String> optionsList = new ArrayList<String>();

        //recursive call
        recurse(optionsList, AofA, 0);
        
        for (int i = 0 ; i < testCases.size() ; i++) {
            System.out.println("Test Case " + (i+1) + ": " + testCases.get(i));
            }
        
        }//end main(String args[])
        
    

    private static void recurse(ArrayList<String> newOptionsList, 
        ArrayList<ArrayList<String>> newAofA, int placeHolder){
        recursiveCallsCounter++;

        //check to see if we are at the end of all TestAspects
        if(placeHolder < newAofA.size()){
            
            //remove the first item in the ArrayOfArrays
            ArrayList<String> currentAspectsOptions = newAofA.get(placeHolder);
            //iterate through the popped off options
            
            for (int i=0 ; i<currentAspectsOptions.size();i++){
                ArrayList<String> newOptions = new ArrayList<String>();
                //add all the passed in options to the new object to pass on
                for (int j=0 ; j < newOptionsList.size();j++) {
                    newOptions.add(newOptionsList.get(j));
                }
                
                newOptions.add(currentAspectsOptions.get(i));
                int newPlaceHolder = placeHolder + 1;
                recurse(newOptions,newAofA, newPlaceHolder);
            }
        } else { // no more arrays to pop off
            ArrayList<String> newTestCase = new ArrayList<String>();
            for (int i=0; i < newOptionsList.size();i++){
                newTestCase.add(newOptionsList.get(i));
            }
            testCases.add(newTestCase);
        }
    }//end recursive helper 
}// end of test class

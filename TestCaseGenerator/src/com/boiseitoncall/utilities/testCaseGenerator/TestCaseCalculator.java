/*
 * 
 * 
 */
package com.boiseitoncall.utilities.testCaseGenerator;

import com.boiseitoncall.utilities.testCaseGenerator.models.TestAspect;
import com.boiseitoncall.utilities.testCaseGenerator.models.TestCase;
import com.boiseitoncall.utilities.testCaseGenerator.models.TestOptionGroup;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JasonR
 */
public class TestCaseCalculator {
    private ArrayList<ArrayList<?>> aspectsList;
    private int recursiveCallsCounter;
    private int numAllTestCases;
    private int numSmartTestCases;
    private ArrayList<TestCase> allTestCases;
    private ArrayList<TestCase> smartTestCases;


    
    /**
     * Default constructor
     */
    public TestCaseCalculator(){
        this.aspectsList = new ArrayList<ArrayList<?>>();
        this.recursiveCallsCounter = 0;
        this.numAllTestCases = 0;
        this.numSmartTestCases = 0;
        this.allTestCases = new ArrayList<TestCase>();
        this.smartTestCases = new ArrayList<TestCase>();
    }
    

    public TestCaseCalculator(ArrayList<ArrayList<?>> newTestAspects){
        this.aspectsList = newTestAspects;
        this.allTestCases = new ArrayList<TestCase>();
        this.smartTestCases = new ArrayList<TestCase>();
        this.computeAllTestCases();
        this.computeSmartTestCases();
    }
    
    
    
    
    
    
    
    
    
/////////////////////////////////////////////////////////////////////////////
/////  P R I V A T E   M E T H O D S   B E L O W  ///////////////////////////
/////////////////////////////////////////////////////////////////////////////

    /**
     * Adds a new TestCase to the AllTestCases list.
     * @param newTestCase 
     */
    private void addToAllTestCase(TestCase newTestCase){
        this.allTestCases.add(newTestCase);
    }
    
    /**
     * Adds a new TestCase to the SmartTestCases list.
     * @param newTestCase 
     */
    private void addToSmartTestCase(TestCase newTestCase){
        this.smartTestCases.add(newTestCase);
    }
    
    
    
    
    
    
    
    
    
    
    /**
     * Computes the unique combinations 
     */
    private void computeAllTestCases() {
        //System.out.println("DEBUG: Starting computeAllTestCases.");
        
        ArrayList<String> optionsList = new ArrayList<String>();
        ArrayList<ArrayList<String>> arrayOfArrays = buildArrayOfArrays();
        
        this.recursiveCallsCounter = 0;
        recurseIgnoringGroups(optionsList, arrayOfArrays, 0);
    
        //System.out.println("Total Test Cases: " + this.getNumberOfAllTestCases());
        //System.out.println("DEBUG: ENDING computeAllTestCases.\n\n");
        this.numAllTestCases = this.allTestCases.size();
    }
    
    /**
     * Computes the Smart Test Case list making use of TestOptionGroups
     */
    private void computeSmartTestCases(){
        ArrayList<String> smartOptionsList = new ArrayList<String>();
        ArrayList<ArrayList<String>> smartArrayOfArrays = buildArrayOfArrays();
        
        recursiveCallsCounter = 0;
        recurseWithGroups(smartOptionsList, smartArrayOfArrays, 0);
    
        //System.out.println("Total Test Cases: " + this.getNumberOfAllTestCases());
        //System.out.println("DEBUG: ENDING computeAllTestCases.\n\n");
        this.numSmartTestCases = this.smartTestCases.size();
    }
    
    
    

    /**
     * Recursive function to traverse an Array of Arrays.
     * 
     * @param newOptionsList
     * @param newAofA
     * @param placeHolder 
     */
    private void recurseIgnoringGroups(ArrayList<String> newOptionsList, 
        ArrayList<ArrayList<String>> newAofA, int placeHolder){
        
        //check to see if we are at the end of the AofA
        if(placeHolder < newAofA.size()) {
            //Get the next item in the ArrayOfArrays
            ArrayList<String> currentAspectsOptions = newAofA.get(placeHolder);
            
            //iterate through the new TestAspect's Options
            for(int i = 0 ; i < currentAspectsOptions.size();i++) {
                //new Options List
                ArrayList<String> newOptions = new ArrayList<String>();
                
                //iterate through and store a copy of the newOptionsList
                for (int j=0 ; j < newOptionsList.size();j++) {
                    newOptions.add(newOptionsList.get(j));
                }
                newOptions.add(currentAspectsOptions.get(i));
                int newPlaceHolder = placeHolder + 1;
                recurseIgnoringGroups(newOptions,newAofA, newPlaceHolder);
            }
        } else { // no more arrays to pop off
            
            TestCase tc = new TestCase();
            for (int i=0; i < newOptionsList.size();i++){
                tc.addTestOption(newOptionsList.get(i));
                }
            this.addToAllTestCase(tc);
        }
        
    }//end recursive helper 

    
    /**
     * Recursive function to traverse Smart groups
     * 
     * @param newOptionsList
     * @param newAofA
     * @param placeHolder 
     */
    private void recurseWithGroups(ArrayList<String> newOptionsList, 
        ArrayList<ArrayList<String>> newAofA, int placeHolder){
        
        //check to see if we are at the end of the AofA
        if(placeHolder < newAofA.size()) {
            //Get the next item in the ArrayOfArrays
            ArrayList<String> currentAspectsOptions = newAofA.get(placeHolder);
            
            //iterate through the new TestAspect's Options
            for(int i = 0 ; i < currentAspectsOptions.size();i++) {
                //new Options List
                ArrayList<String> newOptions = new ArrayList<String>();
                
                //iterate through and store a copy of the newOptionsList
                for (int j=0 ; j < newOptionsList.size();j++) {
                    newOptions.add(newOptionsList.get(j));
                }
                newOptions.add(currentAspectsOptions.get(i));
                int newPlaceHolder = placeHolder + 1;
                recurseIgnoringGroups(newOptions,newAofA, newPlaceHolder);
            }
        } else { // no more arrays to pop off
            
            TestCase tc = new TestCase();
            for (int i=0; i < newOptionsList.size();i++){
                tc.addTestOption(newOptionsList.get(i));
                }
            this.addToAllTestCase(tc);
        }
        
    }//end recursive helper 


    /**
     * Builds a  simple array of Arrays
     * 
     * @return ArrayList of ArrayList of Strings
     */
    private ArrayList<ArrayList<String>> buildArrayOfArrays(){
        //build the simple list of lists
        //System.out.println("\tDEBUG: in buildArrayOfArrays.");
        ArrayList<ArrayList<String>> arrayOfArrays = new ArrayList<ArrayList<String>>();
        
        for (int i = 0; i<this.aspects.size();i++) {
            TestAspect ta = this.aspects.get(i);
            ArrayList<String> allOptions = new ArrayList<String>();
            List<TestOptionGroup> groups = ta.getOptionGroups();
            
            //for each option group
            for(int j=0; j<groups.size();j++) {
                List<String> options = groups.get(j).getOptions();
                //add all the Group's options to the allOptions list
                allOptions.addAll(options);
            }
            
            //System.out.println("DEBUG: BEFORE ADDING TO AofA: " + ta.getName() + "'s Options: " + allOptions.toString());
            arrayOfArrays.add(allOptions);
            //System.out.println("DEBUG: AFTER ADDING TO AofA: " +arrayOfArrays.get(i).toString());
        }
        return arrayOfArrays;
    }

        
    
    
    

}

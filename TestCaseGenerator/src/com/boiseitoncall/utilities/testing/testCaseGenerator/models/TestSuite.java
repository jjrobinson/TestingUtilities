/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boiseitoncall.utilities.testing.testCaseGenerator.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author JasonRobinson
 */
public class TestSuite {
    private String name;
    private String desc;
    private ArrayList<TestAspect> aspects;
    private int numTestAspects;
    private ArrayList<ArrayList<String>> testCases;
    private int recursiveCallsCounter;
    
    
    /**
     * Default constructor
     * 
     */
    public TestSuite() {
        this.name = "";
        this.desc = "";
        this.numTestAspects = 0;
        this.testCases = new ArrayList<ArrayList<String>>();
        this.aspects = new ArrayList<TestAspect>();
    }

    /**
     * Partial TestSuite Constructor. Requires name (String), description (String)
     * 
     * @param name String
     * @param desc String
     * @param aspects List<TestAspect> list of TestAspect objects
     * @param testCases List<String> individual strings containing the description of that test.
     */
    public TestSuite(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.testCases = new ArrayList<ArrayList<String>>();
        this.aspects = new ArrayList<TestAspect>();
    }

    /**
     * Full TestSuite Constructor. Requires name (String), description (String), List of TestAspects, number of aspects (int), 
     * and List of TestCases(String).
     * 
     * @param name String
     * @param desc String
     * @param newAspects List<TestAspect> list of TestAspect objects
     * @param numTestAspects int count of test aspects
     */
    public TestSuite(String name, String desc, ArrayList<TestAspect> newAspects) {
        this.name = name;
        this.desc = desc;
        this.aspects = newAspects;
        this.numTestAspects = newAspects.size();
        this.testCases = new ArrayList<ArrayList<String>>();
        computeAllTestCases();
    }

    
    /**
     * returns the name of the TestSuite
     * @return name String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the TestSuite's name
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of this TestSuite
     * @return name String
     */
    public String getDescription() {
        return desc;
    }

    /**
     * Sets the description of this TestSuite
     * @param desc 
     */
    public void setDescription(String desc) {
        this.desc = desc;
    }
    

    /**
     * Returns a list of TestAspects
     * @return aspects List<TestAspect>
     */
    public List<TestAspect> getAspects() {
        return this.aspects;
    }


    /**
     * Returns a list of all the Computed TestCases for the TestSuite
     * @return 
     */
    public ArrayList<ArrayList<String>> getTestCases() {
        return this.testCases;
    }




    /**
     * Adds a testCase to the TestSuit's testCase list.
     * @return 
     */
    private boolean AddATestCase(ArrayList a) {
        return this.testCases.add(a);
    }
    
    /**
     * Returns a List<String> of all the test cases generated by this test suite
     * @return 
     */
    private void computeAllTestCases() {
        recursiveCallsCounter = 0;
        setTestCases(new ArrayList<ArrayList<String>>());
        //assembles a simple list of lists of strings with no TestCases / TestAspects objects
        ArrayList<ArrayList<String>> arrayOfArrays = buildArrayOfArrays();
        

        System.out.println("DEBUG: List of Lists: ToString(): " +arrayOfArrays.toString());
        
        ArrayList<String> optionsList = new ArrayList<String>();

        
        //recursive call
        recurse(optionsList, arrayOfArrays);
        
        System.out.println("DEBUG: testCases.size(): "+testCases.size());
        System.out.println("DEBUG: testCases.toString(): "+testCases.toString());
    }


    /**
     * 
     * @param optionsList
     * @param AofA
     * @return 
     */
    private void recurse(
            ArrayList<String> optionsList, 
            ArrayList<ArrayList<String>> AofA){
        recursiveCallsCounter++;
        
        System.out.println("DEBUG: in Recurse iteration #"+recursiveCallsCounter+" : optionsList:" +optionsList.toString());
        System.out.println("DEBUG: in Recurse iteration #"+recursiveCallsCounter+" : AofA:" +AofA.toString());
        

        
        //check to see if we are at the end of all TestAspects
        if(AofA.size() == 0){
            //no more aspects, add the current options as a TestCase
            //TestCase newTest = new TestCase((this.testCases.size() + 1), OptionsList);
            this.testCases.add(optionsList);

        }// end of if there are more TestAspects
        else {            //still something left in the AofA stack
            //take the top TestAspect off the stack
            List<String> currentAspectsOptions = AofA.remove(0);
//            Iterator i = currentAspect.iterator();
            
            //Iterator implementation vs for loop
            /*
            while(i.hasNext()) {
                newOptionsList.add(i.next().toString());
                recurse(newOptionsList, AofA);
            }
            */
            
            for (int i=0 ; i<currentAspectsOptions.size();i++){
                ArrayList<String> newOptionsList = new ArrayList<String>();

                newOptionsList.addAll(optionsList);
                newOptionsList.add(currentAspectsOptions.get(i));
                System.out.println("DEBUG: Before Recursive Call; newOptionsList: " + newOptionsList.toString());
                System.out.println("DEBUG: Before Recursive Call; currentAspectsOptions["+i+"]: " + currentAspectsOptions.get(i));
                System.out.println("DEBUG: Before Recursive Call; AofA: " + AofA.toString());
                recurse(newOptionsList,AofA);
            }
        }
        
        
        
    }//end recursive helper 


    /**
     * Adds a TestAspect to the TestSuite, increments the Aspect counter,
     * computes all the new TestCases as a result of the new combinations provided.
     * 
     * Test case computation is accomplished via the private method computeAllTestCases
     * @param newAspect <code>TestAspect</code> and must not be null
     */
    public boolean addAspect(TestAspect newAspect)
    {
        if(newAspect != null)
        {
            int tmpCount = this.numTestAspects;
            this.aspects.add(newAspect);
            this.numTestAspects = this.aspects.size();
            this.testCases = new ArrayList<ArrayList<String>>();
            this.testCases = computeAllTestCases();
            
            //sanity check to make sure we incremented the # of testAspects
            if(tmpCount != this.numTestAspects)
                return true;
        }
        return false;
    }
        
    
    /**
     * Adds one or more TestAspects to the TestSuite AND increments the Aspect counter
     * @param newAspects List of <code>TestAspect</code>'s and must not be null
     */
    public boolean addAspects(List<TestAspect> newAspects)
    {
        if(newAspects != null)
        {
            int tmpCount = this.numTestAspects;
            if (newAspects.size() == 1)
                addAspect(newAspects.get(0));//if there is only one, call the normal addAspect
            this.aspects.addAll(newAspects);//if there are several aspects being added, add them all
            this.numTestAspects = aspects.size();
            this.testCases = new ArrayList<ArrayList<String>>();
            this.testCases = computeAllTestCases();
            
            //sanity check to make sure we incremented the # of testAspects
            if(tmpCount != this.numTestAspects)
                return true;
        }
        return false;
    }

        /**
     * Sets the TestSuite's Aspects list to the provided List TestAspects. 
     * Resets the counter for the number of aspects to the size of the list passed in.
     * @param newApsects ArrayList<TestAspect>
     */
    public void setAspects(ArrayList<TestAspect> newApsects) {
        this.aspects = newApsects;
        this.numTestAspects = newApsects.size();
        computeAllTestCases();
    }

    
    
    /**
     * Returns the names of all TestAspects in the TestSuite
     * @return 
     */
    public ArrayList<String> getAspectNames(){
        ArrayList aspectNames = new ArrayList<>();
        Iterator<TestAspect> i = aspects.listIterator();
        while (i.hasNext()){
            aspectNames.add(i.next().getName());
            }
        return aspectNames;
    }

    /**
     * Returns the number of TestAspects in this TestSuite
     * @return int number of TestAspects
     */
    public int getNumTestAspects() {
        return numTestAspects;
    }

    /**
     * Overrides the default toString behavior to provide nicer formatting
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        String NEW_LINE = System.getProperty("line.separator");

        result.append(this.getClass().getName() + " Object {" + NEW_LINE);
        result.append("TestSuite Name: " +this.getName() + NEW_LINE);
        result.append("TestSuite Description: " +this.getName() + NEW_LINE);
        result.append("Number of TestAspects: " +this.getNumTestAspects() + NEW_LINE);
        result.append("TestAspects: " +NEW_LINE);

        for (int i = 0 ; i < aspects.size() ; i++) {
            result.append("\"" + aspects.get(i).toString() + "\", ");
        }

        result.append(NEW_LINE + "}");

        return result.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * DEPRICATED
     * Find the largest 2D array needed to hold all possible Aspects x Objects
     * @return int size
     */
    private int findLargestOptionDimension() {
        int maxSize = 0;
        for(int i = 0; i<aspects.size(); i++) {
            int aspectSize = aspects.get(i).getNumberOfOptions();
            if (aspectSize > maxSize) {
            maxSize = aspectSize; }
        }
    return maxSize;
    }
    
    /**
     * DEPRICATED
     * 
     */
    private String[][] fillIn2Darray()
    {
        
        int maxSize = findLargestOptionDimension();
        String[][] results = new String[aspects.size()][maxSize];
        //loop on each aspect
        for (int i=0; i < aspects.size();i++){
            //for the current aspect until you hit that aspect's Max# of options
            //loop on options
            for (int j=0; j < aspects.get(i).getOptions().size() ; j++){
                //add options to the results array
                results[i][j] = aspects.get(i).getOptions().get(j);
            }
        }
        return results;
    }
    
    /**
     * Builds the simple array of Arrays
     */
    private ArrayList<ArrayList<String>> buildArrayOfArrays(){
        //build the simple list of lists
        ArrayList<ArrayList<String>> arrayOfArrays = new ArrayList<ArrayList<String>>();
        
        for (int i = 0; i < aspects.size() ; i++) {
            //initialize each row in the array
            arrayOfArrays.add(i, new ArrayList<String>());
            for (int j = 0 ; j < this.aspects.get(i).getOptions().size(); j++) {
                arrayOfArrays.get(i).add(this.aspects.get(i).getOptions().get(j));
            }
        }
        return arrayOfArrays;
    }

    /**
     * 
     * @param newTestCases 
     */
    private void setTestCases(ArrayList<ArrayList<String>> newTestCases){
        this.testCases = null;
        this.testCases = newTestCases;
    }
    
}

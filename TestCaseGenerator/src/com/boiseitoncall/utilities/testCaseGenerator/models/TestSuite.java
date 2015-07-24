/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boiseitoncall.utilities.testCaseGenerator.models;


import java.util.ArrayList;
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
    private ArrayList<TestCase> allTestCases;
    private ArrayList<TestCase> smartTestCases;
    private int recursiveCallsCounter;
    private int numAllTestCases;
    private int numSmartTestCases;
    
    /**
     * Default constructor
     * 
     */
    public TestSuite() {
        this.name = "";
        this.desc = "";
        this.numTestAspects = 0;
        this.allTestCases = new ArrayList<TestCase>();
        this.smartTestCases = new ArrayList<TestCase>();
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
        this.allTestCases = new ArrayList<TestCase>();
        this.smartTestCases = new ArrayList<TestCase>();
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
        this.numTestAspects = this.aspects.size();
        this.allTestCases = new ArrayList<TestCase>();
        this.smartTestCases = new ArrayList<TestCase>();
        computeAllTestCases();
        computeSmartTestCases();
    }

    
    /**
     * returns the name of the TestSuite
     * @return name String
     */
    public String getName() {
        return this.name;
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
        return this.desc;
    }

    /**
     * Sets the description of this TestSuite
     * @param desc 
     */
    public void setDescription(String desc) {
        this.desc = desc;
    }
    


    public int getNumAllTestCases() {
        return this.numAllTestCases;
    }

    public int getNumSmartTestCases() {
        return this.numSmartTestCases;
    }
  

    /**
     * Returns a list of TestAspects
     * @return aspects List<TestAspect>
     */
    public List<TestAspect> getAspects() {
        return this.aspects;
    }


    /**
     * Returns the allTestCases list in this TestSuite
     * @return TestCase {@literal <testCase>}
     */
    public ArrayList<TestCase> getAllTestCases() {
        return this.allTestCases;
    }

    /**
     * Returns the smartTestCases list in this TestSuite
     * @return TestCase {@literal <testCase>}
     */
    public ArrayList<TestCase> getSmartTestCases() {
        return this.smartTestCases;
    }

    /**
     * Adds a single TestCase to the allTestCases list
     * @param a {@literal <TestCase>} a TestCase
     * @return boolean true if anything was added, false for everything else
     */
    private boolean addToAllTestCase(TestCase aTestCase) {
        int i = this.numAllTestCases;
        this.allTestCases.add(aTestCase);
        if (i != this.allTestCases.size()) {
            this.numAllTestCases = this.allTestCases.size();
            return true;
        } else { return false; }
    }
    

    /**
     * Adds a single TestCase to the smartTestCases list and sets the 
     * @param a {@literal <TestCase>} a TestCase
     * @return boolean true if anything was added, false for everything else
     */
    private boolean addToSmartTestCase(TestCase aTestCase) {
        int i = this.numSmartTestCases;
        this.smartTestCases.add(aTestCase);
        if (i != this.smartTestCases.size()){
            this.numSmartTestCases = this.smartTestCases.size();
            return true;
        } else { return false; }
    }
    

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
            int oldCountOfAspects = this.numTestAspects;
            this.aspects.add(newAspect);
            this.numTestAspects = this.aspects.size();
            this.allTestCases = new ArrayList<TestCase>();
            //after setting the TestAspects, we need to calculate the AllTestCases
            computeAllTestCases();
            computeSmartTestCases();
            
            //sanity check to make sure we incremented the # of testAspects
            if(oldCountOfAspects != this.numTestAspects)
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
            this.allTestCases = new ArrayList<TestCase>();

            //after setting the TestAspects and blanking out the AllTestCases
            // we need to calculate the AllTestCases based on the new TestAspects
            computeAllTestCases();
            computeSmartTestCases();
            
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
        //after setting the TestAspects, we need to calculate the AllTestCases
        computeAllTestCases();
        computeSmartTestCases();
    }

    
    
    /**
     * Returns the names of all TestAspects in the TestSuite
     * @return 
     */
    public ArrayList<String> getAspectNames(){
        ArrayList aspectNames = new ArrayList<>();
        
        for (TestAspect ta : this.aspects) {
            aspectNames.add(ta.getName());
        }
        return aspectNames;
    }

    /**
     * Returns the number of TestAspects in this TestSuite
     * @return int number of TestAspects
     */
    public int getNumTestAspects() {
        return this.numTestAspects;
    }

    /**
     * Overrides the default toString behavior to provide nicer formatting
     * @return 
     */
    @Override
    public String toString() {
        //stuff all the toString stuff into a single StringBuilder then output at the end.
        StringBuilder result = new StringBuilder();

        String NEW_LINE = System.getProperty("line.separator");

        result.append("TestSuite Name: \"").append(this.getName()).append("\"")
                .append(NEW_LINE);
        result.append("TestSuite Description: \"").append(this.getDescription())
                .append("\"").append(NEW_LINE);
        result.append("Number of TestAspects: \"").append(this.getNumTestAspects())
                .append("\"").append(NEW_LINE);
        result.append("TestAspects: ").append(NEW_LINE).append("{");

        for (int i = 0 ; i < aspects.size() ; i++) {
            TestAspect ta = aspects.get(i);
            String num = String.valueOf(i+1);
            result.append("\tAspect #").append(num).append(" Name: \"").append(
                    ta.getName()).append("\"").append(NEW_LINE);
            result.append("\tAspect #").append(num).append(" Description: \"").
                    append(ta.getDescription()).append("\"").append(NEW_LINE);
            result.append("\tAspect #").append(num).append(" OptionGroups: ").
                    append(NEW_LINE).append("\t{").append(NEW_LINE);
            List<TestOptionGroup> togs = ta.getOptionGroups();

            for (int j=0;j<togs.size();j++) {
                String num2 = String.valueOf(j+1);
                result.append("\t\tOption Group #").append(num2).append(": Name: \"")
                        .append(togs.get(j).getName()).append("\"").append(NEW_LINE);
                result.append("\t\tOption Group #").append(num2).append(": Description: \"")
                        .append(togs.get(j).getDescription()).append("\"").append(NEW_LINE);
                result.append("\t\tOption Group #").append(num2).append(": Options: ")
                        .append(NEW_LINE).append("\t\t{").append(NEW_LINE);
                List<String> options = togs.get(j).getOptions();

                for (int k=0; k< options.size();k++) {
                    String num3 = String.valueOf(k+1);
                    result.append("\t\t\tOption #").append(num3).append(": \"")
                            .append(options.get(k)).append("\"").append(NEW_LINE);
                }
                result.append("\t\t}").append(NEW_LINE);
            }
            result.append("\t}").append(NEW_LINE);
        }

        result.append(NEW_LINE).append("}");

        return result.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
  

    /**
     * Returns the number of test cases in the TestSuite
     * @return int NumTestCases
     */
    public int getNumberOfAllTestCases(){
        return this.numAllTestCases;
    }
  

    /**
     * Returns the number of test cases in the TestSuite
     * @return int NumTestCases
     */
    public int getNumberOfSmartTestCases(){
        return this.numSmartTestCases;
    }


   /////////////////////////////////////////////////////////////////////////////
   /////  P R I V A T E   M E T H O D S   B E L O W  ///////////////////////////
   /////////////////////////////////////////////////////////////////////////////
    /*
     * Sets the allTestCases list of test cases to the supplied ListOfLists.
     * @param newTestCases 
    private void setTestCases(ArrayList<TestCase> newTestCases){
        this.allTestCases = newTestCases;
        this.numAllTestCases = this.allTestCases.size();    
    }
     */

    /*
     * Sets the smartTestCases list of test cases to the supplied ListOfLists.
     * @param newTestCases 
    private void setSmartTestCases(ArrayList<TestCase> newSmartTestCases){
        this.smartTestCases = null;
        this.smartTestCases = newSmartTestCases;
        this.numSmartTestCases = this.smartTestCases.size();
    }
     */


    /**
     * Computes the unique combinations 
     */
    private void computeAllTestCases() {
        //System.out.println("DEBUG: Starting computeAllTestCases.");
        
        ArrayList<String> optionsList = new ArrayList<String>();
        ArrayList<ArrayList<String>> arrayOfArrays = buildArrayOfArrays();
        
        recursiveCallsCounter = 0;
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
        ArrayList<ArrayList<String>> arrayOfArrays = buildArrayOfArrays();
        
        recursiveCallsCounter = 0;
//        recurseIgnoringGroups(smartOptionsList, arrayOfArrays, 0);
    
        //System.out.println("Total Test Cases: " + this.getNumberOfAllTestCases());
        //System.out.println("DEBUG: ENDING computeAllTestCases.\n\n");
        this.numAllTestCases = this.allTestCases.size();
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

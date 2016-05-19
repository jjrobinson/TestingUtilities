/*
 * 
 * 
 */
package org.jjrobinson.utilities.testCaseGenerator;

import org.jjrobinson.utilities.testCaseGenerator.models.AspectOptionGroupItem;
import org.jjrobinson.utilities.testCaseGenerator.models.TestAspect;
import org.jjrobinson.utilities.testCaseGenerator.models.TestCase;
import org.jjrobinson.utilities.testCaseGenerator.models.TestOptionGroup;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JasonR
 */
public class TestCaseCalculator {
    private ArrayList<TestAspect> aspectsList;
    private int recursiveCallsCounter;
    private int numAllTestCases;
    private int numSmartTestCases;
    private ArrayList<TestCase> allTestCases;
    private ArrayList<TestCase> smartTestCases;
    private boolean ignoreGroups;

    
    /**
     * Default constructor
     */
    public TestCaseCalculator(){
        this.aspectsList = new ArrayList<TestAspect>();
        this.recursiveCallsCounter = 0;
        this.numAllTestCases = 0;
        this.numSmartTestCases = 0;
        this.allTestCases = new ArrayList<TestCase>();
        this.smartTestCases = new ArrayList<TestCase>();
        this.ignoreGroups = false;
    }
    
    /**
     * Incomplete constructor with {@code ArrayList<TestAspects>} provided
     * @param newTestAspects 
     */
    public TestCaseCalculator(ArrayList<TestAspect> newTestAspects){
        this.aspectsList = new ArrayList<TestAspect>();
        this.recursiveCallsCounter = 0;
        this.numAllTestCases = 0;
        this.numSmartTestCases = 0;
        this.allTestCases = new ArrayList<TestCase>();
        this.smartTestCases = new ArrayList<TestCase>();
        this.ignoreGroups = false;

        this.aspectsList = newTestAspects;
        this.allTestCases.clear();
        this.smartTestCases.clear();
//        this.computeAllTestCases();
//        this.computeSmartTestCases();
    }
    
    
    /**
     * Returns the list of AllTestCases
     * @return {@code ArrayList<TestCase>}
     */
    public ArrayList<TestCase> getAllTestCases(){
        this.allTestCases.clear();
        this.computeAllTestCases();
        return this.allTestCases;
    }
    
    /**
     * Returns the list of SmartTestCases
     * @return {@code ArrayList<TestCase>}
     */
    public ArrayList<TestCase> getSmartTestCases(){
        this.smartTestCases.clear();
        if (!this.ignoreGroups) {
            computeSmartTestCases();
            return this.smartTestCases;
        } else {
            return this.smartTestCases;
        }
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
     * Computes the unique combinations ignoring {@code TestOptionGroup} efficiencies
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
     * Computes the Smart Test Case list making use of {@code TestOptionGroup}
     */
    private void computeSmartTestCases(){
        //System.out.println("DEBUG: In computeSmartTestCases");
        ArrayList<AspectOptionGroupItem> AOGIlist = 
                new ArrayList<AspectOptionGroupItem>();
        this.smartTestCases.clear();
        
        recursiveCallsCounter = 0; // just for debugging.
        
        recurseWithGroups(AOGIlist, this.aspectsList, 0);
    
        //System.out.println("Total Test Cases: " + this.getNumberOfAllTestCases());
        //System.out.println("DEBUG: ENDING computeAllTestCases.\n\n");
        this.numSmartTestCases = this.smartTestCases.size();
    }
    
    
    /**
     * Recursive function to traverse each TestAspect's TestOptionGroup and 
     * return a complete list of TestCases containing one item chosen from each
     * TestOptionGroup.  
     * Aspect's OptionGroups x Aspect's OptionGroups x etc.ww
     * 
     * @param AOGIList
     * @param testAspects
     * @param placeHolder 
     */
    private void recurseWithGroups(
            ArrayList<AspectOptionGroupItem> AOGIList, 
            ArrayList<TestAspect> testAspects, int placeHolder){
        this.recursiveCallsCounter++; // for debugging
//        System.out.println("DEBUG: In recurseWithGroups(). Call #"+recursiveCallsCounter);
//        System.out.println("DEBUG: placeHolder: " + placeHolder);
//        System.out.println("DEBUG: AOGIList Size: " +AOGIList.size());
//        for(int i=0;i< AOGIList.size();i++) {
//            System.out.println("DEBUG: AOGIList Item #"+i +" " +AOGIList.get(i).toString());
//        }
        
        //check to see if we are at the end of the list of TestAspects
        if(placeHolder < testAspects.size()) {
            //get the TestAspect for the current placeHolder
            TestAspect ta = testAspects.get(placeHolder);
            //save the TestOptionGroups for this TestAspect
            ArrayList<TestOptionGroup> togList = ta.getOptionGroups();
            for (int i=0 ; i< togList.size();i++) {
                ArrayList<AspectOptionGroupItem> newAOGIList = 
                        new ArrayList<AspectOptionGroupItem>();
                newAOGIList.addAll(AOGIList);
                TestOptionGroup tog = togList.get(i);

                //create a new GroupItem for the AOGIList
                AspectOptionGroupItem newAOGI = new AspectOptionGroupItem(
                        placeHolder, this.aspectsList.get(placeHolder).getName(),
                        i,togList.get(i).getName(),togList.get(i));
                newAOGIList.add(newAOGI);
                recurseWithGroups(newAOGIList, testAspects, (placeHolder+1));
            }
        } else { // no more arrays to pop off
            ArrayList<String> options = new ArrayList<String>();
            String s;
            for(AspectOptionGroupItem item :AOGIList) {
                try {
                s = testAspects.get(item.getTestAspectNumber()).getOptionGroups()
                        .get(item.getTestOptionGroupNumber()).getAndUseLeastUsedOption();
                options.add(s);
                } catch (Exception e) {}
                }
            TestCase tc = new TestCase(options);
            this.smartTestCases.add(tc);
        }
        
    }//end recursive helper 

    
    
    private AspectOptionGroupItem makeNewAOGI(int aspectNum, String aspectName,
            int optionNum, String optionName) throws Exception{
        TestOptionGroup tog = null;
        AspectOptionGroupItem AOGI = null;
        
        //throw an error if there aren't enoungh aspects
        if(aspectNum > this.aspectsList.size()){
            //a list of 3 items only has indexes 0, 1, 2
            //TODO make a real exceptions instead of throwing generic Exception
            throw new Exception("ERROR: AspectNum is higher than number of aspects.");
        } 

        TestAspect ta = this.aspectsList.get(aspectNum);
        //sanity check on name + number
        if (ta.getName().equalsIgnoreCase(aspectName)){
            //sanity check on the TestOptionGroup
            tog = ta.getOptionGroups().get(optionNum);
            
            if(tog.getName().equals(optionName)) {
                //build the new AOGI
                TestOptionGroup newTOG = null;
                try {
                    newTOG = (TestOptionGroup)tog.clone();
                }catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                    return null;
                }

                AOGI = new AspectOptionGroupItem(
                aspectNum, aspectName, optionNum,optionName,newTOG);
            } else {
                //oh no. number matched by name didn't.  bail out?
                throw new Exception("ERROR: TestOptionGroup Number matched but the Name didn't.");
            }
        } else {
            //oh no. number matched by name didn't.  bail out?
            throw new Exception("ERROR: AspectNum matched but AspectName didn't.");
        }
        return AOGI;
    }
    

    /**
     * Builds a  simple array of Arrays
     * 
     * @return ArrayList of ArrayList of Strings
     */
    private ArrayList<ArrayList<String>> buildArrayOfArrays(){
        //build the simple list of lists
        //System.out.println("\tDEBUG: in buildArrayOfArrays.");
        ArrayList<ArrayList<String>> arrayOfArrays = new ArrayList<ArrayList<String>>();
        
        for (int i = 0; i<this.aspectsList.size();i++) {
            TestAspect ta = this.aspectsList.get(i);
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

    
    /**
     * returns if this TestSuite is ignoring groups
     * @return 
     */
    public boolean isIgnoreGroups() {
        return ignoreGroups;
    }

    /**
     * sets if this TestSuite is ignoring groups or not.
     * @param ignoreGroups boolean
     */
    public void setIgnoreGroups(boolean ignoreGroups) {
        this.ignoreGroups = ignoreGroups;
    }


    
    
    
}//end of class

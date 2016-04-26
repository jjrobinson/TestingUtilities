/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jjrobinson.utilities.testCaseGenerator.models;


import org.jjrobinson.utilities.testCaseGenerator.TestCaseCalculator;
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
    private int numAllTestCases;
    private int numSmartTestCases;
    private boolean ignoreGroups;
 
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
        this.ignoreGroups = false;
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
        this.ignoreGroups = false;
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
    public TestSuite(String name, String desc, 
            ArrayList<TestAspect> newAspects,
            boolean ignoreGroupsStatus) {
        this.name = name;
        this.desc = desc;
        this.aspects = newAspects;
        this.numTestAspects = this.aspects.size();
        this.allTestCases = new ArrayList<TestCase>();
        this.smartTestCases = new ArrayList<TestCase>();
        this.ignoreGroups = ignoreGroupsStatus;
    }

    
    /**
     * Sets the allTestCases list in this TestSuite
     * @return TestCase {@literal <testCase>}
     */
    public void setAllTestCases(ArrayList<TestCase> newAllTestCases) {
        this.allTestCases.clear();
        this.allTestCases.addAll(newAllTestCases);
        this.numAllTestCases = this.allTestCases.size();
    }

    /**
     * Sets the smartTestCases list in this TestSuite
     * @return TestCase {@literal <testCase>}
     */
    public void setSmartTestCases(ArrayList<TestCase> newSmartTestCases) {
        this.smartTestCases.clear();
        this.smartTestCases.addAll(newSmartTestCases);
        this.numSmartTestCases = this.smartTestCases.size();
    }

    /**
     * Adds a single TestCase to the allTestCases list
     * @param a {@code <TestCase>} a TestCase
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
     * @param a {@code <TestCase>} a TestCase
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
     * Adds a TestAspect to the TestSuite, AND increments the Aspect counter,
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

            this.allTestCases.clear();
            this.smartTestCases.clear();
            
            TestCaseCalculator tcc = new TestCaseCalculator(this.aspects);
            tcc.setIgnoreGroups(this.ignoreGroups);
            //System.out.println("DEBUG: In TestSuite.addAspect() adding " +newAspect.getName());

            this.setAllTestCases(tcc.getAllTestCases());
            if (!this.ignoreGroups) this.setSmartTestCases(tcc.getSmartTestCases());
            
            
            //sanity check to make sure we incremented the # of testAspects
            if(oldCountOfAspects != this.numTestAspects)
                return true;
        }
        return false;
    }
        
    
    /**
     * Adds one or more TestAspects to the TestSuite using addAspect method.
     * @param newAspectsList List of <code>TestAspect</code>'s and must not be null
     */
    public boolean addAspects(List<TestAspect> newAspectsList)
    {
        boolean success_tracker = false;
        if(newAspectsList != null)
        {
            for (TestAspect ta : newAspectsList) {
                if(addAspect(ta))
                    success_tracker = true;
            }
        }
        return success_tracker;
    }

        /**
     * Sets the TestSuite's Aspects list to the provided List TestAspects. 
     * Resets the counter for the number of aspects to the size of the list passed in.
     * @param newApsects ArrayList<TestAspect>
     */
    public void setAspects(ArrayList<TestAspect> newApsects) {
        this.aspects = newApsects;
        this.numTestAspects = newApsects.size();
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
        if(this.ignoreGroups)
            result.append("This TestSuite IS Ignoring Groups.").append(NEW_LINE);
        else
            result.append("This TestSuite IS NOT Ignoring Groups.").append(NEW_LINE);
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
            ArrayList<TestOptionGroup> togs = ta.getOptionGroups();

            
            if(!this.isIgnoreGroups()) {
                result.append("\tAspect #").append(num).append(" OptionGroups: ").
                    append(NEW_LINE).append("\t{").append(NEW_LINE);
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
                }//end of OptionGroups
            } else { //if NOT using groups
                result.append("\tAspect #").append(num).
                    append(NEW_LINE).append("\t{").append(NEW_LINE);
                if(togs.size() ==1) {//just a doublecheck
                    List<String> options = togs.get(0).getOptions();
                    for (int k=0; k< options.size();k++) {
                        String num3 = String.valueOf(k+1);
                        result.append("\t\tOption #").append(num3).append(": \"")
                                .append(options.get(k)).append("\"").append(NEW_LINE);
                    }
                }//end of the double check
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
    


    /**
     * Returns the number of AllTestCases
     * @return 
     */
    public int getNumAllTestCases() {
        return this.numAllTestCases;
    }


    /**
     * Returns the number of SmartTestCases
     * @return 
     */
    public int getNumSmartTestCases() {
        return this.numSmartTestCases;
    }
  

    /**
     * Returns a list of TestAspects
     * @return aspects ArrayList<TestAspect>}
     */
    public ArrayList<TestAspect> getAspects() {
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
     * Returns if this TestSuite is going to ignore groups or not
     * @return boolean
     */
    public boolean isIgnoreGroups() {
        return ignoreGroups;
    }

    /**
     * Sets if this test group is going to ignore groups
     * @param ignoreGroups boolean
     */
    public void setIgnoreGroups(boolean ignoreGroups) {
        this.ignoreGroups = ignoreGroups;
    }
       
}

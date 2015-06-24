/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boiseitoncall.utilities.testing.models;

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
    private List<String> testCases;

    //TODO implement a toString that can handle the nested TestAspects and TestCases
    
    
    
    /**
     * Default constructor
     * 
     */
    public TestSuite() {
        this.name = "";
        this.desc = "";
        this.numTestAspects = 0;
        this.testCases = new ArrayList<String>();
        this.aspects = new ArrayList<TestAspect>();
    }

    /**
     * partial TestSuite Constructor. Requires name (String), description (String), number of aspects (int)
     * 
     * @param name String
     * @param desc String
     * @param aspects List<TestAspect> list of TestAspect objects
     * @param numTestAspects int count of test aspects
     * @param testCases List<String> individual strings containing the description of that test.
     */
    public TestSuite(String name, String desc, int numTestAspects) {
        this.name = name;
        this.desc = desc;
        this.numTestAspects = numTestAspects;
        this.testCases = new ArrayList<String>();
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
     * @param testCases List<String> individual strings containing the description of that test.
     */
    public TestSuite(String name, String desc, ArrayList<TestAspect> newAspects, int numTestAspects, List<String> testCases) {
        this.name = name;
        this.desc = desc;
        this.aspects = newAspects;
        this.numTestAspects = numTestAspects;
        this.testCases = testCases;
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
    public void setDesc(String desc) {
        this.desc = desc;
    }
    

    /**
     * Returns a list of TestAspects
     * @return aspects List<TestAspect>
     */
    public List<TestAspect> getAspects() {
        return aspects;
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
     * Returns a List<String> of all the test cases generated by this test suite
     * @return 
     */
    public List<String> getAllTestCases() {
        this.testCases = null;
        List<List<String>> arrayOfArrays = new ArrayList<>();
        
        List indexTracker = new ArrayList();
        
        //build the simple list of lists
        
        for (int i = 0; i < this.numTestAspects ; i++) {
            arrayOfArrays.add(i, aspects.get(i).getOptions());
        }
        
        System.out.println("DEBUG: List of Lists: ToString(): " +arrayOfArrays.toString());
        
        
        
        
        //now fill in aspects into a plain 2D String array
        
        String[][] inputSets = fillIn2Darray();
        
        System.out.println("DEBUG: Plain String[][] array: " + inputSets.toString());
        
        
        //recursive call
        getAllTestCasesRecursiveHelper(inputSets, indexTracker, 0, this.testCases);
        
        return this.testCases;
    }

	/**
	 * Recursive method to take off one layer from the list, and call itself
	 * @param smallerList
	 * @return 
	 */
    private void getAllTestCasesRecursiveHelper(String[][] inputSets, List indexTracker, int currentSet, List results){
        //for (int i = 0 ; i < inputSets
        //algorithm here http://programmingpraxis.com/2013/09/06/cartesian-product/
        
        for(int i = 0 ; i < inputSets[currentSet].length; i++){
            indexTracker.set(currentSet, i);
            if(currentSet == inputSets.length -1){
                List carteseanSet = new ArrayList<String>();
                for(int j = 0 ; j <inputSets.length ; j++) {
                    carteseanSet.set(j, inputSets[j][Integer.parseInt(indexTracker.get(j).toString())]);
                    
                }
            }
        }

        
    }
	
	/**
         * 
	public class CartesianProductRecursive : ICartesianProduct
{
    public int[][] GetProduct(int[][] sets) {
        var resultSets = new List<int[]> ();
        var indices = new int[sets.Length];
        CreateProductSet (sets, indices, 0, resultSets);
        return resultSets.ToArray();
    }
 
    private void CreateProductSet(int[][] inputSets, int[] indices, int currentSet, List<int[]> resultSets) {
        for (int i = 0; i < inputSets[currentSet].Length; i++) {
            indices [currentSet] = i;
            if (currentSet == (inputSets.Length - 1)) {
                var cartesianSet = new int[inputSets.Length];
                for (int j = 0; j < inputSets.Length; j++) {
                    cartesianSet[j] = (inputSets[j][indices[j]]);
                }
                resultSets.Add (cartesianSet);
            } else {
                CreateProductSet (inputSets, indices, currentSet + 1, resultSets);
            }
        }
    }
}
	*/
    
	
    /**
     * Sets the TestSuite's TestCase list to the provided List String.
     * Should not be used.  This is a troubleshooting method only.
     * @param testCases  List<String>
     */
    public void setTestCases(List<String> testCases) {
        this.testCases = testCases;
    }
    
    
    /**
     * Adds a TestAspect to the TestSuite AND increments the Aspect counter
     * @param newAspect TestAspect
     */
    public void addAspect(TestAspect newAspect)
    {
        if(newAspect != null)
        {
            this.aspects.add(newAspect);
            this.numTestAspects++;
            
        }
        
    }
    
    /**
     * Gets the names of all TestAspects in the TestSuite
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


    @Override public String toString() {
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
    
    
    private int findLargestOptionDimension() {
        int maxSize = 0;
        for(int i = 0; i<aspects.size(); i++) {
            int aspectSize = aspects.get(i).getNumberOfOptions();
            if (aspectSize > maxSize) {
            maxSize = aspectSize; }
        }
    return maxSize;
    }
    
    private String[][] fillIn2Darray()
    {
        int maxSize = findLargestOptionDimension();
        String[][] results = new String[aspects.size()][maxSize];
        
        for (int i=0; i < aspects.size();i++){
            for (int j=0; j < aspects.get(i).getOptions().size() ; j++){
                results[i][j] = aspects.get(i).getOptions().get(j);
            }
        }
        return results;
    }
    
    
}

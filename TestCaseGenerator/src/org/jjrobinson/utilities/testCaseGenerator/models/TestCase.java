/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jjrobinson.utilities.testCaseGenerator.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jason Robinson
 */
public class TestCase implements Cloneable {
    private ArrayList<String> testOptions;
    private int numberOfTestOptions;

    /**
     * Constructor for when you have the testOptions to use
     * @param testOptions 
     */
    public TestCase(ArrayList<String> testOptions) {
        this.testOptions = testOptions;
        this.numberOfTestOptions = testOptions.size();
    }

    /**
     * Default Constructor
     */
    public TestCase() {
        this.testOptions = new ArrayList<String>();
        this.numberOfTestOptions = 0;
    }

    /**
     * Over ride of the Object.clone() method.
     * @return 
     */
    @Override
    public Object clone()throws CloneNotSupportedException {
        ArrayList<String> newOptions = new ArrayList<String>();
        for(String s: this.testOptions)
            newOptions.add(s);
        TestCase tc = (TestCase)super.clone();
        tc.setTestOptions(newOptions);
        return tc;
    }
    
    /**
     * Returns a list of the test variables (strings)
     * @return testOptions List<String>
     */
    public List<String> getTestOptions() {
        return this.testOptions;
    }

    /**
     * Sets the TestCase's list of Options to the provided list, deleting all 
     * previous options.
     * @param testOptions 
     */
    public void setTestOptions(ArrayList<String> testOptions) {
        this.testOptions.clear();
        this.testOptions.addAll(testOptions);
        this.numberOfTestOptions = this.testOptions.size();
    }
    
    public int getNumberOfTestOptions(){
        return this.numberOfTestOptions;
    }
    
    public void addTestOption(String newOption){
        this.testOptions.add(newOption);
        this.numberOfTestOptions = this.testOptions.size();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boiseitoncall.utilities.testing.testCaseGenerator.models;

import java.util.List;

/**
 *
 * @author Jason Robinson
 */
public class TestCase {
    private int testNumber;
    private List<String> testOptions;

    /**
     * Default constructor
     * @param testNumber
     * @param testOptions 
     */
    public TestCase(int testNumber, List<String> testOptions) {
        this.testNumber = testNumber;
        this.testOptions = testOptions;
    }

    /**
     * Returns this test's TestCase #
     * @return 
     */
    public int getTestNumber() {
        return testNumber;
    }

    /**
     * Sets this test case's number
     * @param testNumber 
     */
    public void setTestNumber(int testNumber) {
        this.testNumber = testNumber;
    }

    /**
     * Returns a list of the test variables (strings)
     * @return testOptions List<String>
     */
    public List<String> getTestOptions() {
        return testOptions;
    }

    /**
     * Sets the TestCase's list of Options
     * @param testOptions 
     */
    public void setTestOptions(List<String> testOptions) {
        this.testOptions = testOptions;
    }
    
    

}

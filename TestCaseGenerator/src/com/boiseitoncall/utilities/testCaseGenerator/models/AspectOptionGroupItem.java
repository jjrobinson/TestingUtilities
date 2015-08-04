/*
 * 
 * 
 */
package com.boiseitoncall.utilities.testCaseGenerator.models;

/**
 *
 * @author JasonRobinson
 */
public class AspectOptionGroupItem {
    private int testAspectNumber;
    private String testAspectName;
    private int testOptionGroupNumber;
    private String testOptionGroupName;
    private TestOptionGroup tog;


    /**
     * default constructor
     */
    public AspectOptionGroupItem(){
        
    }
    
    
    /**
     * Full constructor 
     * @param aspectNum int
     * @param aspectName String
     * @param optionGroupNum int
     * @param optionGroupName String
     * @param tog TestOptionGroup
     */
    public AspectOptionGroupItem(int aspectNum, String aspectName,
            int optionGroupNum, String optionGroupName,TestOptionGroup tog){
        this.testAspectNumber = aspectNum;
        this.testAspectName = aspectName;
        this.testOptionGroupNumber = optionGroupNum;
        this.testOptionGroupName = optionGroupName;
        this.tog = tog;
    }
    
    
    
    public int getTestAspectNumber() {
        return testAspectNumber;
    }

    public void setTestAspectNumber(int testAspectNumber) {
        this.testAspectNumber = testAspectNumber;
    }

    public String getTestAspectName() {
        return testAspectName;
    }

    public void setTestAspectName(String testAspectName) {
        this.testAspectName = testAspectName;
    }

    public int getTestOptionGroupNumber() {
        return testOptionGroupNumber;
    }

    public void setTestOptionGroupNumber(int testOptionGroupNumber) {
        this.testOptionGroupNumber = testOptionGroupNumber;
    }

    public String getTestOptionGroupName() {
        return testOptionGroupName;
    }

    public void setTestOptionGroupName(String testOptionGroupName) {
        this.testOptionGroupName = testOptionGroupName;
    }

    public TestOptionGroup getTog() {
        return tog;
    }

    public void setTog(TestOptionGroup tog) {
        this.tog = tog;
    }
    
    
}

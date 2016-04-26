/*
 * 
 * 
 */
package org.jjrobinson.utilities.testCaseGenerator.models;

import java.util.List;

/**
 *
 * @author JasonRobinson
 */
public class AspectOptionGroupItem {
    private static final String NEW_LINE = System.getProperty("line.separator");
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
    
    /**
     * Overrides the default object toString method to provide better output
     * @return 
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Aspect: ").append(this.testAspectName).append(" #")
                .append(this.testAspectNumber).append(" - Group: ")
                .append(this.testOptionGroupName).append(" #")
                .append(this.testOptionGroupNumber).append(NEW_LINE);
        List<String> options = this.tog.getOptions();
        for (int i=0; i< options.size();i++) {
            sb.append("\t").append(options.get(i)).append(NEW_LINE);
        }
        return sb.toString();
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

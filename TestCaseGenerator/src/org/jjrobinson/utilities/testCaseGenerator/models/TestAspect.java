/*
 * Test Aspect object contains a name, and a list of Test Options.  Options are grouped into functional groups.
 */
package org.jjrobinson.utilities.testCaseGenerator.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JasonRobinson
 */
public class TestAspect {
    private String name;
    private String description;
    private int numberOfOptionGroups;
    private List<TestOptionGroup> optionGroups;

    /**
     * Default constructor
     */
    public TestAspect() {
        name = "";
        //description = "";
        numberOfOptionGroups = 0;
        optionGroups = new ArrayList<TestOptionGroup>();
    }


    /*
     * Complete constructor.
     *
    public TestAspect(String name, String description, int numberOfOptions, List newOptions) {
        this.name = name;
        this.description = description;
        this.numberOfOptions = numberOfOptions;
        options.addAll(newOptions);
    }
    */
    
    /**
     * Half complete constructor.
     * @param name
     * @param description 
     */
    public TestAspect(String name, String description) {
        this.name = name;
        //this.description = description;
    }

    
    /**
     * 3/4 complete constructor.
     * @param name
     * @param description 
     * @param numOfOptions
     */
    public TestAspect(String name, String description, int numOfOptions) {
        this.name = name;
        //this.description = description;
        this.numberOfOptionGroups = numOfOptions;
    }


    /**
     * returns the name of this Aspect
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the aspect
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the number of options in this aspect
     * @return 
     */
    public int getNumberOfOptionGroups() {
        return this.numberOfOptionGroups;
    }

    /**
     * Sets the number of options in this aspect. DO NOT USE. This number should 
     * be calculated.  Instead use addOptionGroup, which adds the option and auto-increments the numberOfOptions value
     * @param numberOfOptionGroups 
     */
    public void setNumberOfOptionGroups(int numberOfOptionGroups) {
        this.numberOfOptionGroups = numberOfOptionGroups;
    }

    /**
     * Returns a list of Options.
     * @return List options
     */
    public ArrayList<TestOptionGroup> getOptionGroups() {
        return new ArrayList<TestOptionGroup>(this.optionGroups);
    }

    /**
     * Sets the Aspect's optionGroups list to the supplied List of TestOptionGroups
     * @param newOptionGroups 
     */
    public void setOptionGroups(List<TestOptionGroup> newOptionGroups) {
        this.optionGroups = new ArrayList<TestOptionGroup>();
        for (TestOptionGroup tog : newOptionGroups) {
            this.addOptionGroup(tog);
        }
        this.numberOfOptionGroups = this.optionGroups.size();
    }

    /**
     * Returns the aspect's Description
     * @return 
     *
     * */
    public String getDescription() {
        return description;
    }
   
    /**
     * Sets the aspect's description.
     * @param description 
     *
     * */
    public void setDescription(String description) {
        this.description = description;
    }    

    
    /**
     * Adds a new Option to the Aspect and auto-increments the numberOfOptions
     * @param newOption 
     */
    public void addOptionGroup(TestOptionGroup newOptionGroup){
        this.optionGroups.add(newOptionGroup);
        this.numberOfOptionGroups = this.optionGroups.size();
    }

    /**
     * clears out all options and resets the numberOfOptions counter to 0.
     */
    public void clearOptionGroups(){
        this.optionGroups.clear();
        this.numberOfOptionGroups = 0;
    }


    /**
     * Overrides the default ToString behavior. system agnostic newline
     * @return 
    */     
    @Override public String toString() {
        StringBuilder result = new StringBuilder();

        String NEW_LINE = System.getProperty("line.separator");

        result.append(this.getClass().getName() + " Object {" + NEW_LINE);
        result.append("Aspect Name: " +this.getName() + NEW_LINE);
        //result.append("Aspect Description: " +this.getName() + NEW_LINE);
        result.append("Number of Option Groups: ").append(this.getNumberOfOptionGroups())
                .append(NEW_LINE);
        result.append("Options: ").append(NEW_LINE);
        List options = new ArrayList(getOptionGroups());
        for (int i = 0 ; i < options.size() ; i++) {
            result.append("\tOption ").append((1+i)).append(" ")
                    .append(options.get(i).toString()).append(NEW_LINE);
        }


        result.append(NEW_LINE + "}");

        return result.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}

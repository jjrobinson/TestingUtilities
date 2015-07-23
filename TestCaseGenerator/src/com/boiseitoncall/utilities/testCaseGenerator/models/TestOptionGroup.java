/*
 */

package com.boiseitoncall.utilities.testCaseGenerator.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jason Robinson
 */
public class TestOptionGroup {
    private String name;
    private String description;
    private int numberOfOptions;
    private List<String> options;

    
    /**
     * Default constructor. creates an empty TestOptionGroup
     */
    public TestOptionGroup(){
        this.name="";
        this.description = "";
        this.numberOfOptions = 0;
        this.options = new ArrayList<String>();
    }
    
    /**
     * Construction where name and description are provided
     * @param newName String
     * @param newDescription String
     */
    public TestOptionGroup(String newName, String newDescription){
        this.name = newName;
        this.description = newDescription;
    }

    /**
     * returns the name
     * @return name String
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns the description
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * returns the number of Options in this group
     * @return int
     */
    public int getNumberOfOptions() {
        return numberOfOptions;
    }

    /**
     * returns the options in this group
     * @return options ArrayList
     */
    public List<String> getOptions() {
        return options;
    }

    /**
     * Re sets the test group's options to the provided list. 
     * deletes all previous options
     * @param options 
     */
    public void setOptions(List<String> newOptions) {
        //wipes out all previous options.
        this.options.clear();
        //increment through and add the new options 
        for(String o : newOptions) {
            addOption(o);
        }
    }
    
    /**
     * Adds a new TestOption
     * @param newOption String
     */
    public void addOption(String newOption) {
        this.options.add(newOption);
        this.numberOfOptions = this.options.size();
    }

}

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
    private List<Integer> usesCounter;


    /**
     * Default constructor. creates an empty TestOptionGroup
     */
    public TestOptionGroup(){
        this.name="";
        this.description = "";
        this.numberOfOptions = 0;
        this.options = new ArrayList<String>();
        this.usesCounter = new ArrayList<Integer>();
    }
    
    /**
     * Constructor where name and description are provided
     * @param newName String
     * @param newDescription String
     */
    public TestOptionGroup(String newName, String newDescription){
        this.name = newName;
        this.description = newDescription;
        this.options = new ArrayList<String>();
        this.usesCounter = new ArrayList<Integer>();
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
        setUses(newOptions.size());
    }
    
    /**
     * Adds a new TestOption, sets the usesCounter array to 0 for the new option.
     * @param newOption String
     */
    public void addOption(String newOption) {
        this.options.add(newOption);
        this.numberOfOptions = this.options.size();
        int i = this.options.indexOf(newOption);
        this.usesCounter.add(i, 0);
    }


    /**
     * returns the usesCounter array
     * @return 
     */
    public List<Integer> getUsesCounter() {
        return this.usesCounter;
    }
    
    /**
     * Returns the Option at a given index AND increments the "usesCounter" array for that
 option
     * @param index 
     */
    public String useOption(int index){
        //is someoen trying to access an out of bounds address?
        //HELP not sure how arrayList size works if some addresses are null
        String option = "";
        if(this.options.size() > index) {
            return null;
        } else { // if not, proceed
            option = this.options.get(index);
            if (option != null)
                this.usesCounter.set(index, (this.usesCounter.get(index)+1));
            else
                return null;
        }
        return option;
    }
    
    public int getUsesOfOptionAtIndex(int index){
        if (this.usesCounter != null) {
            return this.usesCounter.get(index);
        } else {
            return 0;
        }
    }
    

    /**
     * private method to reset all usesCounter of each option to zero
     *
     */
    private void setUses(int size){
        this.usesCounter = new ArrayList();
        for(int i=0; i< size ;i++) {
            this.usesCounter.add(i, 0);
        }
    }
    //*/
}

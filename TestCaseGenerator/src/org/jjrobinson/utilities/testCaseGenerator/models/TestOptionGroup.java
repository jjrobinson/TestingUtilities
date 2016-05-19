/*
 */

package org.jjrobinson.utilities.testCaseGenerator.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jason Robinson
 */
public class TestOptionGroup implements Cloneable {
    private String name;
    private String description;
    private int numberOfOptions;
    private ArrayList<String> options;
    private ArrayList<Integer> usesCounter;


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
     * Override for clone from object class.  Returns a new object with identical 
     * properties with deep copy cloning.
     * @return 
     */
    @Override
    public Object clone() throws CloneNotSupportedException{
        String newName = new String(this.name);
        String newDescription = new String(this.description);
        List<String> newOptions = new ArrayList<String>();
        for(String s:this.options)
            newOptions.add(s);
        List<Integer> newUsesCounter = new ArrayList<Integer>();
        for(Integer i: this.usesCounter)
            newUsesCounter.add(i);

        
        TestOptionGroup clonedTOG = (TestOptionGroup)super.clone();
        
        clonedTOG.setName(newName);
        clonedTOG.setDescription(newDescription);
        clonedTOG.setOptions(newOptions);
        
        return clonedTOG;
    }
    
    /**
     * returns the name
     * @return name String
     */
    public String getName() {
        return this.name;
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
        return this.description;
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
        return this.numberOfOptions;
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
        resetUses(newOptions.size());
        this.numberOfOptions = this.options.size();
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
     * Returns the Option at a given index AND increments the "usesCounter" 
     * array for that option
     * @param index 
     */
    public String useOption(int index){
        //is someoen trying to access an out of bounds address?
        String option;
        //is index larger than the size of the list
        if(this.options.size() < index) {
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

    
    public String getAndUseLeastUsedOption() throws Exception {
        return useOption(getIndexOfLeastUsedOption());
    }
    
    

    /**
     * Returns the index of the option that has the least uses. If there is a tie,
     * the first option will be returned, since options that tie are skipped.
     * If the list has no items, null is returned.  If a list has only one item,
     * then the index for that item is returned (which is also 0).
     * @return int index
     * @throws Exception generic exception.
     */
    public int getIndexOfLeastUsedOption() throws Exception {
        int leastUsedCount;
        int leastUsedIndex;
        if(this.options.size() == 0){
            throw new Exception("List is empty. Cannot return the index of item with the least uses.");
        } else if (this.options.size() == 1){
            leastUsedCount = this.usesCounter.get(0);
            leastUsedIndex = 0;
        }else {
            leastUsedCount = this.usesCounter.get(0);
            leastUsedIndex = 0;
            int temp;
            for (int i=0; i< this.options.size();i++){
                temp = this.usesCounter.get(i);
                if (leastUsedCount> temp) {
                    leastUsedCount = temp;
                    leastUsedIndex = i;
                }
            }
        }
        return leastUsedIndex;
    }
    
    
    /**
     * Sets the TestGroupOption at the given index, if the list is large enough.
     * Returns false if the list is not large enough to set the index.
     * @param index int
     * @param newOption String
     * @return boolean success or failure
     */
    public boolean setOptionAtIndex(int index, String newOption){
        if(index < this.options.size()){
            this.options.set(index, newOption);
            return true;
        } else {
            return false;
        }
    }
    
    

    /**
     * private method to reset all usesCounter of each option to zero
     *
     */
    private void resetUses(int size){
        this.usesCounter = new ArrayList();
        for(int i=0; i< size ;i++) {
            this.usesCounter.add(i, 0);
        }
    }
    
    /**
     * Override the default object toString for better looking output
     * @return 
     */
    @Override
    public String toString(){
        String NEW_LINE = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        sb.append("TestOptionGroup").append(NEW_LINE);
        sb.append("\tName: ").append(this.name).append(NEW_LINE);
        sb.append("\tDescription: ").append(this.description).append(NEW_LINE);
        sb.append("\tOptions: {");
        for(String s: this.options){
            sb.append(" [").append(s).append("]");
        }
        sb.append("}").append(NEW_LINE);
        return sb.toString();
    }

}

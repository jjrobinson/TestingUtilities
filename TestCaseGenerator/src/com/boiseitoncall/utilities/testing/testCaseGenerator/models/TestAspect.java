/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boiseitoncall.utilities.testing.testCaseGenerator.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JasonRobinson
 */
public class TestAspect {
    private String name;
    //private String description;
    private int numberOfOptions;
    private List<String> options;

    /**
     * Default constructor
     */
    public TestAspect() {
        name = "";
        //description = "";
        numberOfOptions = 0;
        options = new ArrayList<String>();
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
        this.numberOfOptions = numOfOptions;
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
    public int getNumberOfOptions() {
        return numberOfOptions;
    }

    /**
     * Sets the number of options in this aspect. NOT PREFERRED.  
     * Instead use addOption, which adds the option and auto-increments the numberOfOptions value
     * @param numberOfOptions 
     */
    public void setNumberOfOptions(int numberOfOptions) {
        this.numberOfOptions = numberOfOptions;
    }

    /**
     * Returns a list of Options.
     * @return List options
     */
    public List<String> getOptions() {
        return options;
    }

    /**
     * Sets the Aspect's options list to the supplied List of options (strings)
     * @param options 
     */
    public void setOptions(List<String> options) {
        this.options = options;
    }

    /*
     * Returns the aspect's Description
     * @return 
     *
    public String getDescription() {
        return description;
    }
   
    /*
     * Sets the aspect's description.
     * @param description 
     *
    public void setDescription(String description) {
        this.description = description;
    }    
    */
    
    /**
     * Adds a new Option to the Aspect and auto-increments the numberOfOptions
     * @param newOption 
     */
    public void addOption(String newOption){
        this.options.add(newOption);
    }

    /**
     * clears out all options and resets the numberOfOptions counter to 0.
     */
    public void clearOptions(){
        this.options.clear();
        this.numberOfOptions = 0;
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
        result.append("Number of Options: " +this.getNumberOfOptions() + NEW_LINE);
        result.append("Option Names: ");
        List options = new ArrayList(getOptions());
        for (int i = 0 ; i < options.size() ; i++) {
                result.append("\"" + options.get(i).toString() + "\", ");
        }


        result.append(NEW_LINE + "}");

        return result.toString(); //To change body of generated methods, choose Tools | Templates.
    }
	
}

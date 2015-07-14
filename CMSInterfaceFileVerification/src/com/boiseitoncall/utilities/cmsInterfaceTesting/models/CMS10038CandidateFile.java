/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boiseitoncall.utilities.cmsInterfaceTesting.models;


import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author JasonRobinson
 */
public class CMS10038CandidateFile implements InterfaceFileInterface {
    private String interfaceType;
    private String state;
    private String fileName;
    private List<String> lines;
    private String headerLine;
    private String trailerLine;
    private boolean hasHeaderLine;

    /**
     * returns if this interface has a header line
     * @return 
     */
    public boolean hasHeaderLine() {
        return hasHeaderLine;
    }

    /**
     * sets the logic for if this interface candidate has a header line
     * @param hasHeaderLine 
     */
    public void setHasHeaderLine(boolean hasHeaderLine) {
        this.hasHeaderLine = hasHeaderLine;
    }


    /**
     * Constructor
     */
    public CMS10038CandidateFile() {
        this.interfaceType = "10038";
    }
	
    /**
     * Returns that this is a "10038" interface file
     * @return 
     */
    public String getInterfaceType(){
        return this.interfaceType;
    }
    
    /**
     * returns what state the interface file is for
     * @return 
     */
    public String getState(){
        return this.state;
    }
    
    /**
     * returns the string of the name of the interface file.
     * @return 
     */
    public String getFileName(){
        return this.fileName;
    }

    /**
     * Returns the string of the first header line found
     * @return 
     */
    public String getHeaderLine() {
        return headerLine;
    }

    /**
     * returns the string of the last trailer line found
     * @return 
     */
    public String getTrailerLine() {
        return trailerLine;
    }


    /**
     * sets the header line string
     * @param headerLine 
     */
    public void setHeaderLine(String headerLine) {
        this.headerLine = headerLine;
    }

    /**
     * sets the trailer line string
     * @param trailerLine 
     */
    public void setTrailerLine(String trailerLine) {
        this.trailerLine = trailerLine;
    }
    
    /**
     * Sets what state the 10038 file represents
     * @param newState 
     */
    public void setState(String newState){
        this.state = newState;
    }
    
    /**
     * sets the string of the interface file name
     * @param newFileName 
     */
    public void setFileName(String newFileName){
        this.fileName = newFileName;
    }

    /**
     * returns all lines of the interface file
     * @return 
     */
    public List<String> getLines() {
        return lines;
    }

    /**
     * sets all the lines of the interface file
     * There is no incremental add 1 line method
     * @param lines 
     */
    public void setLines(List<String> lines) {
        this.lines = lines;
    }   
}
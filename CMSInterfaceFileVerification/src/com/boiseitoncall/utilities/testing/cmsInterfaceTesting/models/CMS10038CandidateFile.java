/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boiseitoncall.utilities.testing.CMSInterfaceTesting.models;


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


    public CMS10038CandidateFile() {
        this.interfaceType = "10038";
    }
	
    
    public String getInterfaceType(){
        return this.interfaceType;
    }
    
    public String getState(){
        return this.state;
    }
    
    public String getFileName(){
        return this.fileName;
    }

    public String getHeaderLine() {
        return headerLine;
    }

    public String getTrailerLine() {
        return trailerLine;
    }


    
    public void setHeaderLine(String headerLine) {
        this.headerLine = headerLine;
    }

    public void setTrailerLine(String trailerLine) {
        this.trailerLine = trailerLine;
    }
    
    
    public void setState(String newState){
        this.state = newState;
    }
    
    public void setFileName(String newFileName){
        this.fileName = newFileName;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
        
    
    
    
    
    
    
    public boolean checkForProblems(CandidateInterfaceFile candidate){
        return true;
    }
    
    public boolean checkFileName(){
        
        
        return true;
    }
    
    
        
}

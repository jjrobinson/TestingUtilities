/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boiseitoncall.utilities.testing.CMSInterfaceTesting.models.rules;


import com.boiseitoncall.utilities.testing.CMSInterfaceTesting.models.CandidateInterfaceFile;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jason Robinson
 */
public class CMS10038Rules implements InterfaceFileRuleInterface {
    private List<InterfaceFileRecordRule> recordRules;
    private String fileName;
    private String interfaceType;
    private String state;
    private String fileNameRegEx;
    private Pattern fileNamePattern;
    private Matcher fileNameMatcher;
    private List<String> errors;

    public CMS10038Rules(String newState) {
        this.interfaceType = "10038";
        this.state = newState;
    }
    
    public CMS10038Rules() {
        this.interfaceType = "10038";
        this.state = new String();
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<InterfaceFileRecordRule> getRecordRules() {
        return this.recordRules;
    }

    /**
     *
     * @return
     */
    @Override
    public String getFileName(){
        return this.fileName;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getInterfaceType() {
        return this.interfaceType;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getState() {
        return this.state;
    }
    
    /**
     *
     * @return
     */
    public String getFileNameRegEx() {
        return this.fileNameRegEx;
    }

    /**
     *
     * @return
     */
    public Pattern getFileNamePattern() {
        return this.fileNamePattern;
    }

    
    public List<String> getErrors() {
        return this.errors;
    }
    
    /**
     *
     * @param recordRules
     */
    @Override
    public void setRecordRules(List<InterfaceFileRecordRule> recordRules) {
        this.recordRules = recordRules;
    }

    /**
     *
     * @param interfaceName
     */
    public void setInterfaceName(String interfaceName) {
        this.interfaceType = interfaceName;
    }

    /**
     *
     * @param state
     */
    @Override
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @param fileNameRegEx
     */
    public void setFileNameRegEx(String fileNameRegEx) {
        this.fileNameRegEx = fileNameRegEx;
    }
    
    /**
     *
     * @param newName
     */
    @Override
    public void setFileName(String newName){
        this.fileName = newName;
    }
    
    /**
     *
     * @param fileNamePattern
     */
    public void setFileNamePattern(Pattern fileNamePattern) {
        this.fileNamePattern = fileNamePattern;
    }

    /**
     *
     * @return
     */
    public Matcher getFileNameMatcher() {
        return fileNameMatcher;
    }

    /**
     *
     * @param fileNameMatcher
     */
    public void setFileNameMatcher(Matcher fileNameMatcher) {
        this.fileNameMatcher = fileNameMatcher;
    }

    public RecordFieldRule getHeaderRule(){

        //a place to store all the rules we find
        ArrayList<Integer> foundHeaderRules = new ArrayList<Integer>();
        
        //iterate through all the rules, and find the one with
        //"first" as the required line position
        for(int i = 0 ; i < this.recordRules.size() ; i ++) {
            if (recordRules.get(i).getPosition().equalsIgnoreCase("first")){
                //found a rule that says it is a header rule
                foundHeaderRules.add(i);
            } else {
                //this rule is not a header rule.  move on
            }
        } // finished parsing throuhg all the rules
        
        //check to see if the foundHeaderRules list is larger than 1 entry
        //if so, throw an error, the Rules file is nto formatted correctly!
        //we can only have one header file rule.
        int numberFound = foundHeaderRules.size();
        if (numberFound == 0) {
            //error didn't find any rules that are a header
        } else {
            //found the header rule
        }
        
        return null;
    }
    
    
    public RecordFieldRule getFooterRule(){

        //a place to store all the rules we find
        ArrayList<Integer> foundFooterRules = new ArrayList<Integer>();
        
        //iterate through all the rules, and find the one with
        //"first" as the required line position
        for(int i = 0 ; i < this.recordRules.size() ; i ++) {
            if (recordRules.get(i).getPosition().equalsIgnoreCase("last")){
                //found a rule that says it is a header rule
                foundFooterRules.add(i);
            } else {
                //this rule is not a header rule.  move on
            }
        } // finished parsing throuhg all the rules
        
        //check to see if the foundHeaderRules list is larger than 1 entry
        //if so, throw an error, the Rules file is nto formatted correctly!
        //we can only have one header file rule.
        int numberFound = foundFooterRules.size();
        if (numberFound == 0) {
            //error didn't find any rules that are a header
        } else {
            //found the header rule
        }
        
        return null;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     *
     * @return
     */
    @Override
    public boolean checkFileName(){
        return true;
    }    
    
    /**
     *
     * @param candidate
     * @return
     */
    @Override
    public boolean checkForProblems(CandidateInterfaceFile candidate){
        
        int candidateNumberOfLines = candidate.getNumberOfLines();
        for (int i = 0 ; i< candidateNumberOfLines ; i++)
        {
            for (int j = 0 ; j <this.recordRules.size() ; j++ ) {
                //check line number for header / trailer
                
                //check each rule
                if (checkLineRules(this.recordRules.get(j),
                        candidate.getLines().get(i),i,
                        candidateNumberOfLines)
                    ) {
                    return true;
                } else {
                    //didn't match any line rules
                    
                    //TODO Add error for didn't match a line type
                    return false;
                }
            }
            
        }
        return true;
    }
    
    
    private boolean checkLineRules(InterfaceFileRecordRule rule, String line, int position, int size){
        
        findLineType(rule, line, position, size);
        checkLinePosition(rule,line,position,size);
        //check length
        
        
        //check each field rule
        
        for(RecordFieldRule fieldRule : rule.getRecordFieldRules()) {
            boolean passes = false;
            //test each field rule against the line.
            
        }
        
        return true;
    }
    
    
    
    private void addError(String e) {
        this.errors.add(e);
    }
    
    /**
     * Checks the position of a given line against the rule for that line
     * @param rule
     * @param line
     * @param position
     * @param size
     * @return 
     */
    private boolean checkLinePosition(InterfaceFileRecordRule rule, String line, int position, int size){
        //check position in the file
        //rules should only allow values of: "first", "last", "other"
        if(rule.getPosition().equalsIgnoreCase("other")) {
            // this rule allows the record to exist anywhere in the file except first or last lines
            
            //is the position first or last lines of the file?
            if(position != 0 || position != (size-1)) {
                //yes, failes the position rule
                
            } else { //not first or last
                //passes the position rule
            }
        } else if (rule.getPosition().equalsIgnoreCase("first")){ 
            // this rule requires the line to be the first line in the file
            
        } else if (rule.getPosition().equalsIgnoreCase("last")){
            //this rule requires the line to be the last line in the file
            if((position+1) == size) {
                //then the position of this line is the last line of the file
                
            }
        } else {
            //error we should never get here!
            //that means the rule file specifies a position other than "first", "last", "other"
            System.out.println("ERROR: The Rule for this line specifies a line position other than \"first\", \"last\", \"other\".");
        }
            //then position doesn't matter
        return false;
    }
    
    
    
    private String findLineType(InterfaceFileRecordRule rule, String line, int position, int size){
        //TODO
        return "";
    }
    
}

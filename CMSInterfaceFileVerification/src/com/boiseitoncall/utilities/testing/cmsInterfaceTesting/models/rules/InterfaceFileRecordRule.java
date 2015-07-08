/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boiseitoncall.utilities.testing.CMSInterfaceTesting.models.rules;

import java.util.ArrayList;

/**
 *
 * @author Jason Robinson
 */
public class InterfaceFileRecordRule {
    private String recordName;
    private int numberOfChars;
    private boolean isRequired;
    private String position;
    private boolean duplicatesAllowed;
    private ArrayList<RecordFieldRule> recordFields;
    
       

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public int getNumberOfChars() {
        return numberOfChars;
    }

    public void setNumberOfChars(int numberOfChars) {
        this.numberOfChars = numberOfChars;
    }

    public boolean isIsRequired() {
        return isRequired;
    }

    public void setIsRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isDuplicatesAllowed() {
        return duplicatesAllowed;
    }

    public void setDuplicatesAllowed(boolean duplicatesAllowed) {
        this.duplicatesAllowed = duplicatesAllowed;
    }

    public ArrayList<RecordFieldRule> getRecordFieldRules() {
        return recordFields;
    }

    public void setRecordFieldRules(ArrayList<RecordFieldRule> recordFields) {
        this.recordFields = recordFields;
    }

}

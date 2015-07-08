/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boiseitoncall.utilities.testing.CMSInterfaceTesting.models.rules;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Jason Robinson
 */
public class RecordFieldRule {
    private String fieldName;
    private String targetColumn;
    private String targetTable;
    private int SourceFieldLength;
    private String pattern;
    private List<String> EnumeratedValidValues;
    private String formatGuide;
    private String Comments;
    private boolean passesAllTests;
    private List<String> errors;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getTargetColumn() {
        return targetColumn;
    }

    public void setTargetColumn(String targetColumn) {
        this.targetColumn = targetColumn;
    }

    public String getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(String targetTable) {
        this.targetTable = targetTable;
    }

    public int getSourceFieldLength() {
        return SourceFieldLength;
    }

    public void setSourceFieldLength(int SourceFieldLength) {
        this.SourceFieldLength = SourceFieldLength;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public List<String> getEnumeratedValidValues() {
        return EnumeratedValidValues;
    }

    public void setEnumeratedValidValues(ArrayList<String> EnumeratedValidValues) {
        this.EnumeratedValidValues = EnumeratedValidValues;
    }

    public String getFormatGuide() {
        return formatGuide;
    }

    public void setFormatGuide(String formatGuide) {
        this.formatGuide = formatGuide;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String Comments) {
        this.Comments = Comments;
    }

    private void addError(String e) {
        this.errors.add(e);
    }
    
    public List<String> getErrors() {
        return this.errors;
    }
    
    
    
    public boolean PassesAllTests(String field){
        if (!checkLength(field)){
            this.passesAllTests = false;
        } else {
            addError("ERROR:");
        }
        return true;
    }
    
    
    
    
    /**
     * Does the string have the right length according to this field's rule?
     * @param candidate String
     * @return boolean
     */
    private boolean checkLength(String candidate){
        if(this.SourceFieldLength == candidate.length())
            return true;
        else
            return false;
    }
}

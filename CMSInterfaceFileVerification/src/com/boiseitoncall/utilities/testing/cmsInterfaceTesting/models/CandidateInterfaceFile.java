/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boiseitoncall.utilities.testing.CMSInterfaceTesting.models;

import java.util.List;

/**
 *
 * @author Jason Robinson
 */
public class CandidateInterfaceFile {
    private String fileName;
    private List<String> lines;
    private int numberOfLines;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
        numberOfLines = lines.size();
    }
    
    public int getNumberOfLines(){
        return numberOfLines;
    }
    
}

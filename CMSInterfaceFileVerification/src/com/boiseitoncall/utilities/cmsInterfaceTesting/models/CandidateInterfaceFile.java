/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boiseitoncall.utilities.cmsInterfaceTesting.models;

import java.util.List;

/**
 *
 * @author Jason Robinson
 */
public class CandidateInterfaceFile implements InterfaceFileInterface {
    private String fileName;
    private List<String> lines;
    private int numberOfLines;
    private String interfaceFileType;
    private String headerLine;
    private boolean hasHeaderLine;

    public boolean hasHeaderLine() {
        return hasHeaderLine;
    }

    public void setHasHeaderLine(boolean hasHeaderLine) {
        this.hasHeaderLine = hasHeaderLine;
    }

    public String getInterfaceFileType() {
        return interfaceFileType;
    }

    public void setInterfaceFileType(String interfaceFileType) {
        this.interfaceFileType = interfaceFileType;
    }

    public String getHeaderLine() {
        return headerLine;
    }

    public void setHeaderLine(String headerLine) {
        this.headerLine = headerLine;
    }

    public String getInterfaceType() {
        return interfaceFileType;
    }

    public void setInterfaceType(String fileType) {
        this.interfaceFileType = fileType;
    }

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

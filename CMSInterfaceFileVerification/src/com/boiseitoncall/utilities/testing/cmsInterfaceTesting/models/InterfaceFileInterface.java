/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boiseitoncall.utilities.testing.CMSInterfaceTesting.models;

import java.util.List;

/**
 * A candidate 10038 interface File.  Candidates don't know the rules,
 * so they do not contain the InterfaceRules, nor do they check to see if
 * they pass the rules.  The RuleInterface performs those actions.
 * @author JasonRobinson
 */
public interface InterfaceFileInterface {
    public String getFileName();
    public String getInterfaceType();
    public List<String> getLines();
    public String getHeaderLine();
    public boolean hasHeaderLine();

    public void setHeaderLine(String newHeader);
    public void setFileName(String newFileName);
    public void setLines(List<String> newLines);
    public void setHasHeaderLine(boolean hasHeader);
    
}

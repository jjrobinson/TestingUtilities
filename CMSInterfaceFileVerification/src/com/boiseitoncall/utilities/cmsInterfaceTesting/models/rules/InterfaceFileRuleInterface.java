/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boiseitoncall.utilities.cmsInterfaceTesting.models.rules;


import com.boiseitoncall.utilities.cmsInterfaceTesting.models.CandidateInterfaceFile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * generic interface for the InterfaceFileRules, so that any kind of 
 * interface file rules can be passed through the InterfaceRuleBuilder.  
 * @author Jason Robinson
 */
public interface InterfaceFileRuleInterface {

    public List<InterfaceFileRecordRule> getRecordRules();
    public String getInterfaceType();
    public String getState();
    public String getFileName();
    public RecordFieldRule getHeaderRule();
    public RecordFieldRule getFooterRule();

    public void setRecordRules(List<InterfaceFileRecordRule> recordRules);
    public void setState(String state);
    public void setFileName(String fileName);
    
    public boolean checkForProblems(CandidateInterfaceFile candidate);
    public boolean checkFileName();
    
}

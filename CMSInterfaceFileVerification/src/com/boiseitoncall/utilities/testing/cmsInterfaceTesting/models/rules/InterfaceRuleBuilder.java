/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boiseitoncall.utilities.testing.CMSInterfaceTesting.models.rules;

import java.io.File;

/**
 * This class gathers the rules needed to assemble a completed rule
 * object for checking an interface file.  It is the only part of the whole 
 * application that knows the complete list of supported interfaces.
 * @author Jason Robinson
 */
public class InterfaceRuleBuilder {
    private File interfaceRuleFile;
    private String interfaceNumber;
    private String state;
    private InterfaceFileRuleInterface rules;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public InterfaceFileRuleInterface getInterfaceRules() {
        return rules;
    }

    public void setInterfaceRules(InterfaceFileRuleInterface InterfaceRules) {
        this.rules = InterfaceRules;
    }

    
    
    public File getInterfaceRuleFile() {
        return interfaceRuleFile;
    }


    public String getInterfaceNumber() {
        return interfaceNumber;
    }

    public void setInterfaceNumber(String interfaceNumber) {
        this.interfaceNumber = interfaceNumber;
    }
    

    public InterfaceFileRuleInterface getInterfaceFileRules(String interfaceNumber, String state) {
        
        //list of supported interfaces
        if (interfaceNumber.equalsIgnoreCase("10038")){
            rules = new CMS10038Rules();
        } else {
            rules = new CMS10038Rules();
        }
        
        if (state.equalsIgnoreCase("id"))
            rules = BuildID10038();
        return rules;
    }
    
    private InterfaceFileRuleInterface BuildID10038(){
        return this.rules;
    }

    
    
}

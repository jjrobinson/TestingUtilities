/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boiseitoncall.utilities.cmsInterfaceTesting.models.rules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

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
    private static final String RESOURCES_LOCATION = "resources/rules/";

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
        System.out.println("DEBUG: Inside getInterfaceFileRules");
        
        //String rulesFile = new String(RESOURCES_LOCATION + state + "/" + interfaceNumber + ".rules");
        String rulesFile = new String("classes/src/resources/rules/id/id10038.rules");
        String rulesDir = new String("classes");
          
        /*
        URL url = InterfaceRuleBuilder.class.getResource(rulesDir);
        if (url == null) {
            // error - missing folder
            System.out.println("Could Not Open InterfaceInterfaceRuleBuilder.class.getResource(rulesDir): \""+rulesDir + "\"");
        } else {
            System.out.println("Could Open InterfaceInterfaceRuleBuilder.class.getResource(rulesDir):\n"+url.toString()
            + "\n" + url.getPath().toString());
            
            try {
                File dir = new File(url.toURI());
                System.out.println("Found Rule Files: ");
                for (File nextFile : dir.listFiles()) {
                // Do something with nextFile
                    System.out.println(nextFile.getName());
                } 
            } catch (Exception e) {
                System.out.println("Could Not Open InterfaceInterfaceRuleBuilder.class.getResource(rulesDir):\n"+url.toString());
            }
        }
        */
        
        
        
        
        
        
        //InterfaceRuleBuilder.class.getResource("/utility.properties");
        try {
            //BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(rulesFile)));
            //InterfaceRuleBuilder.class.getResourceAsStream(rulesFile);
            BufferedReader br = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream(rulesFile)));
            //BufferedReader  bfr = new BufferedReader(new FileReader(new File("config.properties")));
        } catch (Exception e) {
            System.out.println("ERROR: getResourceAsStream failed to open the rules file: " +rulesFile);
            e.printStackTrace();
            System.exit(1);
        } 
        // */
        
        
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

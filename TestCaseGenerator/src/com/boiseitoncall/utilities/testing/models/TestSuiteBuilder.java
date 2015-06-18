package com.boiseitoncall.utilities.testing.models;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *Assembles Test Aspects, based on how it is called.  
 * Currently only supports command line interactions
 * 
 * @author Jason Robinson
 */
public class TestSuiteBuilder {
    /**
     * Prompts user via command line for the properties of the TestAspect
     * @return TestAspect
     */
    public TestAspect getTestAspectCmdLine(){
        TestAspect newAspect = new TestAspect();
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader console = new BufferedReader(isr);

        
        
        
        return newAspect;
    }
    
    
    public boolean checkForNumber(String input) {
        return input.matches("[0-9]+");
        
    }
)
    }
    
    
    
    
    
    
    
    
    
}

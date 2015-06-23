package com.boiseitoncall.utilities.testing;

import com.github.lalyos.jfiglet.FigletFont;
import com.boiseitoncall.utilities.testing.models.TestAspect;
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

        
        DisplayBannerCmdLine();
        
        
        return newAspect;
    }
    
    
    public boolean createTestSuiteCmdLine(int numberOfTestAspects) {
        for (int i = 1 ; i<= numberOfTestAspects ; i++)
        {
            //System.out.println("\tDEBUG: Inside: ComputeTestCases Aspects Loop...");
            try { System.out.println(FigletFont.convertOneLine("Aspect    #" + i)); } catch(Exception e) {}


            //fillInAspect(testSuite, scanner);
            //fillInAspect(testSuite, i);
            testSuite.addAnAspect(testBuilder.getTestAspectCmdLine());


        }
        return true;
    }// end of createTestSuite
    
    
    
    
    
    public boolean checkForNumber(String input) {
        return input.matches("[0-9]+");
        
    }

    
    static void DisplayBannerCmdLine() {
                //String asciiArt= "";
        try {
            //asciiArt = FigletFont.convertOneLine("Test\nCase\nGenerator");
            //asciiArt = FigletFont.convertOneLine("Test\nCase\nGenerator");
            System.out.println(FigletFont.convertOneLine("Test"));
            System.out.println(FigletFont.convertOneLine("Case"));
            System.out.println(FigletFont.convertOneLine("Generator"));
        }catch(Exception e) {
            }
    }
    
}//end of class
    
    
    
    
    
    


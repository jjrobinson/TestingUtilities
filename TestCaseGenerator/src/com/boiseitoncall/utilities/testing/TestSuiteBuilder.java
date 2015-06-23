package com.boiseitoncall.utilities.testing;

import com.boiseitoncall.utilities.testing.models.TestAspect;
import com.boiseitoncall.utilities.testing.models.TestSuite;
import com.github.lalyos.jfiglet.FigletFont;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Fills out the details of a TestSuite.  Currently only supports command line interactions.
 * 
 * @author Jason Robinson
 */
public class TestSuiteBuilder {
	private String builderType;
        private TestSuite suite;
	
	/**
	 * Stores what type of a TestSuite the Builder is creating. Not an enum (yet) TODO
	 * @param newType String
	 */
	public void setBuilderType(String newType) {
		this.builderType = newType;
	}
	
	/**
	 * Returns what interface the builder is using to create the TestSuite.
         * Currently only supports command line
	 * @return 
	 */
	public String getBuilderType(){
		return this.builderType;
	}
	
    /**
     * The main method of this class (not to be confused with main(String args[]).
     * Call this method to create a new TestSuite via the command line.
     * 
     * @param suite
     * @param numberOfTestAspects
     * @return 
     */
    public TestSuite createTestSuiteCmdLine(BufferedReader console) {
        suite = new TestSuite();
        String numAspectsString = new String();
        int numberOfTestAspects = 0;

        //initial prompt to get number of TestAspects
        System.out.print("Enter the number of different Aspects to be tested: ");
        // get their input as a String
        try {
            numAspectsString = console.readLine();
            numberOfTestAspects = Integer.parseInt(numAspectsString);
        } catch(NumberFormatException e)
        {
            System.out.println("ERROR: Non number submitted for 'Number of Aspects': \"" + numAspectsString + "\"");
        }catch(IOException e) {
            System.out.println("ERROR: Could not read from standard input / console." + e);
        }

        
        for (int i = 1 ; i<= numberOfTestAspects ; i++)
        {
            //System.out.println("\tDEBUG: Inside: ComputeTestCases Aspects Loop...");
            try { System.out.println(FigletFont.convertOneLine("Aspect    #" + i)); } catch(Exception e) {}


            //fillInAspect(testSuite, scanner);
            //fillInAspect(testSuite, i);
            suite.addAspect(getTestAspectCmdLine());


        }
        return suite;
    }// end of createTestSuite
    
    
    
    /**
     * The main method of this class (not to be confused with main(String args[]).
     * Call this method to create a new TestSuite via the command line.
     * 
     * @param suite
     * @param numberOfTestAspects
     * @return 
     */
    public boolean createTestSuiteCmdLine(TestSuite suite, int numberOfTestAspects) {
        for (int i = 1 ; i<= numberOfTestAspects ; i++)
        {
            //System.out.println("\tDEBUG: Inside: ComputeTestCases Aspects Loop...");
            try { System.out.println(FigletFont.convertOneLine("Aspect    #" + i)); } catch(Exception e) {}


            //fillInAspect(testSuite, scanner);
            //fillInAspect(testSuite, i);
            suite.addAspect(getTestAspectCmdLine());


        }
        return true;
    }// end of createTestSuite
    
    
    
    
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
    
    
    
    
    
    


package com.boiseitoncall.utilities.testing;

import static com.boiseitoncall.utilities.testing.TestCaseGeneratorMain.testSuite;
import com.boiseitoncall.utilities.testing.models.TestAspect;
import com.boiseitoncall.utilities.testing.models.TestSuite;
import com.github.lalyos.jfiglet.FigletFont;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Fills out the details of a TestSuite.  Currently only supports command line interactions.
 * 
 * @author Jason Robinson
 */
public class TestSuiteBuilder {
    private String builderType;
    private TestSuite suite;
    private static String NEW_LINE = System.getProperty("line.separator");
    /**
     * The main method of this class (not to be confused with main(String args[]).
     * Call this method to create a new TestSuite via the command line.
     * 
     * @param suite
     * @param numberOfTestAspects
     * @return 
     */
    public TestSuite createTestSuiteCmdLine() {
        this.builderType = "command line";
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader console = new BufferedReader(isr);
		
        suite = new TestSuite();
        String numAspectsString = new String();
        int numberOfTestAspects = 0;
        
		try {
			System.out.print("Enter the Name of this TestSuite: ");
            suite.setName(console.readLine());
			System.out.print("Enter the Description of this TestSuite: ");
            suite.setDescription(console.readLine());
		} catch(Exception e) {}
		
		
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
            System.out.println("ERROR: Could not read from standard input / console. " + e);
        }

        
        for (int i = 1 ; i<= numberOfTestAspects ; i++)
        {
            //System.out.println("\tDEBUG: Inside: ComputeTestCases Aspects Loop...");
            try { System.out.println(FigletFont.convertOneLine("Aspect    #" + i)); } catch(Exception e) {}


            //fillInAspect(testSuite, scanner);
            //fillInAspect(testSuite, i);
            suite.addAspect(getTestAspectCmdLine(console, i));
        }
        return suite;
        
    }// end of createTestSuite
    

    /**
     * Prompts user via command line for the properties of the TestAspect
     * @return TestAspect
     */
    public TestAspect getTestAspectCmdLine(BufferedReader console, int aspectNumber){ 
        TestAspect newAspect = new TestAspect();
        String aspectName = new String("");
        //String aspectDescription = new String(""); // removing this for simplicity
        
        System.out.println("DEBUG: in TestSuiteBuilder.getTestAspectCmdLine() method call.");
        int aspectNumberOfOptions = 0;
        List<String> aspectOptions = new ArrayList();
        

        
        //enter a name
        if (aspectName.isEmpty())
        {
            System.out.print("Enter test aspect " +aspectNumber + "'s NAME: ");
            //aspectName = scanner.next();
            try { aspectName = console.readLine().toString(); } catch(Exception e) {};
            newAspect.setName(aspectName);
        }
        
        /* BROKEN will just use name for the description for now.
        //enter a description
        if (aspectDescription.isEmpty())
        {
            System.out.print("\tEnter the test Aspect DESCRIPTION. Hit Enter when done:");
            aspectDescription = scanner.next();
            newAspect.setDescription(aspectDescription);
        }
        */
        //aspectDescription = aspectName;
        newAspect.setDescription(aspectName);
        
        
        if (aspectNumberOfOptions < 1 );
        {
            System.out.print("\tEnter the NUMBER of different options for Aspect " +aspectNumber + " \"" +aspectName 
                    +"\": ");
            try {
            //try { aspectName = console.readLine(); } catch(Exception e) {};

            //aspectNumberOfOptions = Integer.parseInt(scanner.next());
                aspectNumberOfOptions = Integer.parseInt(console.readLine().toString());
            } catch(NumberFormatException e)
            {
                System.out.println("ERROR: Non number submitted for 'Number of Options' for aspect \"" 
                        +aspectName +"\"" + aspectNumberOfOptions);
            } catch (Exception e) { }
            
            //if we have a number and it is larger than 0, then fill in that number of Aspect options
            if (aspectNumberOfOptions > 0)
            {
                newAspect.setNumberOfOptions(aspectNumberOfOptions);
                
                for (int i = 0; i < aspectNumberOfOptions ; i ++)
                {
                    String newOption = new String("");
                    try {
                        System.out.print("\tOption #" + (i+1) + "'s name: ");
                        //newOption = scanner.next();
                        newOption = console.readLine().toString();
                        aspectOptions.add(newOption);
                    } catch(Exception e)
                    {
                    System.out.println("ERROR: Problem reading the option: \"" + newOption + "\"." + NEW_LINE + "EXCEPTION:" + e);
                    }
                }
                
                
            } else { // you entered a number, but it wasn't larger than 0.
                System.out.println("ERROR: You entered a negative or zero value for 'aspectNumberOfOptions': \"" 
                        +aspectNumberOfOptions + "\"");
                //reset to zero and try again.
                aspectNumberOfOptions = 0;
            }// end loop to get Options entered
        }//end entry of Options.
        
        newAspect.setOptions(aspectOptions);
        
        return newAspect;
    } ////////////// end of getTestAspectCmdLine //////////////////////////////////// 
    
    
    /**
     * Simple is numeric type check
     * @param input
     * @return 
     */
    public boolean checkForNumber(String input) {
        return input.matches("[0-9]+");
        
    }

    /**
     * Displays a giant banner to make reading easier
     */
    public void DisplayBannerCmdLine() {
                //String asciiArt= "";
        try {
            //asciiArt = FigletFont.convertOneLine("Test" + NEW_LINE + "Case" + NEW_LINE + "Generator");
            //asciiArt = FigletFont.convertOneLine("Test" + NEW_LINE + "Case" + NEW_LINE + "Generator");
            System.out.println(FigletFont.convertOneLine("Test"));
            System.out.println(FigletFont.convertOneLine("Case"));
            System.out.println(FigletFont.convertOneLine("Generator"));
        }catch(Exception e) {
            }
    }

    /**
     * Stores what type of a TestSuite the Builder is creating.
     * Just a String field for now. 
     * TODO implement as an enum?
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

    
}//end of class
    
    
    
    
    
    


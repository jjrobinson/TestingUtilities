package com.boiseitoncall.utilities.testCaseGenerator;

import com.boiseitoncall.utilities.testCaseGenerator.models.TestAspect;
import com.boiseitoncall.utilities.testCaseGenerator.models.TestOptionGroup;
import com.boiseitoncall.utilities.testCaseGenerator.models.TestSuite;
import com.github.lalyos.jfiglet.FigletFont;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * Fills out the details of a TestSuite.  Currently only supports command line interactions.
 * 
 * @author Jason Robinson
 */
public class TestSuiteBuilder {
    private String builderType;
    private TestSuite testSuite;
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
		
        testSuite = new TestSuite();
        String numAspectsString = new String();
        int numberOfTestAspects = 0;
        
        //enter a name
        testSuite.setName(getAStringCmdLine(console, "Enter a NAME for this Test Suite",""));
        
        //enter a description
        testSuite.setDescription(getAStringCmdLine(console, "Enter a DESCRIPTION for this Test Suite",""));
        
	
		
        //initial prompt to get number of TestAspects

        numberOfTestAspects = getAPositiveNumber(console,0, "Enter the NUMBER of different Aspects to be tested");
        

        
        for (int i = 0 ; i< numberOfTestAspects ; i++)
        {
            //System.out.println("\tDEBUG: Inside: ComputeTestCases Aspects Loop...");
            String num = String.valueOf(i+1);
            try { System.out.println(FigletFont.convertOneLine("Aspect    #" + num)); } catch(Exception e) {}

            testSuite.addAspect(getTestAspectCmdLine(console, num));
        }
        return testSuite;
        
    }// end of createTestSuite
    

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

    
   /////////////////////////////////////////////////////////////////////////////
   /////  P R I V A T E   M E T H O D S   B E L O W  ///////////////////////////
   /////////////////////////////////////////////////////////////////////////////
    /**
     * Prompts user via command line for the properties of the TestAspect
     * @param console
     * @param num String
     * @return 
     */
    private TestAspect getTestAspectCmdLine(BufferedReader console, String num){
        //initialize the new TestAspect
        TestAspect newAspect = new TestAspect();
        int numberOfOptionGroups = 0;
        
        //enter an aspect's name
        newAspect.setName(
                getAStringCmdLine(console, "\tEnter a NAME for test aspect ",num));
        
        
        //enter an aspect's description
        newAspect.setDescription(
                getAStringCmdLine(console, "\tEnter a DESCRIPTION for test aspect ",num));
        
        //get the number of TestOptionGroups
        StringBuilder sb = new StringBuilder();
        sb.append("Enter the NUMBER of TestOption Groups for TestAspect ").append(num);
        numberOfOptionGroups = getAPositiveNumber(console, 1, sb.toString());

        //fill in the details of each option group
        for (int i=0;i< numberOfOptionGroups; i++) {
            
            //banner for the new OptionGroup
            try { System.out.println(
                FigletFont.convertOneLine("        Option Group    #"+(i+1))); 
            } catch(Exception e) {}

            //prompt to fill in each option group
            newAspect.addOptionGroup(getTestOptionGroupCmdLine(console, String.valueOf(i)));
        }

        return newAspect;
    } ////////////// end of getTestAspectCmdLine //////////////////////////////////// 
 
   
    /**
     * Prompts to fill in the information for a TestOptionGroup. 
     * ONLY called by getTestAspectCmdLine
     * 
     * @param console BufferedReader
     * @param optionGroupNumber int
     * @return TestOptionGroup tog
     */
    private TestOptionGroup getTestOptionGroupCmdLine(BufferedReader console, String num) {
        TestOptionGroup tog = new TestOptionGroup();
        String name = new String();
        String desc = new String();
        int numberOfOptions = 0;
        int groupNumber = Integer.valueOf(num).intValue()+1;
        String groupNumberAsString = String.valueOf(groupNumber);
        
        //enter a name
        name = getAStringCmdLine(console, "\t\tEnter a NAME for Test Option Group ", groupNumberAsString);
        tog.setName(name);
        
        //enter a description
        desc = getAStringCmdLine(console, "\t\tEnter a DESCRIPTION for the Test Option Group ", groupNumberAsString);
        tog.setDescription(desc);

        
        //fill in the details of each option group

        StringBuilder sb = new StringBuilder();
        sb.append("Enter the NUMBER of different Options in TestOptionGroup \"").append(name).append("\"");

        numberOfOptions = getAPositiveNumber(console, 2, sb.toString());


        //now we have the name, desc, and number of options in this group
        //time to fill in the options
        for(int i=0; i<numberOfOptions;i++) {
            StringBuilder s = new StringBuilder();
            s.append("\t\t\tEnter a value for OptionGroup "+groupNumber+ ": \"").append(name).append("\" - Option #").append((i+1));
            tog.addOption(getAStringCmdLine(console,s.toString(),""));
        }

        return tog;
    }
    
 
    /**
     * Prompts user for input from the console with a leading description
     * @param console BufferedReader
     * @param desc String leading string
     * @param number String further description
     * @return 
     */
    private String getAStringCmdLine(BufferedReader console, String desc, String number) {
        System.out.print(desc +number +": ");
        try { return console.readLine().toString(); 
        } catch(Exception e) {return "";}
    }
    

    /**
     * Prompts the user for a number and provides a description. Intents by depth
     * @param console BufferedReader
     * @param depth int how many tabs to indent
     * @param desc String
     * @return 
     */
    private int getAPositiveNumber(BufferedReader console, int depth, String desc) {
        String spacingDepth = StringUtils.repeat("\t", depth);
        //System.out.print("\tEnter the NUMBER of different Aspect TestOptionGroups for Aspect " 
        int theNumber = 0;
        while(theNumber == 0) {
            System.out.print(spacingDepth +desc+": ");
            try {
                theNumber = Integer.parseInt(console.readLine().toString());
            } catch(NumberFormatException e)
            {
                System.out.println(spacingDepth+"ERROR: Non number submitted for : \""+desc +"\"");
                theNumber = 0;
            //catch everything else
            } catch (Exception e) { e.printStackTrace();                
                theNumber = 0;
            }

            //we finally got a number, but it is zero or negative
            if(theNumber < 1) {
                System.out.println(spacingDepth+"ERROR: You entered a negative or zero value for \""
                        +desc +"\"  Try again.");
                //reset to zero and try again.
                theNumber = 0;
            }
        }
        return theNumber;

    }
}//end of class
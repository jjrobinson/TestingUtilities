package com.boiseitoncall.utilities.testCaseGenerator;

import com.boiseitoncall.utilities.testCaseGenerator.models.TestAspect;
import com.boiseitoncall.utilities.testCaseGenerator.models.TestOptionGroup;
import com.boiseitoncall.utilities.testCaseGenerator.models.TestSuite;
import com.github.lalyos.jfiglet.FigletFont;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.management.RuntimeErrorException;
import org.apache.commons.lang3.StringUtils;

/**
 * Fills out the details of a TestSuite.  Currently only supports command line interactions.
 * 
 * @author Jason Robinson
 */
public class TestSuiteBuilder {
    private String builderType;
    private TestSuite testSuite;

    /**
     * The main method of this class (not to be confused with main(String args[]).
     * Call this method to create a new TestSuite via the command line.
     * @throws Exception e
     * @return TestSuite 
     */
    public TestSuite createTestSuiteCmdLine() throws Exception{
        this.builderType = "command line";
        int numberOfTestAspects = 0;
        //to get FindBugs to stop complaining about DM_DEFAULT_ENCODING, providing the encoding type
        
        try{
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));

            testSuite = new TestSuite();


            //enter a name
            testSuite.setName(getAStringCmdLine(console, 
                    "Enter a NAME for this Test Suite",""));

            //enter a description
            testSuite.setDescription(getAStringCmdLine(console, 
                    "Enter a DESCRIPTION for this Test Suite",""));

            //initial prompt to get number of TestAspects
            numberOfTestAspects = getAPositiveNumber(console,0, 
                    "Enter the NUMBER of different Aspects to be tested");

            for (int i = 0 ; i< numberOfTestAspects ; i++)
            {
                String num = String.valueOf(i+1);
                try { System.out.println(
                        FigletFont.convertOneLine("Aspect    #" + num)); 
                } catch(Exception e) {
                throw e;}

                testSuite.addAspect(getTestAspectCmdLine(console, num));
            }
            return testSuite;
        
        } catch (Exception e) {
            //can't read from console, so throw an error and quit
            throw new RuntimeErrorException(new Error());
        }
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
     * @throws Exception e
     */
    public void displayBannerCmdLine() throws Exception {
        try { 
            System.out.println(FigletFont.convertOneLine("Test"));
            System.out.println(FigletFont.convertOneLine("Case"));
            System.out.println(FigletFont.convertOneLine("Generator"));
        } catch(Exception e) { 
            throw e;
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
     * @throws Exception for output via FigletFont
     * @param r
     * @param num String
     * @return 
     */
    private TestAspect getTestAspectCmdLine(
            BufferedReader r, String num) throws Exception{
        //initialize the new TestAspect
        TestAspect newAspect = new TestAspect();
        int numberOfOptionGroups = 0;
        
        //enter an aspect's name
        newAspect.setName(getAStringCmdLine(
                r, "\tEnter a NAME for test aspect ",num));
        
        
        //enter an aspect's description
        newAspect.setDescription(getAStringCmdLine(
                r, "\tEnter a DESCRIPTION for test aspect ",num));
        
        //get the number of TestOptionGroups
        StringBuilder sb = new StringBuilder();
        sb.append("Enter the NUMBER of TestOption Groups for TestAspect ")
                .append(num);
        numberOfOptionGroups = getAPositiveNumber(r, 1, sb.toString());

        //fill in the details of each option group
        for (int i=0;i< numberOfOptionGroups; i++) {
            
            //banner for the new OptionGroup
            try { System.out.println(
                FigletFont.convertOneLine("        Option Group    #"+(i+1))); 
            } catch(Exception e) { throw e;}

            //prompt to fill in each option group
            newAspect.addOptionGroup(getTestOptionGroupCmdLine(r, 
                    String.valueOf(i)));
        }

        return newAspect;
    } ////////////// end of getTestAspectCmdLine //////////////////////////////////// 
 
   
    /**
     * Prompts to fill in the information for a TestOptionGroup. 
     * ONLY called by getTestAspectCmdLine
     * 
     * @param r BufferedReader
     * @param optionGroupNumber int
     * @return TestOptionGroup tog
     */
    private TestOptionGroup getTestOptionGroupCmdLine(BufferedReader r, 
            String num) throws Exception {
        int numberOfOptions = 0;
        int groupNumber = Integer.parseInt(num)+1;
        String groupNumberAsString = String.valueOf(groupNumber);
        
        //enter a name
        String name = getAStringCmdLine(r, 
                "\t\tEnter a NAME for Test Option Group ", groupNumberAsString);
        
        //enter a description
        String desc = getAStringCmdLine(r, 
                "\t\tEnter a DESCRIPTION for the Test Option Group ", 
                groupNumberAsString);
        TestOptionGroup tog = new TestOptionGroup(name, desc);

        
        //fill in the details of each option group

        StringBuilder sb = new StringBuilder();
        sb.append("Enter the NUMBER of different Options in TestOptionGroup \"")
                .append(name).append("\"");

        numberOfOptions = getAPositiveNumber(r, 2, sb.toString());


        //now we have the name, desc, and number of options in this group
        //time to fill in the options
        for(int i=0; i<numberOfOptions;i++) {
            StringBuilder s = new StringBuilder();
            s.append("\t\t\tEnter a value for OptionGroup ").append(groupNumber)
                    .append(": \"").append(name).append("\" - Option #")
                    .append((i+1));
            tog.addOption(getAStringCmdLine(r,s.toString(),""));
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
    private String getAStringCmdLine(
            BufferedReader console, String desc, String number) throws Exception{
        System.out.print(desc +number +": ");
        try { 
            return console.readLine();
        } catch(Exception e) { 
            throw e;
        }


    }
    

    /**
     * Prompts the user for a number and provides a description. Intents by depth
     * @throws Exception e
     * @param r BufferedReader
     * @param depth int how many tabs to indent
     * @param desc String
     * @return 
     */
    private int getAPositiveNumber(BufferedReader r, int depth, String desc) 
            throws Exception{
        String spacingDepth = StringUtils.repeat("\t", depth);
        int theNumber = 0;
        while(theNumber == 0) {
            System.out.print(spacingDepth +desc+": ");
            try {
                theNumber = Integer.parseInt(r.readLine());
            } catch(NumberFormatException e)
            {
                System.out.println(spacingDepth+
                        "ERROR: Non number submitted for : \""+desc +"\"");
                theNumber = 0;
            //catch everything else
            } catch (Exception e) { 
                
                theNumber = 0;
            }

            //we finally got a number, but it is zero or negative
            if(theNumber < 1) {
                System.out.println(spacingDepth
                        +"ERROR: You entered a negative or zero value for \""
                        +desc +"\"  Try again.");
                //reset to zero and try again.
                theNumber = 0;
            }
        }
        return theNumber;

    }
}//end of class
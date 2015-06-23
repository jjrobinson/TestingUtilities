package com.boiseitoncall.utilities.testing;

import com.boiseitoncall.utilities.testing.models.TestAspect;
import com.boiseitoncall.utilities.testing.models.TestSuite;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TestCaseGenerator {
    /**
     * @param args the command line arguments
     */
/*
        public static ArrayList<String> procedures = new ArrayList<String>();
        public static ArrayList<String> plan_variations = new ArrayList<String>();
        public static ArrayList<String> mmcp_participation_variations = new ArrayList<String>();
        public static ArrayList<String> prior_auth_variations = new ArrayList<String>();
*/
  
    public static TestSuite testSuite;

    public static void main(String[] args) {
        ArrayList<String> procedures = new ArrayList<String>();
        ArrayList<String> plan_variations = new ArrayList<String>();
        ArrayList<String> mmcp_participation_variations = new ArrayList<String>();
        ArrayList<String> prior_auth_types = new ArrayList<String>();
      
        printUsage();
        
        //Call to function to get all user input
        List<List> input = getInput();
        
        
        //call function to iterate and create the test cases
        //using generic Object instead of List<String>
        List testCases = new ArrayList();
        //ComputeTestCases(input);

        //print to screen all the test cases
        //Object has a toString() so we don't need to specify that this is actually string object.
        for(Object test : testCases) {
            System.out.println(test);
        }
        
        
        
    }//end main(Args)

    
    /**
     *Prints a usage statement at the top of each run.
     */
    public static void printUsage(){
        System.out.println("Test Case Generator.");
        System.out.println("Example: To test 2D CGI shapes we have 1) border color 2) Number of Sides 3) fill color .... \nWhich is 3 different aspects.");
        System.out.println("For our 3 Aspects:");
        System.out.println("\tAspect #1 Options (Border Color): black, brown, blue = 3 different border colors.");
        System.out.println("\tAspect #1 Options (Number of Sides): 3 (triangle), 4, (rectangle), 5 (pentagon) = 3 different shapes.");
        System.out.println("\tAspect #1 Options (Fill Colors): red, green, yellow = 3 different fill colors.");
        System.out.println("\tTotal Test Cases: #BorderColors x #Sides x #FillColors = 3 x 3 x 3 = 27\n");
    }
    
    
    
    /**
     * Prompts user for all input.  Once input has been completed and the user tells the program to "generate"
     * Then the method returns a list of all the input.
     * @return 
     */
    public static List getInput() {
        System.out.println("\tDEBUG: Inside: getInput...");
        /*
        ArrayList<String> procedures = new ArrayList<String>();
        ArrayList<String> plan_variations = new ArrayList<String>();
        ArrayList<String> mmcp_participation_variations = new ArrayList<String>();
        ArrayList<String> prior_auth_types = new ArrayList<String>();
        
        String[] tmpProcedures = new String[] {"H2000","97537","H2032","H2011","H2011 HM"};
        String[] tmpRates = new String[] {"14", "15", "17", "41", "42", "43", "51", "52", "54", "56", "83", "85"};
        String[] tmpMMCPstatus = new String[] {"Prospective MMCP", "MMCP","Non-MMCP"};
        String[] tmpPAdates = new String[] {"current dated", "future dated"};
        */
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader console = new BufferedReader(isr);

        
        TestSuiteBuilder BobTheBuilder = new TestSuiteBuilder();
              
        BobTheBuilder.DisplayBannerCmdLine();
        
        testSuite = BobTheBuilder.createTestSuiteCmdLine(console);
        
        
        return null;
    } // end input method
    

    /**
     * Method to get input from the user for one Aspect at a time. 
     * Once the aspect is completed, it is added to the TestSuite
     * 
     * @param suite
     * @param scanner 
     */
    //public static void fillInAspect(TestSuite suite, Scanner scanner)
    public static void fillInAspect(TestSuite suite, int number)
    {
        System.out.println("DEBUG: in fillInAspect method call.");
        String aspectName = new String("");
        //String aspectDescription = new String("");
        int aspectNumberOfOptions = 0;
        List<String> aspectOptions = new ArrayList();
        TestAspect newAspect = new TestAspect();
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader console = new BufferedReader(isr);
        
        
        //enter a name
        if (aspectName.isEmpty())
        {
            System.out.print("Enter test aspect " +number + "'s NAME: ");
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
            System.out.print("\tEnter the NUMBER of different options for Aspect " +number + " \"" +aspectName 
                    +"\"");
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
                        System.out.print("\tOption #" + i + "'s name: ");
                        //newOption = scanner.next();
                        newOption = console.readLine().toString();
                        aspectOptions.add(newOption);
                    } catch(Exception e)
                    {
                    System.out.println("ERROR: Problem reading the option: \"" + newOption + "\".\nEXCEPTION:" + e);
                    }
                }
                
                
            } else { // you entered a number, but it wasn't larger than 0.
                System.out.println("ERROR: You entered a negative or zero value for 'aspectNumberOfOptions': \"" 
                        +aspectNumberOfOptions + "\"");
                //reset to zero and try again.
                aspectNumberOfOptions = 0;
            }// end loop to get Options entered
        }//end entry of Options.

        //now that we have filled in all the info for a TestAspect, add it to the TestSuite
        suite.addAnAspect(newAspect);

  
        
        
        
    }// end fillInAspect method
    
    
    
    
    
    /**
     * Takes the user input and generates the output test cases
     */
    public static List<String> ComputeTestCases(List<List> inputs) {
        int testCaseNumber = 0;
        List testCases = new ArrayList();
        System.out.println("\tDEBUG: Inside: ComputeTestCases...");
        
        List currentInput = inputs.remove(1);
        
        for (int i =0; i < currentInput.size(); i++){
            testCaseNumber++;
            System.out.println("\tTestCase #" + testCaseNumber + ": \"" + currentInput.get(i) + "\"");
        }
        return testCases;
        } //end function
}//end class

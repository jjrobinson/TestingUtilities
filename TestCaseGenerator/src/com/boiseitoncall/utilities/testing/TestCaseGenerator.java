package com.boiseitoncall.utilities.testing;


import com.boiseitoncall.utilities.testing.models.TestSuite;
import com.boiseitoncall.utilities.testing.models.TestAspect;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.print("Test Case Generator.\nEnter the number of test Aspects to be tested: ");
        // get their input as a String
        String numAspectsString = scanner.next();
        int numberOfAspects = 0;
        try {
            numberOfAspects = Integer.parseInt(numAspectsString);
        } catch(NumberFormatException e)
        {
            System.out.println("ERROR: Non number submitted for 'Number of Aspects': " + numAspectsString);
        }
        
        if (numberOfAspects < 1)
        {
            System.out.println("ERROR: Must supply a number of 1 or larger");
            System.exit(1);
        } else {
            //we have a valid number of aspects
            //create the new testSuite object
            TestSuite testSuite = new TestSuite();
            
            
            for (int i = 1 ; i<= numberOfAspects ; i++)
            {
                //System.out.println("\tDEBUG: Inside: ComputeTestCases Aspects Loop...");
                
                
                fillInAspect(testSuite, scanner);
                


            }
            
        }

        return null;
    } // end input method
    

    /**
     * Method to get input from the user for one Aspect at a time. 
     * Once the aspect is completed, it is added to the TestSuite
     * 
     * @param suite
     * @param scanner 
     */
    public static void fillInAspect(TestSuite suite, Scanner scanner)
    {
        
        String aspectName = "";
        String aspectDescription = "";
        int aspectNumberOfOptions = 0;
        List<String> aspectOptions = null;
        
        
        //enter a name
        if (aspectName.isEmpty())
        {
            System.out.print("Enter the name of the Aspect. Hit Enter when done:");
            aspectName = scanner.next();
        }
        
          
        //enter a description
        if (aspectDescription.isEmpty())
        {
            System.out.print("Enter the name of the Aspect. Hit Enter when done:");
            aspectDescription = scanner.next();
        }
        
        
        if (aspectNumberOfOptions < 1 );
        {
            System.out.print("Enter the number of different options for \"" +aspectName 
                    +"\".  Hit Enter when done.");
            try {
            aspectNumberOfOptions = Integer.parseInt(scanner.next());
            } catch(NumberFormatException e)
            {
                System.out.println("ERROR: Non number submitted for 'Number of Options' for aspect \"" 
                        +aspectName +"\"" + aspectNumberOfOptions);
            }
            
            //if we have a number and it is larger than 0, then fill in that number of Aspect options
            if (aspectNumberOfOptions > 0)
            {
                TestAspect aspect = new TestAspect(aspectName, aspectDescription);
                for (int i = 0; i < aspectNumberOfOptions ; i ++)
                {
                    String newOption = null;
                    try {
                        newOption = scanner.next();
                    } catch(Exception e)
                    {
                    System.out.println("ERROR: Problem reading the option: \"" + newOption + "\".\nESCEPTION:" + e);
                    }
                }
                
                
            } else { // you entered a number, but it wasn't larger than 0.
                System.out.println("ERROR: You entered a negative or zero value for 'aspectNumberOfOptions': \"" 
                        +aspectNumberOfOptions + "\"");
                //reset to zero and try again.
                aspectNumberOfOptions = 0;
            }// end loop to get Options entered
        }//end entry of Options.
        
  
        
        
        
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

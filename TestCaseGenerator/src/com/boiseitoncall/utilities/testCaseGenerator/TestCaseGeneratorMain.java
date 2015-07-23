package com.boiseitoncall.utilities.testCaseGenerator;

import com.boiseitoncall.utilities.testCaseGenerator.models.TestAspect;
import com.boiseitoncall.utilities.testCaseGenerator.models.TestCase;
import com.boiseitoncall.utilities.testCaseGenerator.models.TestOptionGroup;
import com.boiseitoncall.utilities.testCaseGenerator.models.TestSuite;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCaseGeneratorMain {

    public static TestSuite testSuite;
    private static String NEW_LINE = System.getProperty("line.separator");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*
        ArrayList<String> procedures = new ArrayList<String>();
        ArrayList<String> plan_variations = new ArrayList<String>();
        ArrayList<String> mmcp_participation_variations = new ArrayList<String>();
        ArrayList<String> prior_auth_types = new ArrayList<String>();
      */
        printUsageCmdLine();
        
        //Call to function to get all user input
        testSuite = getInputCmdLine();
        
        //call to populate all info from hard coded lists for testing.
        //testSuite = HardCodedVersion();

        System.out.println("TestSuite.toString(): ");
        System.out.println(testSuite.toString());

        //print to screen all the test cases
        //Object has a toString() so we don't need to specify that this is actually a string object.
        System.out.println("COMPUTED: Total Test Cases: " + testSuite.getNumberOfAllTestCases());
        ArrayList<TestCase> testCases = testSuite.getAllTestCases();
        for (int i = 0 ; i < testCases.size(    ) ; i++) {
            System.out.println("COMPUTED\tTest Case #" + (i+1) + ": " + testCases.get(i).getTestOptions().toString());
        }
        
        
        
        /*
        for (ArrayList testCase : testSuite.getAllTestCases()) {
            System.out.println("Test Case #" + testCase.index + testCase.getTestOptions().toString());
        }*/
        
    }//end main(Args)

    
    public static void printTestCasesCmdLine(List<TestCase> allTestCases){
        System.out.println("Printing All Possible Test Cases.");
        for(int i = 0 ; i < allTestCases.size(); i++) {
            System.out.println("Test Case #" + (i+1) + ": " + allTestCases.get(i).getTestOptions().toString());
        }
    }
    

    /**
     *Prints a usage statement at the top of each run.
     */
    public static void printUsageCmdLine(){
        System.out.println("Test Case Generator.");
        System.out.println("Example: To test 2D CGI shapes we have 1) border color "
				+ "2) Number of Sides 3) fill color .... " + NEW_LINE + "Which is 3 different aspects.");
        System.out.println("For our 3 Aspects:");
        System.out.println("\tAspect #1 Options (Border Color): black, brown, blue = "
				+ "3 different border colors.");
        System.out.println("\tAspect #2 Options (Number of Sides): 3 (triangle), 4, "
				+ "(rectangle), 5 (pentagon) = 3 different shapes.");
        System.out.println("\tAspect #3 Options (Fill Colors): red, green, yellow = "
				+ "3 different fill colors.");
        System.out.println("\tTotal Test Cases: #BorderColors x #Sides x #FillColors "
				+ "= 3 x 3 x 3 = 27" + NEW_LINE + "");
    }
    
    
    
    /**
     * Prompts user for all input.  Once input has been completed and the user 
	 * tells the program to "generate".  Then the method returns a completed
	 * TestSuite object
     * @return TestSuite
     */
    public static TestSuite getInputCmdLine() {
        TestSuiteBuilder BobTheBuilder = new TestSuiteBuilder();
        //print out the command line headers
        BobTheBuilder.DisplayBannerCmdLine();

        //fill in all the aspects
        testSuite = BobTheBuilder.createTestSuiteCmdLine();

        //List testCases = new ArrayList(ComputeTestCases(testSuite));

        return testSuite;
    } // end input method
    

    /**
     * hard coded method to populate a TestSuite
     * @return TestSuite ts
     */
    public static TestSuite HardCodedVersion() {

        //list of aspects
        List hardCodedAspectList = new ArrayList<>();

        /*
        Non-Modified: "H2000", "H2032", "97537", "H2011"
        Modified: "H2011 HM"
        
        SECONDARY "14", "15", "17", 
        PRIMARY "41", "42", "43", "51", "52", "54", "56", "83", "85"
        
        "Prospective", "Member","Non-Member"
        CURENT: "current single", "current multi-procedure"
        FUTURE: "future single", "future multi-procedure"
        NOTFOUND "not found"
        */
        TestSuite ts = new TestSuite();
        ts.setName("CCF10772B1");
        ts.setDescription("MMCP Program Changes");
        
            TestAspect ta1 = new TestAspect();
            ta1.setName("Procedures");
                TestOptionGroup tog1 = new TestOptionGroup();
                    tog1.setName("Non-Modified");
                    tog1.setOptions(Arrays.asList("H2000", "H2011", "H2032", "97537"));
                TestOptionGroup tog2 = new TestOptionGroup();
                    tog2.setName("Modifed");
                    tog2.addOption("H2011 HM");
            ta1.addOptionGroup(tog1);ta1.addOptionGroup(tog2);
        ts.addAspect(ta1);

            TestAspect ta2 = new TestAspect();
            ta2.setName("Rate Codes");
                TestOptionGroup tog3 = new TestOptionGroup();
                    tog3.setName("Secondary Rates");
                    tog3.setOptions(Arrays.asList("14", "15", "17"));
                TestOptionGroup tog4 = new TestOptionGroup();
                    tog4.setName("Primary Rates");
                    tog4.setOptions(Arrays.asList("41", "42", "43", "51", "52", "54", "56", "83", "85"));
            ta2.addOptionGroup(tog3);ta2.addOptionGroup(tog4);
        ts.addAspect(ta2);

            TestAspect ta3 = new TestAspect();
            ta3.setName("MMCP Member Status");
                TestOptionGroup tog5 = new TestOptionGroup();
                    tog5.setName("Member Status");
                    tog5.setOptions(Arrays.asList("Non-Member", "Member", "Prospective"));
            ta3.addOptionGroup(tog5);
        ts.addAspect(ta3);

            TestAspect ta4 = new TestAspect();
            ta4.setName("PA Status");
                TestOptionGroup tog6 = new TestOptionGroup();
                    tog6.setName("Current Dated");
                    tog6.setOptions(Arrays.asList("Current Dated - Single Procedure PA", "Current Dated - Multi-Procedure PA"));
                TestOptionGroup tog7 = new TestOptionGroup();
                    tog7.setName("Future Dated");
                    tog7.setOptions(Arrays.asList("Future Dated - Single Procedure PA", "Future Dated - Multi-Procedure PA"));
                TestOptionGroup tog8 = new TestOptionGroup();
                    tog8.setName("Not Found");
                    tog8.setOptions(Arrays.asList("Not Found"));
            ta4.addOptionGroup(tog6); ta4.addOptionGroup(tog7); ta4.addOptionGroup(tog8);
        ts.addAspect(ta4);

        
        
        /*
        //quick output
        System.out.println("HARDCODED: Adding TestAspect: " +ta2.getName() +" with \"" +ta2.getOptionGroups().size() +"\" OptionGroups");
            for(TestOptionGroup tog: ta2.getOptionGroups()) {
                System.out.println("HARDCODED\tOptionGroup \""+tog.getName() +"\" Contains Options: \"" +tog.getOptions().toString()+"\"");
            }
        System.out.println();
        

        System.out.println("DEBUG: HARDCODED: All Test Cases:");
        for (int i=0; i< tests.size(); i++) {
            TestCase tc = tests.get(i);
            System.out.println("\tTest #" + (i+1) + tc.getTestOptions().toString());
        }
        */
        
        return ts;
    } // end of hard coded method
}//end class

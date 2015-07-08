package com.boiseitoncall.utilities.testing.testCaseGenerator;

import com.boiseitoncall.utilities.testing.testCaseGenerator.models.TestAspect;
import com.boiseitoncall.utilities.testing.testCaseGenerator.models.TestCase;
import com.boiseitoncall.utilities.testing.testCaseGenerator.models.TestSuite;
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
        printUsage();
        
        //Call to function to get all user input
        testSuite = getInput();
        

        //print to screen all the test cases
        //Object has a toString() so we don't need to specify that this is actually string object.
        System.out.println("Total Test Cases: " + testSuite.getNumTestAspects());
        ArrayList<ArrayList<String>> testCases = testSuite.getTestCases();
        for (int i = 0 ; i < testCases.size() ; i++) {
            System.out.println("Test Case #" + i + ": " + testCases.get(i).toString());
        }
        
        
        
        /*
        for (ArrayList testCase : testSuite.getTestCases()) {
            System.out.println("Test Case #" + testCase.index + testCase.getTestOptions().toString());
        }*/
        
    }//end main(Args)

    
    public static void printTestCases(List<TestCase> allTestCases){
        System.out.println("Printing All Possible Test Cases.");
        for(int i = 0 ; i < allTestCases.size(); i++) {
            System.out.println("Test Case #" + (i+1) + ": " + allTestCases.get(i).getTestOptions().toString());
        }
    }
    

    /**
     *Prints a usage statement at the top of each run.
     */
    public static void printUsage(){
        System.out.println("Test Case Generator.");
        System.out.println("Example: To test 2D CGI shapes we have 1) border color "
				+ "2) Number of Sides 3) fill color .... " + NEW_LINE + "Which is 3 different aspects.");
        System.out.println("For our 3 Aspects:");
        System.out.println("\tAspect #1 Options (Border Color): black, brown, blue = "
				+ "3 different border colors.");
        System.out.println("\tAspect #1 Options (Number of Sides): 3 (triangle), 4, "
				+ "(rectangle), 5 (pentagon) = 3 different shapes.");
        System.out.println("\tAspect #1 Options (Fill Colors): red, green, yellow = "
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
    public static TestSuite getInput() {
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

        
        TestSuiteBuilder BobTheBuilder = new TestSuiteBuilder();
        //print out the command line headers
        BobTheBuilder.DisplayBannerCmdLine();

        //fill in all the aspects
        testSuite = BobTheBuilder.createTestSuiteCmdLine();

        //List testCases = new ArrayList(ComputeTestCases(testSuite));

        //System.out.println("" + NEW_LINE + "" + testSuite.toString());
        System.out.println("DEBUG: Inside TestSuiteBuilder:createTestSuiteCmdLine()");
        System.out.println("TestSuite Name: " + testSuite.getName());
        System.out.println("TestSuite Description: " + testSuite.getDescription());
        System.out.println("TestSuite Number Of Aspects: " + testSuite.getNumTestAspects());
        System.out.println("TestSuite Aspects: ");
        for(TestAspect aspect : testSuite.getAspects()){
            System.out.print("\t" + aspect.getName() + " : {");
            for(String option : aspect.getOptions()) {
                System.out.print("\"" + option + "\" ");
                }
            System.out.print("}" + NEW_LINE + "");
        }
	
       
		
		
        return testSuite;
    } // end input method
    

    /**
     * Hard coded example
     */
    public static void HardCodedVersion() {

        List hardCodedMasterList = new ArrayList<>();

        /*
        "H2000", "H2011", "H2011 HM", "H2032", "97537"
        "14", "15", "17", "41", "42", "43", "51", "52", "54", "56", "83", "85"
        "Prospective", "Member","Non-Member"
        "current dated", "future dated"
        */
        
        
        List<String> VariableA = new ArrayList<String>(Arrays.asList("H2000", "H2011", "H2011 HM", "H2032", "97537"));
        List<String> VariableB = new ArrayList<String>(Arrays.asList("14", "15", "17", "41", "42", "43", "51", "52", "54", "56", "83", "85"));
        List<String> VariableC = new ArrayList<String>(Arrays.asList("Prospective", "Member","Non-Member"));
        List<String> VariableD =  new ArrayList<String>(Arrays.asList("current dated", "future dated"));

//		List<String> allCombinations = new ArrayList<String>();
        List<List<String>> allCombinations = new ArrayList<List<String>>();

        int numberOfCombinations = 0;

        for(int i = 0 ; i < VariableA.size() ; i++) {
            for(int j = 0 ; i < VariableB.size() ; j++) {
                for(int k = 0 ; i < VariableC.size() ; k++) {
                    for(int l = 0 ; i < VariableD.size() ; l++) {
                        numberOfCombinations++;
                        String newTestCase = new String("TestCase #" +numberOfCombinations 
                            + ": Procedure: " + VariableA.get(i) + " Rate: " 
                            + VariableB.get(j) + " Status: " + VariableC.get(k)
                            + " Date: " + VariableD.get(l));
                        System.out.println(newTestCase);
                        //allCombinations.add(newTestCase);
        } } } }

        hardCodedMasterList.addAll(VariableA);
        hardCodedMasterList.addAll(VariableB);
        hardCodedMasterList.addAll(VariableC);
        hardCodedMasterList.addAll(VariableD);


    } // end of hard coded method
	
	
	
}//end class

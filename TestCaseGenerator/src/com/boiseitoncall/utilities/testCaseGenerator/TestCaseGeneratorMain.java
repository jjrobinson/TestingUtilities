package com.boiseitoncall.utilities.testCaseGenerator;

import com.boiseitoncall.utilities.testCaseGenerator.models.TestAspect;
import com.boiseitoncall.utilities.testCaseGenerator.models.TestCase;
import com.boiseitoncall.utilities.testCaseGenerator.models.TestOptionGroup;
import com.boiseitoncall.utilities.testCaseGenerator.models.TestSuite;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Main entry point for running TestGenerator as a jar
 * @author robinso3
 */
public class TestCaseGeneratorMain {

    /**
     * The TestSuite object for the static main(String args[])
     */
    static TestSuite testSuite;
    private static final String NEW_LINE = System.getProperty("line.separator");

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
        boolean demo = false;
        boolean demo2 = false;
        boolean ignoreGroups = false;
        boolean saveToFile = false;
        if (args != null) {
            for (String s : args) {
                if (s.equalsIgnoreCase("demo")) {
                    demo = true;
                }
                if (s.equalsIgnoreCase("demo2")) {
                    demo2 = true;
                }
                if (s.equalsIgnoreCase("ignoreGroups")) {
                    ignoreGroups = true;
                }
                
                if (s.equalsIgnoreCase("saveToFile")) {
                    saveToFile = true;
                }
            }
            if (demo) {
                //call to populate all info from hard coded lists for testing.
                testSuite = callHardCodedVersion();
            } else if (demo2) {
                //call to populate all info from hard coded lists for testing.
                testSuite = callHardCodedVersion2();
            } else {
                //Call to function to get all user input
                testSuite = getInputCmdLine(ignoreGroups);
            }
        }
        

        System.out.println("TestSuite.toString(): ");
        System.out.println(testSuite.toString());

        //print everything to the screen
        printTestCasesCmdLine(testSuite);
        
        if(saveToFile){
            saveTestCasesToFile(testSuite);
        }
        
    }//end main(Args)

    
    /**
     * Saves the contents of the test cases to csv format files
     * @param testSuite TestSuite
     */
    public static void saveTestCasesToFile(TestSuite testSuite){
        //fetch the SmartTestCases and AllTestCases into ArrayLists
        ArrayList<TestCase> smartTestCases = testSuite.getSmartTestCases();
        ArrayList<TestCase> allTestCases = testSuite.getAllTestCases();

        StringBuilder sbHeader = new StringBuilder();
        sbHeader.append("TestCase Verbosity,").append("TestCase#");
        for(String s : testSuite.getAspectNames()){
            sbHeader.append(",").append(s);
        }
        String csvHeader = sbHeader.toString();

        
        //wrapping file IO sections in a try
        try{
            //get the current datetime stamp
            StringBuilder smartFileName = new StringBuilder();
            StringBuilder allFileName = new StringBuilder();
            StringBuilder testSuiteFileName = new StringBuilder();

            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(Calendar.getInstance().getTime());
            
            
            
            testSuiteFileName.append("TestSuiteInfo-").append(timeStamp).append(".txt");
            File testSuiteFile = new File(testSuiteFileName.toString());
            //delete file if it exists, then create empty placeholder
            Files.deleteIfExists(testSuiteFile.toPath());
            testSuiteFile.createNewFile();

            BufferedWriter testSuiteWriter = new BufferedWriter(
                new FileWriter(testSuiteFile));
            testSuiteWriter.write(testSuite.toString());
            testSuiteWriter.newLine();
            testSuiteWriter.close();
            
            if(smartTestCases.size() > 0 && smartTestCases.size() < allTestCases.size()){
                smartFileName.append("SmartTestCases-").append(timeStamp).append(".csv");
                
                File smartFile = new File(smartFileName.toString());
                //delete file if it exists, then create empty placeholder
                Files.deleteIfExists(smartFile.toPath());
                smartFile.createNewFile();
                
                BufferedWriter smartCasesWriter = new BufferedWriter(
                    new FileWriter(smartFile));
                smartCasesWriter.write(csvHeader);
                smartCasesWriter.newLine();
            
                for (int i = 0 ; i < smartTestCases.size() ; i++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Smart,").append(Integer.toString(i+1));
                    for(String s : smartTestCases.get(i).getTestOptions())
                        sb.append(",").append(s);
                    smartCasesWriter.write(sb.toString());//write the content
                    smartCasesWriter.newLine();//add a newline
                }

                smartCasesWriter.close();
            }//end smart test cases section

            //create the strings for the file names
            allFileName.append("AllTestCases-").append(timeStamp).append(".csv");
            //create the File objects
            File allFile = new File(allFileName.toString());
            Files.deleteIfExists(allFile.toPath());
            allFile.createNewFile();
            
            BufferedWriter allCasesWriter = new BufferedWriter(
                new FileWriter(allFile));
            allCasesWriter.write(csvHeader);
            allCasesWriter.newLine();
            
            
            for (int i = 0 ; i < allTestCases.size() ; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append("All,").append(Integer.toString(i+1));
                for(String s : allTestCases.get(i).getTestOptions())
                    sb.append(",").append(s);
                allCasesWriter.write(sb.toString());//write the content
                allCasesWriter.newLine();//add a newline
            }//end all test cases section
            allCasesWriter.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    

    
    /**
     * Outputs the allTestCases and smartTestCases list to the console
     * @param TestSuite ts
     */
    public static void printTestCasesCmdLine(TestSuite testSuite){
        ArrayList<TestCase> smartTestCases = testSuite.getSmartTestCases();
        ArrayList<TestCase> allTestCases = testSuite.getAllTestCases();
        System.out.println("Smart Test Cases: " + testSuite.getNumberOfSmartTestCases());
        for (int i = 0 ; i < testSuite.getNumSmartTestCases() ; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("SMART\tTest Case #").append(Integer.toString(i+1))
                .append(": ").append(
                    smartTestCases.get(i).getTestOptions().toString()
                );
            System.out.println(sb.toString());
        }

        System.out.println("ALL Test Cases: " + testSuite.getNumAllTestCases());
        for (int i = 0 ; i < testSuite.getNumAllTestCases() ; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("ALL\t").append(Integer.toString(i+1))
                .append(": ").append(
                    allTestCases.get(i).getTestOptions().toString()
                );
            System.out.println(sb.toString());
        }//end all test cases section

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
    public static TestSuite getInputCmdLine(boolean ignoreGroups) {
        TestSuiteBuilder BobTheBuilder = new TestSuiteBuilder();
        //print out the command line headers
        try{
            BobTheBuilder.displayBannerCmdLine();

        //fill in all the aspects
        testSuite = BobTheBuilder.createTestSuiteCmdLine(true, ignoreGroups);
        }catch (Exception e) { e.printStackTrace();}

        //List testCases = new ArrayList(ComputeTestCases(testSuite));

        return testSuite;
    } // end input method
    

    /**
     * hard coded method to populate a TestSuite
     * @return TestSuite ts
     */
    public static TestSuite callHardCodedVersion() {


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
        ts.setName("MMCP Program Changes");
        ts.setDescription("MMCP Program Changes");
        
            TestAspect ta1 = new TestAspect();
            ta1.setName("Procedures");
                TestOptionGroup tog1 = new TestOptionGroup();
                    tog1.setName("Non-Modified");
                    tog1.setOptions(Arrays.asList("H2000", "H2032", "97537", "H2011"));
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
        
        return ts;
    } // end of hard coded method
    
    
    
    /**
     * hard coded method to populate a TestSuite
     * @return TestSuite ts
     */
    public static TestSuite callHardCodedVersion2() {
        TestSuite ts2 = new TestSuite();
        ts2.setName("Dual Enrollment Behavior Modifications");
        ts2.setDescription("Complete enumeration of functionality changes for complementary health coverage plans.");
        
            TestAspect ts2_ta1 = new TestAspect();
            ts2_ta1.setName("Death Date");
            ts2_ta1.setDescription("Does the member have a Date Of Death?");
                TestOptionGroup ts2_ta1_tog1a = new TestOptionGroup();
                    ts2_ta1_tog1a.setName("TRUE");
                    ts2_ta1_tog1a.setOptions(Arrays.asList("TRUE"));
                TestOptionGroup ts2_ta1_tog1b = new TestOptionGroup();
                    ts2_ta1_tog1b.setName("FALSE");
                    ts2_ta1_tog1b.addOption("FALSE");
            ts2_ta1.addOptionGroup(ts2_ta1_tog1a);ts2_ta1.addOptionGroup(ts2_ta1_tog1b);
        ts2.addAspect(ts2_ta1);

            TestAspect ts2_ta2 = new TestAspect();
            ts2_ta2.setName("Has Medicaid");
            ts2_ta2.setDescription("Does the member have Medicaid?");
                TestOptionGroup ts2_ta2_tog2a = new TestOptionGroup();
                    ts2_ta2_tog2a.setName("TRUE");
                    ts2_ta2_tog2a.setOptions(Arrays.asList("TRUE"));
                TestOptionGroup ts2_ta2_tog2b = new TestOptionGroup();
                    ts2_ta2_tog2b.setName("FALSE");
                    ts2_ta2_tog2b.setOptions(Arrays.asList("FALSE"));
            ts2_ta2.addOptionGroup(ts2_ta2_tog2a);ts2_ta2.addOptionGroup(ts2_ta2_tog2b);
        ts2.addAspect(ts2_ta2);

            TestAspect ts2_ta3 = new TestAspect();
            ts2_ta3.setName("Has Medicare");
            ts2_ta3.setDescription("Does the member have Medicare? If so, which? (None,A,B,Both)");
                TestOptionGroup ts2_ta2_tog3a = new TestOptionGroup();
                    ts2_ta2_tog3a.setName("None");
                    ts2_ta2_tog3a.setOptions(Arrays.asList("None"));
                TestOptionGroup ts2_ta2_tog3b = new TestOptionGroup();
                    ts2_ta2_tog3b.setName("A");
                    ts2_ta2_tog3b.setOptions(Arrays.asList("A"));
                TestOptionGroup ts2_ta2_tog3c = new TestOptionGroup();
                    ts2_ta2_tog3c.setName("B");
                    ts2_ta2_tog3c.setOptions(Arrays.asList("B"));
                TestOptionGroup ts2_ta2_tog3d = new TestOptionGroup();
                    ts2_ta2_tog3d.setName("Both");
                    ts2_ta2_tog3d.setOptions(Arrays.asList("Both"));
            ts2_ta3.addOptionGroup(ts2_ta2_tog3a); ts2_ta3.addOptionGroup(ts2_ta2_tog3b); ts2_ta3.addOptionGroup(ts2_ta2_tog3c);
            ts2_ta3.addOptionGroup(ts2_ta2_tog3d);
        ts2.addAspect(ts2_ta3);


            TestAspect ts2_ta4 = new TestAspect();
            ts2_ta4.setName("Has Medicare BuyIns");
            ts2_ta4.setDescription("Does the member have Medicare BuyIns? If so, which? (None,A,B,Both)");
                TestOptionGroup ts2_ta2_tog4a = new TestOptionGroup();
                    ts2_ta2_tog4a.setName("None");
                    ts2_ta2_tog4a.setOptions(Arrays.asList("None"));
                TestOptionGroup ts2_ta2_tog4b = new TestOptionGroup();
                    ts2_ta2_tog4b.setName("A");
                    ts2_ta2_tog4b.setOptions(Arrays.asList("A"));
                TestOptionGroup ts2_ta2_tog4c = new TestOptionGroup();
                    ts2_ta2_tog4c.setName("B");
                    ts2_ta2_tog4c.setOptions(Arrays.asList("B"));
                TestOptionGroup ts2_ta2_tog4d = new TestOptionGroup();
                    ts2_ta2_tog4d.setName("Both");
                    ts2_ta2_tog4d.setOptions(Arrays.asList("Both"));
            ts2_ta4.addOptionGroup(ts2_ta2_tog4a); ts2_ta4.addOptionGroup(ts2_ta2_tog4b); ts2_ta4.addOptionGroup(ts2_ta2_tog4c);
            ts2_ta4.addOptionGroup(ts2_ta2_tog4d);
        ts2.addAspect(ts2_ta4);


            TestAspect ts2_ta5 = new TestAspect();
            ts2_ta5.setName("Medicare Terms");
            ts2_ta5.setDescription("Has the member's Medicare coverage termed? If so, which? (None,A,B,Both)");
                TestOptionGroup ts2_ta2_tog5a = new TestOptionGroup();
                    ts2_ta2_tog5a.setName("None");
                    ts2_ta2_tog5a.setOptions(Arrays.asList("None"));
                TestOptionGroup ts2_ta2_tog5b = new TestOptionGroup();
                    ts2_ta2_tog5b.setName("A");
                    ts2_ta2_tog5b.setOptions(Arrays.asList("A"));
                TestOptionGroup ts2_ta2_tog5c = new TestOptionGroup();
                    ts2_ta2_tog5c.setName("B");
                    ts2_ta2_tog5c.setOptions(Arrays.asList("B"));
                TestOptionGroup ts2_ta2_tog5d = new TestOptionGroup();
                    ts2_ta2_tog5d.setName("Both");
                    ts2_ta2_tog5d.setOptions(Arrays.asList("Both"));
            ts2_ta5.addOptionGroup(ts2_ta2_tog5a); ts2_ta5.addOptionGroup(ts2_ta2_tog5b); ts2_ta5.addOptionGroup(ts2_ta2_tog5c);
            ts2_ta5.addOptionGroup(ts2_ta2_tog5d);
        ts2.addAspect(ts2_ta5);


            TestAspect ts2_ta6 = new TestAspect();
            ts2_ta6.setName("Term Dates Match");
            ts2_ta6.setDescription("Do the member's Medicare term dates match? How? (Yes,N/A,No: A<B, No: B<A");
                TestOptionGroup ts2_ta2_tog6a = new TestOptionGroup();
                    ts2_ta2_tog6a.setName("Yes");
                    ts2_ta2_tog6a.setOptions(Arrays.asList("Yes"));
                TestOptionGroup ts2_ta2_tog6b = new TestOptionGroup();
                    ts2_ta2_tog6b.setName("N/A");
                    ts2_ta2_tog6b.setOptions(Arrays.asList("N/A"));
                TestOptionGroup ts2_ta2_tog6c = new TestOptionGroup();
                    ts2_ta2_tog6c.setName("No: A<B");
                    ts2_ta2_tog6c.setOptions(Arrays.asList("No: A<B"));
                TestOptionGroup ts2_ta2_tog6d = new TestOptionGroup();
                    ts2_ta2_tog6d.setName("No: B<A");
                    ts2_ta2_tog6d.setOptions(Arrays.asList("No: B<A"));
            ts2_ta6.addOptionGroup(ts2_ta2_tog6a); ts2_ta6.addOptionGroup(ts2_ta2_tog6b); ts2_ta6.addOptionGroup(ts2_ta2_tog6c);
            ts2_ta6.addOptionGroup(ts2_ta2_tog6d);
        ts2.addAspect(ts2_ta6);


            TestAspect ts2_ta7 = new TestAspect();
            ts2_ta7.setName("Are Term Dates <=31days apart");
            ts2_ta7.setDescription("Are the term dates <= 31 days apart? How? (No,Yes,N/A)");
                TestOptionGroup ts2_ta2_tog7a = new TestOptionGroup();
                    ts2_ta2_tog7a.setName("No");
                    ts2_ta2_tog7a.setOptions(Arrays.asList("No"));
                TestOptionGroup ts2_ta2_tog7b = new TestOptionGroup();
                    ts2_ta2_tog7b.setName("N/A");
                    ts2_ta2_tog7b.setOptions(Arrays.asList("N/A"));
                TestOptionGroup ts2_ta2_tog7c = new TestOptionGroup();
                    ts2_ta2_tog7c.setName("Yes");
                    ts2_ta2_tog7c.setOptions(Arrays.asList("Yes"));
            ts2_ta7.addOptionGroup(ts2_ta2_tog7a); ts2_ta7.addOptionGroup(ts2_ta2_tog7b); ts2_ta7.addOptionGroup(ts2_ta2_tog7c);
        ts2.addAspect(ts2_ta7);
        
        return ts2;    } // end of hard coded method
    
    
}//end class

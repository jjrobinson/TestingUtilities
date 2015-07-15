/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boiseitoncall.utilities.cmsInterfaceTesting;



import com.boiseitoncall.utilities.cmsInterfaceTesting.models.CandidateInterfaceFile;
import com.boiseitoncall.utilities.cmsInterfaceTesting.models.InterfaceFileInterface;
import com.boiseitoncall.utilities.cmsInterfaceTesting.models.rules.InterfaceFileRuleInterface;
import com.boiseitoncall.utilities.cmsInterfaceTesting.models.rules.InterfaceRuleBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author JasonRobinson
 */
public class TestInterfaceFile {
        //global variables needed
        public static String fileType = new String();
        public static String fileName;
        public static File fileHandle;
        public static CandidateInterfaceFile candidate;
        
        public static BufferedReader buffer = null;
        public static List<String> errors = new ArrayList();
        public static List<String> lines = new ArrayList<String>();
        public static String state = "id";
        public static InterfaceFileInterface IF;
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        /*
        InterfaceTestingArguments bean = new InterfaceTestingArguments();
        CmdLineParser parser = new CmdLineParser(bean);
        
        //wrapping the parse command in a try catch because of required error handling
        try{
        parser.parseArgument(args);
        } catch(CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar TestInterfaceFile.jar [options...] arguments...");
            parser.printUsage(System.err);
            return;
        }
        */
        
        //prints some info about where the program is running, and what files are visible.
        PrintDiagnostics();
        



//TODO: add use of args4j
        if (args.length == 0)
        {
            // no arguments provided
            printUsageStatement();
            System.exit(1);
            //normal non-zero exit status so automation will see this as a failed run
        } else {
        
            //call to process the arguments
            processArgs(args);

            InterfaceRuleBuilder bobTheBuilder = new InterfaceRuleBuilder();


            InterfaceFileRuleInterface rules = bobTheBuilder.getInterfaceFileRules(fileType, state);
            //test to see if the file exists at the path given
            //and is not a directory
            if(fileHandle.exists() && !fileHandle.isDirectory()) {
                //stuff the file name and lines into a CandidateFile
                candidate = buildCandidateFile();
            }
            
            
            printCandidate();
            
            
            //now that we have a candidate, it is time to test it against the 
            //appropriate set of rules
            //rules.checkForProblems(candidate);
            
        }
        //normal zero exit status so automation will see this as a successful run
        System.exit(0);
    } // end of main (String args[])

    
    
    
    /**
     * Processes the command line arguments manually. Eventually will be replaced 
     * with args4j.
     * @param args 
     */
    private static void processArgs(String[] args){
        
        System.out.println("Command line Args:");
        for (int i = 0 ; i< args.length ; i++ ) {
            System.out.println("Arg" + i + ": \"" + 
                    args[i].toString() + "\"");
            }

        //what type of file is this.
        if (args[0].equalsIgnoreCase("10038")){
            fileType = args[0];
        } else if (args[0].equalsIgnoreCase("10039")){
            fileType = args[0];
        } else {
            errors.add("Unknown file type \"" + fileType + "\".");
            printUsageStatement();
            System.exit(1);
        }
        System.out.println("Testing a " + fileType + " file.\n\n");

        //test to see if we got a file name argument
        if (!args[1].toString().isEmpty()) {
            //open up the file for reading
            fileName = new String(args[1]);
            
            if (fileName !=null){
                fileHandle = new File(fileName);
            } else {
                System.out.println("File Name not submitted.");
             printUsageStatement();
                System.exit(0);    
            }
  
            
        } else {
            //not enough arguments passed in.
            String msg = new String ("Empty inputFile name.  See Usage Statement Below.");
            errors.add(msg);
            System.err.println(msg);
            printUsageStatement();
            System.exit(1);
        }//end if we have a file name argument on the command line.

    }
    
        
    
    public static CandidateInterfaceFile buildCandidateFile(){
        
        //given a FileName string and a bufferedReader, open the file, and 
        // read in the contents into lines, savings errors to errors.
        lines = ParseFile();

        if (fileType.equals("10038")){
        //verify lines in a 10038
        /*
        CMS10038File CMSA = new CMS10038File(lines);
        if (CMSA.isValid()) {
        System.out.println("File \"" + fileName.toString() + "\" PASSES inspection.");
        } else {
        System.out.println("File \"" + fileName.toString() + "\" FAILS inspection.  See errors below.");
        printErrors(errors);
        }
        */
        } else {
        //TODO verify lines in a 10039
        }


        
        
        CandidateInterfaceFile candidate = new CandidateInterfaceFile();
        candidate.setFileName(fileName);
        candidate.setLines(lines);

        return candidate;
    }
    
    
    
    
    
    /**
     * Simply prints a usage statement.
     */
    private static void printUsageStatement()
    {
        System.out.println("Usage Statement.");
        System.out.println("java [program] [FileType] [FileName]");
        System.out.println("\tFileType: either \"10038\" or \"10039\"");
        System.out.println("\tFileName: the name of hte file to be tested.");
    }
    
    
    /**
     * Given correctly parsed arguments, opens the candidate file and reads it
     * into a string ArrayList.
     * @return 
     */
    private static List<String> ParseFile()    {

        List<String> inputFileLines = new ArrayList<String>();
        try { buffer = new BufferedReader(new FileReader(fileName)); 
        String line;
        //loop on the input file
        while ((line = buffer.readLine()) != null)
            {
            // Process line of input Here
            if (!line.isEmpty()) {
                
                //System.out.println("DEBUG: " + line);
                inputFileLines.add(line);
                }// end of file IO loop
            //close our buffered reader
            
            } // end while
        buffer.close();
        }
        catch (Exception e) {
            String newError = e.getMessage();
            errors.add(newError);
            System.out.println(newError);
        }

        

        return inputFileLines;
//        } catch (Exception e) {
//            String msg = "Could not open fileName: \"" + fileName + "\".";
//            errors.add(msg);
//            System.err.println(msg);
//            }
//        return lines;
    }
    
    private static void printErrors(List<String> errors)
    {
        System.out.println("Usage Statement.");
    }
    
    /**
     * Utility function to print out info about the current path and files near us.
     */
    private static void PrintDiagnostics(){
        
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);

        
        System.out.println("Printing a list of all files in same dir found at runtime...\n\n");
        File folder = new File(s);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        System.out.println("\n\n");
    }
 
    /**
     * Utility function to clear the console output for testing purposes
     */
    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }

    
    public static void printCandidate(){
        System.out.println("Candidate File Name: " + candidate.getFileName());
        for (int i = 0 ; i < candidate.getLines().size() ; i++){
            System.out.println("Line " + i + ":\""+candidate.getLines().get(i)+"\"");
        }
    }
    
    
    
    
}



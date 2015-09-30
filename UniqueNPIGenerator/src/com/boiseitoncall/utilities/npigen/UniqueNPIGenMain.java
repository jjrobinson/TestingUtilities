/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boiseitoncall.utilities.npigen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.GZIPInputStream;
import org.apache.commons.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement; 
import java.util.ArrayList;





public class UniqueNPIGenMain {
    
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String FILE_PATH_ROOT_DIR = "c:\\dev\\test\\interfaces\\";
    private static final String NPIs_FILE = FILE_PATH_ROOT_DIR+"NPPES_Data_Dissemination_July_2015\\complete_npi_list.txt";
    private static final String RAW_NPI_DB = FILE_PATH_ROOT_DIR+"NPPES_Data_Dissemination_July_2015\\npidata_20050523-20150712.csv";
    private static final String GZIPPED_NPIS_FILE_LOCAL = FILE_PATH_ROOT_DIR+"complete_npi_list.txt.gz";
    private static final String PLAINTEXT_NPIS_FILE_LOCAL = FILE_PATH_ROOT_DIR+"complete_npi_list.txt";
    private static final String CONNECTION_STRING_FILE = FILE_PATH_ROOT_DIR+"jtds_connection_string-UAT01-ENV01.txt";
    //private static final String QUERY_STRING_FILE = FILE_PATH_ROOT_DIR+"query_string.txt";
    private static final String QUERY_STRING_FILE = "C:\\dev\\src\\sql\\_Daily.Checks.sql";
    private static final FileSystem FILE_SYSTEM = FileSystems.getDefault();

    private static ArrayList<Long> ZIPPED_RUNS_FILEIO_LIST = new ArrayList<Long>();
    private static ArrayList<Long> ZIPPED_RUNS_SORT_LIST = new ArrayList<Long>();
    private static ArrayList<Long> PLAINTEXT_RUNS_FILEIO_LIST = new ArrayList<Long>();
    private static ArrayList<Long> PLAINTEXT_RUNS_SORT_LIST = new ArrayList<Long>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//        Args4JOptionsOptionsBean bean = new Args4JOptionsOptionsBean();
//        CmdLineParser parser = new CmdLineParser(bean);
//
//        try {
//            parser.parseArgument(args);
//        } catch( CmdLineException e ) {
//            System.err.println(e.getMessage());
//            System.err.println("java -jar UniqueNPIGenerator.jar [options...] arguments...");
//            parser.printUsage(System.err);
//            return;
//        }

        //only need to do this once!
        //ingestNPIs();


        //run the tests X times
        //runTests(10);
        
        //print out the results
        //printResults();

        String connectionString = getConnectionString();
        String queryString = getQueryString();

        System.out.println("Connection String: \"" + connectionString + "\"");
        System.out.println("Query String: \"" + queryString + "\"");
        
        connectAndExecuteQuery(connectionString, queryString);
        
        
        
        
        
        
        /*
        
        Stored every single NPI in a single text file then imported into MySQL via:
        
        
        LOAD DATA LOCAL INFILE 'C:/dev/test/interfaces/complete_npi_list.txt' 
            INTO TABLE npis FIELDS 
            TERMINATED BY '' 
            LINES TERMINATED BY '\r\n' (NPI_from_export);
        
        
        
        
        */
    
    
    }//end of main(String args[])

    
    /**
     * Runs the test X times
     */
    public static void runTests(int numberOfRuns){
        long[] results = null;
        for(int i = 0; i<numberOfRuns ; i++) {
            System.out.println("\r\nITERATION: #"+(i+1));
            results = openZippedNPIsWithIntegerObjects();
            ZIPPED_RUNS_FILEIO_LIST.add(results[0]);
            ZIPPED_RUNS_SORT_LIST.add(results[1]);
            results = null;

            results = openPlainTextNPIsWithIntegerObjects();
            PLAINTEXT_RUNS_FILEIO_LIST.add(results[0]);
            PLAINTEXT_RUNS_SORT_LIST.add(results[1]);
            results = null;
        }
    }
    
    
    
    /**
     * Print the Results of the X test runs. Computes average run times.
     */
    public static void printResults() {
        System.out.print("Zipped FileIO Run Times:\n\t");
        long zippedFileIORunTimes = 0;
        for(Long L : ZIPPED_RUNS_FILEIO_LIST) {
            System.out.print(" {" +L +"} ");
            zippedFileIORunTimes=zippedFileIORunTimes+L;
        }
        System.out.println("\tAverage Zipped File IO Run Time: "+(zippedFileIORunTimes/(ZIPPED_RUNS_FILEIO_LIST.size())));
        

        System.out.print("Zipped Sort Run Times:\n\t");
        long zippedSortRunTimes = 0;
        for(Long L : ZIPPED_RUNS_SORT_LIST) {
            System.out.print(" {" +L +"} ");
            zippedSortRunTimes=zippedSortRunTimes+L;
        }
        System.out.println("\tAverage Zipped File Sort Time: "+(zippedSortRunTimes/(ZIPPED_RUNS_SORT_LIST.size())));
        

        System.out.print("Plain Text FileIO Run Times:\n\t");
        long plainTextFileIORunTimes = 0;
        for(Long L : PLAINTEXT_RUNS_FILEIO_LIST) {
            System.out.print(" {" +L +"} ");
            plainTextFileIORunTimes=plainTextFileIORunTimes+L;
        }
        System.out.println("\tAverage Plain Text File IO Run Time: "+(plainTextFileIORunTimes/(PLAINTEXT_RUNS_FILEIO_LIST.size())));
        

        System.out.print("Plain Text Sort Run Times:\n\t");
        long plainTextSortRunTimes = 0;
        for(Long L : PLAINTEXT_RUNS_SORT_LIST) {
            System.out.print(" {" +L +"} ");
            plainTextSortRunTimes=plainTextSortRunTimes+L;
        }
        System.out.println("\tAverage Plain Text Sort Run Time: "+(plainTextSortRunTimes/(PLAINTEXT_RUNS_SORT_LIST.size())));
        

    }
    
    
    /**
     * Retrieves SQL Server connection string.
     * Connection String should look like this:
     * jdbc:jtds:sqlserver://[server FQDN]:[port];Instance=[DB Instance Name]
     * @return 
     */
    private static String getConnectionString(){
        
        LineIterator lineIterator = null;
        File inFile = new File(CONNECTION_STRING_FILE);
        String returnString = null;
        try {
            lineIterator = FileUtils.lineIterator(inFile, "UTF-8");

            returnString = lineIterator.nextLine();

            if (returnString ==null || returnString.isEmpty()) {
                throw new IOException("ERROR: Cannot read from file, or file contents are empty.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            LineIterator.closeQuietly(lineIterator);
        }
        return returnString;
    }
    
    /**
     * Retrieves the SQL statement query string to use from a file.
     * @return 
     */
    private static String getQueryString(){
        
        LineIterator lineIterator = null;
        File inFile = new File(QUERY_STRING_FILE);
        StringBuilder sb = new StringBuilder();
        try {
            Reader reader = new FileReader(QUERY_STRING_FILE);
            BufferedReader br = new BufferedReader(reader);

            
            
            
            /*
            lineIterator = FileUtils.lineIterator(inFile, "UTF-8");
            while(lineIterator.hasNext()){
                sb.append(lineIterator.nextLine()).append(NEW_LINE);
            }
            */

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            LineIterator.closeQuietly(lineIterator);
            
        }
        return sb.toString();
    }
     
    
    /**
     * Connects to the SQL Server DB using jTDS and executes the provided query
     * @param connectionUrl
     * @param query 
     */
    private static void connectAndExecuteQuery(String connectionUrl, String query){
      // Declare the connection objects.
      Connection con = null;
      Statement stmt = null;
      ResultSet rs = null;
      ResultSetMetaData rsmd = null;
      
        try {
            // Establish the connection.
            Class.forName("net.sourceforge.jtds.jdbc.JtdsConnection");
            con = DriverManager.getConnection(connectionUrl);

            
            ScriptRunner sr = new ScriptRunner(con,false,true);
            
                        
            sr.setErrorLogWriter(null);
            
            // Create and execute an SQL statement that returns some data.
            //stmt = con.createStatement();
            stmt.executeUpdate(query);
            rs = stmt.executeQuery(query);
            rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            int rowCount = 0;
            // Iterate through the data in the result set and display it.
            
            System.out.print("Header");
            for (int i = 1; i<=numColumns;i++) {
                System.out.print(rsmd.getColumnName(i)+ "\t");
            }
            
            while (rs.next()) {
                rowCount++;
                System.out.print("Row #"+rowCount+":");
                for(int i=1;i< numColumns;i++) {
                    System.out.print("\t" +rs.getString(i));
                }
               System.out.println();
               //System.out.println(rs.getString(4) + " " + rs.g);
               }

            // Handle any errors that may have occurred.
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) try { rs.close(); } catch(Exception e) {}
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (con != null) try { con.close(); } catch(Exception e) {}
        }
    }
    

    /**
     * reads a zipped text file's contents and sorts the array
     */
    public static long[] openZippedNPIsWithIntegerObjects(){
        System.out.println("DEBUG: STARTING openZippedNPIsWithIntegerObjects");
        long start = System.currentTimeMillis();
        
        NPIComparator comparator = new NPIComparator();
        ArrayList<Integer> npiList = new ArrayList();
        File zippedInFile = null;
        String copyOfZippedInFileString = null;
        FileInputStream fis = null;
        BufferedReader br = null;
        //GZIPInputStream gis = null;
        long startRead = 0; long readTime = 0; long startSort = 0; long sortTime = 0;
        
        
        try{
            double rand = Math.random();
            copyOfZippedInFileString = "temp-"+rand+".txt";
            
            Path copyFromPath = FILE_SYSTEM.getPath(GZIPPED_NPIS_FILE_LOCAL);
            Path copyToPath = FILE_SYSTEM.getPath(copyOfZippedInFileString);
            
            System.out.println("\tCopying FROM: " + copyFromPath.toString());
            System.out.println("\tCopying TO: " + copyToPath.toString());
            
            Files.copy(copyFromPath, copyToPath, StandardCopyOption.REPLACE_EXISTING);

            fis = new FileInputStream(copyOfZippedInFileString);
            zippedInFile = new File(copyOfZippedInFileString);

            if(zippedInFile.isFile()) {
                // and a BufferedReader on top to comfortably read the file
                br = new BufferedReader(new InputStreamReader(new GZIPInputStream(fis)));
                //InputStreamReader isr = new InputStreamReader(new GZIPInputStream(fis));
                String line;
                
                System.out.println("\tReading GZipped NPI File that is " +(Files.size(copyToPath)/(1024.0*1024.0)) +" MiB." );
                startRead = System.currentTimeMillis();
                while((line = br.readLine()) != null) {
                    npiList.add(Integer.parseInt(line));
                }
                if (npiList.isEmpty()){
                    throw new Exception("\tList is Empty.");
                    }
                readTime = System.currentTimeMillis()-startRead;
                System.out.println("\tDone Reading in the " + npiList.size()+" NPIs in " +readTime +"ms");

                //sort the list
                System.out.println("\tSorting ("+npiList.size()+" NPIs smallest to largest)");
                startSort = System.currentTimeMillis();
                npiList.sort(comparator);
                sortTime = System.currentTimeMillis()-startSort;
                System.out.println("\tDone Sorting the " + npiList.size()+" NPIs in " +sortTime +"ms");
                
                fis.close();
                Files.delete(copyToPath);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
 
        long[] results = new long[2];
        results[0] = readTime;
        results[1] = sortTime;
        
        long stop = System.currentTimeMillis()-start;
        System.err.println("\tEllapsed Time: "+stop);
        System.out.println("\tDEBUG: ENDING openZippedNPIsWithIntegerObjects");
        
        return results;
    }
 
    
    /**
     * reads a plain text file's contents and sorts it
     */
    public static long[] openPlainTextNPIsWithIntegerObjects(){
        System.out.println("DEBUG: Starting openPlainTextNPIsWithIntegerObjects");
        long start = System.currentTimeMillis();
        
        NPIComparator comparator = new NPIComparator();
        ArrayList<Integer> npiList = new ArrayList();
        File inFile = null;
        String copyOfInFileString = null;
        BufferedReader br = null;
        long startRead = 0; long readTime = 0; long startSort = 0; long sortTime = 0;

            
        try{
            double rand = Math.random();
            copyOfInFileString = "temp-"+rand+".txt";
            
            Path copyFromPath = FILE_SYSTEM.getPath(PLAINTEXT_NPIS_FILE_LOCAL);
            Path copyToPath = FILE_SYSTEM.getPath(copyOfInFileString);
            
            System.out.println("\tCopying FROM: " + copyFromPath.toString());
            System.out.println("\tCopying TO: " + copyToPath.toString());
            
            Files.copy(copyFromPath, copyToPath, StandardCopyOption.REPLACE_EXISTING);

            inFile = new File(copyOfInFileString);
            br = new BufferedReader(new FileReader(copyOfInFileString));
            
            if(inFile.isFile()) {
                
                String line;
                System.out.println("\tReading Flat Text NPI File that is " +(Files.size(copyToPath)/(1024.0*1024.0)) +" MiB." );
                startRead = System.currentTimeMillis();
                while((line = br.readLine()) != null) {
                    npiList.add(Integer.parseInt(line));
                }
                if (npiList.isEmpty()){
                    throw new Exception("\tList is Empty.");
                    }
                readTime = System.currentTimeMillis()-startRead;
                System.out.println("\tDone Reading in the " + npiList.size()+" NPIs in " +readTime +"ms");

                //sort the list
                System.out.println("\tSorting ("+npiList.size()+" NPIs smallest to largest)");
                startSort = System.currentTimeMillis();
                npiList.sort(comparator);
                sortTime = System.currentTimeMillis()-startSort;
                System.out.println("\tDone Sorting the " + npiList.size()+" NPIs in " +sortTime +"ms");
                
                br.close();
                Files.delete(copyToPath);

            } else {
                throw new Exception("Cannot find file: \"" + GZIPPED_NPIS_FILE_LOCAL+ "\"");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
         
        long[] results = new long[2];
        results[0] = readTime;
        results[1] = sortTime;
        
        System.out.println("\tDEBUG: ENDING openPlainTextNPIsWithIntegerObjects");
        System.err.println("\tEllapsed Time: "+(System.currentTimeMillis()-start));
        return results;
    }

    
    /**
     * processes through the NPPES archive found here:
     * http://download.cms.gov/nppes/NPI_Files.html
     * WARNING: >5GB csv file
     */
    private static void ingestNPIs(){        
        File inFile = new File(RAW_NPI_DB);
        File outFile = new File(NPIs_FILE);
        FileWriter fw = null;
        
        LineIterator lineIterator = null;
        try {

            fw = new FileWriter(outFile); 
            if(outFile.isFile()) {
                outFile.delete();
                outFile.createNewFile();
            }
            
            lineIterator = FileUtils.lineIterator(inFile, "UTF-8");

            //this line skipps the header line.
            lineIterator.nextLine();
            
            //loop through EVERY line in the >5GB file
            while (lineIterator.hasNext()) {
                fw.write(lineIterator.nextLine().substring(1, 11)+NEW_LINE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            LineIterator.closeQuietly(lineIterator);
        }
    }

    
    
    /**
     * A column mapping for the CMS.gov NPI datafile. 
     * http://download.cms.gov/nppes/NPI_Files.html
     * @return 
     */
    private static String[] getMapping(){
        String[] mapping = {"NPI",
            "Entity Type Code",
            "Replacement NPI",
            "Employer Identification Number (EIN)",
            "Provider Organization Name (Legal Business Name)",
            "Provider Last Name (Legal Name)",
            "Provider First Name",
            "Provider Middle Name",
            "Provider Name Prefix Text",
            "Provider Name Suffix Text",
            "Provider Credential Text",
            "Provider Other Organization Name",
            "Provider Other Organization Name Type Code",
            "Provider Other Last Name",
            "Provider Other First Name",
            "Provider Other Middle Name",
            "Provider Other Name Prefix Text",
            "Provider Other Name Suffix Text",
            "Provider Other Credential Text",
            "Provider Other Last Name Type Code",
            "Provider First Line Business Mailing Address",
            "Provider Second Line Business Mailing Address",
            "Provider Business Mailing Address City Name",
            "Provider Business Mailing Address State Name",
            "Provider Business Mailing Address Postal Code",
            "Provider Business Mailing Address Country Code (If outside U.S.)",
            "Provider Business Mailing Address Telephone Number",
            "Provider Business Mailing Address Fax Number",
            "Provider First Line Business Practice Location Address",
            "Provider Second Line Business Practice Location Address",
            "Provider Business Practice Location Address City Name",
            "Provider Business Practice Location Address State Name",
            "Provider Business Practice Location Address Postal Code",
            "Provider Business Practice Location Address Country Code (If outside U.S.)",
            "Provider Business Practice Location Address Telephone Number",
            "Provider Business Practice Location Address Fax Number",
            "Provider Enumeration Date",
            "Last Update Date",
            "NPI Deactivation Reason Code",
            "NPI Deactivation Date",
            "NPI Reactivation Date",
            "Provider Gender Code",
            "Authorized Official Last Name",
            "Authorized Official First Name",
            "Authorized Official Middle Name",
            "Authorized Official Title or Position",
            "Authorized Official Telephone Number",
            "Healthcare Provider Taxonomy Code_1",
            "Provider License Number_1",
            "Provider License Number State Code_1",
            "Healthcare Provider Primary Taxonomy Switch_1",
            "Healthcare Provider Taxonomy Code_2",
            "Provider License Number_2",
            "Provider License Number State Code_2",
            "Healthcare Provider Primary Taxonomy Switch_2",
            "Healthcare Provider Taxonomy Code_3",
            "Provider License Number_3",
            "Provider License Number State Code_3",
            "Healthcare Provider Primary Taxonomy Switch_3",
            "Healthcare Provider Taxonomy Code_4",
            "Provider License Number_4",
            "Provider License Number State Code_4",
            "Healthcare Provider Primary Taxonomy Switch_4",
            "Healthcare Provider Taxonomy Code_5",
            "Provider License Number_5",
            "Provider License Number State Code_5",
            "Healthcare Provider Primary Taxonomy Switch_5",
            "Healthcare Provider Taxonomy Code_6",
            "Provider License Number_6",
            "Provider License Number State Code_6",
            "Healthcare Provider Primary Taxonomy Switch_6",
            "Healthcare Provider Taxonomy Code_7",
            "Provider License Number_7",
            "Provider License Number State Code_7",
            "Healthcare Provider Primary Taxonomy Switch_7",
            "Healthcare Provider Taxonomy Code_8",
            "Provider License Number_8",
            "Provider License Number State Code_8",
            "Healthcare Provider Primary Taxonomy Switch_8",
            "Healthcare Provider Taxonomy Code_9",
            "Provider License Number_9",
            "Provider License Number State Code_9",
            "Healthcare Provider Primary Taxonomy Switch_9",
            "Healthcare Provider Taxonomy Code_10",
            "Provider License Number_10",
            "Provider License Number State Code_10",
            "Healthcare Provider Primary Taxonomy Switch_10",
            "Healthcare Provider Taxonomy Code_11",
            "Provider License Number_11",
            "Provider License Number State Code_11",
            "Healthcare Provider Primary Taxonomy Switch_11",
            "Healthcare Provider Taxonomy Code_12",
            "Provider License Number_12",
            "Provider License Number State Code_12",
            "Healthcare Provider Primary Taxonomy Switch_12",
            "Healthcare Provider Taxonomy Code_13",
            "Provider License Number_13",
            "Provider License Number State Code_13",
            "Healthcare Provider Primary Taxonomy Switch_13",
            "Healthcare Provider Taxonomy Code_14",
            "Provider License Number_14",
            "Provider License Number State Code_14",
            "Healthcare Provider Primary Taxonomy Switch_14",
            "Healthcare Provider Taxonomy Code_15",
            "Provider License Number_15",
            "Provider License Number State Code_15",
            "Healthcare Provider Primary Taxonomy Switch_15",
            "Other Provider Identifier_1",
            "Other Provider Identifier Type Code_1",
            "Other Provider Identifier State_1",
            "Other Provider Identifier Issuer_1",
            "Other Provider Identifier_2",
            "Other Provider Identifier Type Code_2",
            "Other Provider Identifier State_2",
            "Other Provider Identifier Issuer_2",
            "Other Provider Identifier_3",
            "Other Provider Identifier Type Code_3",
            "Other Provider Identifier State_3",
            "Other Provider Identifier Issuer_3",
            "Other Provider Identifier_4",
            "Other Provider Identifier Type Code_4",
            "Other Provider Identifier State_4",
            "Other Provider Identifier Issuer_4",
            "Other Provider Identifier_5",
            "Other Provider Identifier Type Code_5",
            "Other Provider Identifier State_5",
            "Other Provider Identifier Issuer_5",
            "Other Provider Identifier_6",
            "Other Provider Identifier Type Code_6",
            "Other Provider Identifier State_6",
            "Other Provider Identifier Issuer_6",
            "Other Provider Identifier_7",
            "Other Provider Identifier Type Code_7",
            "Other Provider Identifier State_7",
            "Other Provider Identifier Issuer_7",
            "Other Provider Identifier_8",
            "Other Provider Identifier Type Code_8",
            "Other Provider Identifier State_8",
            "Other Provider Identifier Issuer_8",
            "Other Provider Identifier_9",
            "Other Provider Identifier Type Code_9",
            "Other Provider Identifier State_9",
            "Other Provider Identifier Issuer_9",
            "Other Provider Identifier_10",
            "Other Provider Identifier Type Code_10",
            "Other Provider Identifier State_10",
            "Other Provider Identifier Issuer_10",
            "Other Provider Identifier_11",
            "Other Provider Identifier Type Code_11",
            "Other Provider Identifier State_11",
            "Other Provider Identifier Issuer_11",
            "Other Provider Identifier_12",
            "Other Provider Identifier Type Code_12",
            "Other Provider Identifier State_12",
            "Other Provider Identifier Issuer_12",
            "Other Provider Identifier_13",
            "Other Provider Identifier Type Code_13",
            "Other Provider Identifier State_13",
            "Other Provider Identifier Issuer_13",
            "Other Provider Identifier_14",
            "Other Provider Identifier Type Code_14",
            "Other Provider Identifier State_14",
            "Other Provider Identifier Issuer_14",
            "Other Provider Identifier_15",
            "Other Provider Identifier Type Code_15",
            "Other Provider Identifier State_15",
            "Other Provider Identifier Issuer_15",
            "Other Provider Identifier_16",
            "Other Provider Identifier Type Code_16",
            "Other Provider Identifier State_16",
            "Other Provider Identifier Issuer_16",
            "Other Provider Identifier_17",
            "Other Provider Identifier Type Code_17",
            "Other Provider Identifier State_17",
            "Other Provider Identifier Issuer_17",
            "Other Provider Identifier_18",
            "Other Provider Identifier Type Code_18",
            "Other Provider Identifier State_18",
            "Other Provider Identifier Issuer_18",
            "Other Provider Identifier_19",
            "Other Provider Identifier Type Code_19",
            "Other Provider Identifier State_19",
            "Other Provider Identifier Issuer_19",
            "Other Provider Identifier_20",
            "Other Provider Identifier Type Code_20",
            "Other Provider Identifier State_20",
            "Other Provider Identifier Issuer_20",
            "Other Provider Identifier_21",
            "Other Provider Identifier Type Code_21",
            "Other Provider Identifier State_21",
            "Other Provider Identifier Issuer_21",
            "Other Provider Identifier_22",
            "Other Provider Identifier Type Code_22",
            "Other Provider Identifier State_22",
            "Other Provider Identifier Issuer_22",
            "Other Provider Identifier_23",
            "Other Provider Identifier Type Code_23",
            "Other Provider Identifier State_23",
            "Other Provider Identifier Issuer_23",
            "Other Provider Identifier_24",
            "Other Provider Identifier Type Code_24",
            "Other Provider Identifier State_24",
            "Other Provider Identifier Issuer_24",
            "Other Provider Identifier_25",
            "Other Provider Identifier Type Code_25",
            "Other Provider Identifier State_25",
            "Other Provider Identifier Issuer_25",
            "Other Provider Identifier_26",
            "Other Provider Identifier Type Code_26",
            "Other Provider Identifier State_26",
            "Other Provider Identifier Issuer_26",
            "Other Provider Identifier_27",
            "Other Provider Identifier Type Code_27",
            "Other Provider Identifier State_27",
            "Other Provider Identifier Issuer_27",
            "Other Provider Identifier_28",
            "Other Provider Identifier Type Code_28",
            "Other Provider Identifier State_28",
            "Other Provider Identifier Issuer_28",
            "Other Provider Identifier_29",
            "Other Provider Identifier Type Code_29",
            "Other Provider Identifier State_29",
            "Other Provider Identifier Issuer_29",
            "Other Provider Identifier_30",
            "Other Provider Identifier Type Code_30",
            "Other Provider Identifier State_30",
            "Other Provider Identifier Issuer_30",
            "Other Provider Identifier_31",
            "Other Provider Identifier Type Code_31",
            "Other Provider Identifier State_31",
            "Other Provider Identifier Issuer_31",
            "Other Provider Identifier_32",
            "Other Provider Identifier Type Code_32",
            "Other Provider Identifier State_32",
            "Other Provider Identifier Issuer_32",
            "Other Provider Identifier_33",
            "Other Provider Identifier Type Code_33",
            "Other Provider Identifier State_33",
            "Other Provider Identifier Issuer_33",
            "Other Provider Identifier_34",
            "Other Provider Identifier Type Code_34",
            "Other Provider Identifier State_34",
            "Other Provider Identifier Issuer_34",
            "Other Provider Identifier_35",
            "Other Provider Identifier Type Code_35",
            "Other Provider Identifier State_35",
            "Other Provider Identifier Issuer_35",
            "Other Provider Identifier_36",
            "Other Provider Identifier Type Code_36",
            "Other Provider Identifier State_36",
            "Other Provider Identifier Issuer_36",
            "Other Provider Identifier_37",
            "Other Provider Identifier Type Code_37",
            "Other Provider Identifier State_37",
            "Other Provider Identifier Issuer_37",
            "Other Provider Identifier_38",
            "Other Provider Identifier Type Code_38",
            "Other Provider Identifier State_38",
            "Other Provider Identifier Issuer_38",
            "Other Provider Identifier_39",
            "Other Provider Identifier Type Code_39",
            "Other Provider Identifier State_39",
            "Other Provider Identifier Issuer_39",
            "Other Provider Identifier_40",
            "Other Provider Identifier Type Code_40",
            "Other Provider Identifier State_40",
            "Other Provider Identifier Issuer_40",
            "Other Provider Identifier_41",
            "Other Provider Identifier Type Code_41",
            "Other Provider Identifier State_41",
            "Other Provider Identifier Issuer_41",
            "Other Provider Identifier_42",
            "Other Provider Identifier Type Code_42",
            "Other Provider Identifier State_42",
            "Other Provider Identifier Issuer_42",
            "Other Provider Identifier_43",
            "Other Provider Identifier Type Code_43",
            "Other Provider Identifier State_43",
            "Other Provider Identifier Issuer_43",
            "Other Provider Identifier_44",
            "Other Provider Identifier Type Code_44",
            "Other Provider Identifier State_44",
            "Other Provider Identifier Issuer_44",
            "Other Provider Identifier_45",
            "Other Provider Identifier Type Code_45",
            "Other Provider Identifier State_45",
            "Other Provider Identifier Issuer_45",
            "Other Provider Identifier_46",
            "Other Provider Identifier Type Code_46",
            "Other Provider Identifier State_46",
            "Other Provider Identifier Issuer_46",
            "Other Provider Identifier_47",
            "Other Provider Identifier Type Code_47",
            "Other Provider Identifier State_47",
            "Other Provider Identifier Issuer_47",
            "Other Provider Identifier_48",
            "Other Provider Identifier Type Code_48",
            "Other Provider Identifier State_48",
            "Other Provider Identifier Issuer_48",
            "Other Provider Identifier_49",
            "Other Provider Identifier Type Code_49",
            "Other Provider Identifier State_49",
            "Other Provider Identifier Issuer_49",
            "Other Provider Identifier_50",
            "Other Provider Identifier Type Code_50",
            "Other Provider Identifier State_50",
            "Other Provider Identifier Issuer_50",
            "Is Sole Proprietor",
            "Is Organization Subpart",
            "Parent Organization LBN",
            "Parent Organization TIN",
            "Authorized Official Name Prefix Text",
            "Authorized Official Name Suffix Text",
            "Authorized Official Credential Text",
            "Healthcare Provider Taxonomy Group_1",
            "Healthcare Provider Taxonomy Group_2",
            "Healthcare Provider Taxonomy Group_3",
            "Healthcare Provider Taxonomy Group_4",
            "Healthcare Provider Taxonomy Group_5",
            "Healthcare Provider Taxonomy Group_6",
            "Healthcare Provider Taxonomy Group_7",
            "Healthcare Provider Taxonomy Group_8",
            "Healthcare Provider Taxonomy Group_9",
            "Healthcare Provider Taxonomy Group_10",
            "Healthcare Provider Taxonomy Group_11",
            "Healthcare Provider Taxonomy Group_12",
            "Healthcare Provider Taxonomy Group_13",
            "Healthcare Provider Taxonomy Group_14",
            "Healthcare Provider Taxonomy Group_15"};
    return mapping;
    }
}


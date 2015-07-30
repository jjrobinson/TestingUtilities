/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boiseitoncall.utilities.npigen.models;

import java.util.ArrayList;

public class NPI {
    private String NPI;
    private String Entity_Type_Code;
    private String Replacement_NPI;
    private String Is_Sole_Proprietor;
    private String Is_Organization_Subpart;
    private String Parent_Organization_LBN;
    private String Parent_Organization_TIN;
    private String Employer_Identification_Number_EIN;

    private String Provider_Organization_Name_Legal_Business_Name;
    private String Provider_Last_Name_Legal_Name;
    
    private String Provider_First_Name;
    private String Provider_Middle_Name;
    private String Provider_Name_Prefix_Text;
    private String Provider_Name_Suffix_Text;
    private String Provider_Credential_Text;

    private String Provider_Other_Organization_Name;
    private String Provider_Other_Organization_Name_Type_Code;

    private String Provider_Other_Last_Name;
    private String Provider_Other_First_Name;
    private String Provider_Other_Middle_Name;
    private String Provider_Other_Name_Prefix_Text;
    private String Provider_Other_Name_Suffix_Text;
    private String Provider_Other_Credential_Text;
    private String Provider_Other_Last_Name_Type_Code;


    private String Provider_Enumeration_Date;
    private String Last_Update_Date;
    private String NPI_Deactivation_Reason_Code;
    private String NPI_Deactivation_Date;
    private String NPI_Reactivation_Date;
    private String Provider_Gender_Code;

    private String Authorized_Official_Last_Name;
    private String Authorized_Official_First_Name;
    private String Authorized_Official_Middle_Name;
    private String Authorized_Official_Title_or_Position;
    private String Authorized_Official_Telephone_Number;
    private String Authorized_Official_Name_Prefix_Text;
    private String Authorized_Official_Name_Suffix_Text;
    private String Authorized_Official_Credential_Text;

    private Address ProviderBusinessMailingAddress;
    private Address ProviderBusinessPracticeLocation;
    private ArrayList<String> HealthcareProviderTaxonomyCodesList; //50
    private ArrayList<String> ProviderLicenseNumbersList; //50
    private ArrayList<String> ProviderLicenseNumberStateCodesList; //50;
    private ArrayList<String> HealthcareProviderPrimaryTaxonomySwitchsList; //50
    private ArrayList<String> HealthcareProviderTaxonomyGroupsList; //15
    private final int CODES_LIST_MAX = 50;
    private final int GROUPS_LIST_MAX = 15;

    
    /**
     * Default constructor
     */
    public NPI(){
        initHealthcareProviderTaxonomyCodesList();
        initProviderLicenseNumbersList();
        initProviderLicenseNumberStateCodesList();
        initHealthcareProviderPrimaryTaxonomySwitchsList();
        initHealthcareProviderTaxonomyGroupsList();
    }
    
    
    
    
    
    ////////////////////////////////////////////////////////////////////////////
    /////////////  I N I T I A L I Z E   A L L   L I S T S   ///////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    //50
    /**
     * initialize the array with empty data to avoid null pointers
     */
    private void initHealthcareProviderTaxonomyCodesList(){
        for(int i = 0; i< CODES_LIST_MAX ; i++) {
            this.HealthcareProviderTaxonomyCodesList.add(i,"");
        }
    }
    
    //50
    /**
     * initialize the array with empty data to avoid null pointers
     */
    private void initProviderLicenseNumbersList(){
        for(int i = 0; i< CODES_LIST_MAX ; i++) {
            this.ProviderLicenseNumbersList.add(i,"");
        }
    }

    //50
    /**
     * initialize the array with empty data to avoid null pointers
     */
    private void initProviderLicenseNumberStateCodesList() {
        for(int i = 0; i< CODES_LIST_MAX ; i++) {
            this.ProviderLicenseNumberStateCodesList.add(i,"");
        }
    }
    
    //50
    /**
     * initialize the array with empty data to avoid null pointers
     */
    private void initHealthcareProviderPrimaryTaxonomySwitchsList() {
        for(int i = 0; i< CODES_LIST_MAX ; i++) {
            this.HealthcareProviderPrimaryTaxonomySwitchsList.add(i,"");
        }
    }
    
    //15
    /**
     * initialize the array with empty data to avoid null pointers
     */
    private void initHealthcareProviderTaxonomyGroupsList(){
        for(int i = 0; i< GROUPS_LIST_MAX ; i++) {
            this.HealthcareProviderTaxonomyGroupsList.add(i,"");
        }
    }

    
    
    ////////////////////////////////////////////////////////////////////////////
    /////////////  G R O U P E D   L I S T S   O F   I T E M S   ///////////////
    ////////////////////////////////////////////////////////////////////////////

    ///// HealthcareProviderTaxonomyCodesList
    //50
    //CODES_LIST_MAX = 50;

    public ArrayList<String> getHealthcareProviderTaxonomyCodesList() {
        return HealthcareProviderTaxonomyCodesList;
    }

    public void setHealthcareProviderTaxonomyCodesList(ArrayList<String> newHealthcareProviderTaxonomyCodesList) {
        this.HealthcareProviderTaxonomyCodesList = newHealthcareProviderTaxonomyCodesList;
    }

    /**
     * Get a HealthcareProviderTaxonomyCode by its number
     * @param i int
     * @return 
     */
    public String getHealthcareProviderTaxonomyCodeByNumber(int i) {
        if ((i-1)>this.CODES_LIST_MAX || (i-1)<0)
            return null;
        else return this.HealthcareProviderTaxonomyCodesList.get((i-1));
    }
    
    /**
     * Sets a HealthCareProviderTaxonomyCode by the index number
     * @param i int
     * @param s String
     */
    public void setHealthcareProviderTaxonomyCodeByNumber(int i, String s) {
        this.HealthcareProviderTaxonomyCodesList.set((i-1), s);
    }
    
    
    ///// ProviderLicenseNumbersList 
    //50
    //CODES_LIST_MAX = 50;

    public ArrayList<String> getProviderLicenseNumbersList() {
        return ProviderLicenseNumbersList;
    }

    public void setProviderLicenseNumbersList(ArrayList<String> newProviderLicenseNumbersList) {
        this.ProviderLicenseNumbersList = newProviderLicenseNumbersList;
    }

    /**
     * Get a ProviderLicenseNumbersList by its number
     * @param i int
     * @return 
     */
    public String getProviderLicenseNumbersListByNumber(int i) {
        if ((i-1)>this.CODES_LIST_MAX || (i-1)<0)
            return null;
        else return this.ProviderLicenseNumbersList.get((i-1));
    }
    
    /**
     * Sets a ProviderLicenseNumbersList by the index number
     * @param i int
     * @param s String
     */
    public void setProviderLicenseNumbersListByNumber(int i, String s) {
        this.ProviderLicenseNumbersList.set((i-1), s);
    }

    
    ///// ProviderLicenseNumberStateCodesList
    //50
    //CODES_LIST_MAX = 50;
    
    public ArrayList<String> getProviderLicenseNumberStateCodesList() {
        return ProviderLicenseNumberStateCodesList;
    }

    public void setProviderLicenseNumberStateCodesList(ArrayList<String> newProviderLicenseNumberStateCodesList) {
        this.ProviderLicenseNumberStateCodesList = newProviderLicenseNumberStateCodesList;
    }

    /**
     * Get a ProviderLicenseNumberStateCodesList by its number
     * @param i int
     * @return 
     */
    public String getProviderLicenseNumberStateCodesListByNumber(int i) {
        if ((i-1)>this.CODES_LIST_MAX || (i-1)<0)
            return null;
        else return this.ProviderLicenseNumberStateCodesList.get((i-1));
    }
    
    /**
     * Sets a ProviderLicenseNumberStateCodesList by the index number
     * @param i int
     * @param s String
     */
    public void setProviderLicenseNumberStateCodesListByNumber(int i, String s) {
        this.ProviderLicenseNumberStateCodesList.set((i-1), s);
    }

    
    ///// HealthcareProviderPrimaryTaxonomySwitchsList
    //15
    //GROUPS_LIST_MAX = 15;
    
    public ArrayList<String> getHealthcareProviderPrimaryTaxonomySwitchsList() {
        return HealthcareProviderPrimaryTaxonomySwitchsList;
    }

    public void setHealthcareProviderPrimaryTaxonomySwitchsList(ArrayList<String> newHealthcareProviderPrimaryTaxonomySwitchsList) {
        this.HealthcareProviderPrimaryTaxonomySwitchsList = newHealthcareProviderPrimaryTaxonomySwitchsList;
    }

    /**
     * Get a HealthcareProviderPrimaryTaxonomySwitchsList by its number
     * @param i int
     * @return 
     */
    public String getHealthcareProviderPrimaryTaxonomySwitchsListByNumber(int i) {
        if ((i-1)>this.GROUPS_LIST_MAX || (i-1)<0)
            return null;
        else return this.HealthcareProviderPrimaryTaxonomySwitchsList.get((i-1));
    }
    
    /**
     * Sets a HealthcareProviderPrimaryTaxonomySwitchsList by the index number
     * @param i int
     * @param s String
     */
    public void setHealthcareProviderPrimaryTaxonomySwitchsListByNumber(int i, String s) {
        this.HealthcareProviderPrimaryTaxonomySwitchsList.set((i-1), s);
    }

    
    ////////////////////////////////////////////////////////////////////////////
    /////////////    A D D R E S S E S   ///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    
    public ArrayList<String> getHealthcareProviderTaxonomyGroupsList() {
        return HealthcareProviderTaxonomyGroupsList;
    }

    public void setHealthcareProviderTaxonomyGroupsList(ArrayList<String> HealthcareProviderTaxonomyGroupsList) {
        this.HealthcareProviderTaxonomyGroupsList = HealthcareProviderTaxonomyGroupsList;
    }

    public Address getProviderBusinessMailingAddress() {
        return ProviderBusinessMailingAddress;
    }

    public void setProviderBusinessMailingAddress(Address ProviderBusinessMailingAddress) {
        this.ProviderBusinessMailingAddress = ProviderBusinessMailingAddress;
    }

    public Address getProviderBusinessPracticeLocation() {
        return ProviderBusinessPracticeLocation;
    }

    public void setProviderBusinessPracticeLocation(Address ProviderBusinessPracticeLocation) {
        this.ProviderBusinessPracticeLocation = ProviderBusinessPracticeLocation;
    }
    
    
    ////////////////////////////////////////////////////////////////////////////
    /////////////   S I M P L E   I T E M S    /////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    public String getNPI() {
        return NPI;
    }

    public void setNPI(String NPI) {
        this.NPI = NPI;
    }

    public String getEntity_Type_Code() {
        return Entity_Type_Code;
    }

    public void setEntity_Type_Code(String Entity_Type_Code) {
        this.Entity_Type_Code = Entity_Type_Code;
    }

    public String getReplacement_NPI() {
        return Replacement_NPI;
    }

    public void setReplacement_NPI(String Replacement_NPI) {
        this.Replacement_NPI = Replacement_NPI;
    }

    public String getIs_Sole_Proprietor() {
        return Is_Sole_Proprietor;
    }

    public void setIs_Sole_Proprietor(String Is_Sole_Proprietor) {
        this.Is_Sole_Proprietor = Is_Sole_Proprietor;
    }

    public String getIs_Organization_Subpart() {
        return Is_Organization_Subpart;
    }

    public void setIs_Organization_Subpart(String Is_Organization_Subpart) {
        this.Is_Organization_Subpart = Is_Organization_Subpart;
    }

    public String getParent_Organization_LBN() {
        return Parent_Organization_LBN;
    }

    public void setParent_Organization_LBN(String Parent_Organization_LBN) {
        this.Parent_Organization_LBN = Parent_Organization_LBN;
    }

    public String getParent_Organization_TIN() {
        return Parent_Organization_TIN;
    }

    public void setParent_Organization_TIN(String Parent_Organization_TIN) {
        this.Parent_Organization_TIN = Parent_Organization_TIN;
    }

    public String getEmployer_Identification_Number_EIN() {
        return Employer_Identification_Number_EIN;
    }

    public void setEmployer_Identification_Number_EIN(String Employer_Identification_Number_EIN) {
        this.Employer_Identification_Number_EIN = Employer_Identification_Number_EIN;
    }

    public String getProvider_Organization_Name_Legal_Business_Name() {
        return Provider_Organization_Name_Legal_Business_Name;
    }

    public void setProvider_Organization_Name_Legal_Business_Name(String Provider_Organization_Name_Legal_Business_Name) {
        this.Provider_Organization_Name_Legal_Business_Name = Provider_Organization_Name_Legal_Business_Name;
    }

    public String getProvider_Last_Name_Legal_Name() {
        return Provider_Last_Name_Legal_Name;
    }

    public void setProvider_Last_Name_Legal_Name(String Provider_Last_Name_Legal_Name) {
        this.Provider_Last_Name_Legal_Name = Provider_Last_Name_Legal_Name;
    }

    public String getProvider_First_Name() {
        return Provider_First_Name;
    }

    public void setProvider_First_Name(String Provider_First_Name) {
        this.Provider_First_Name = Provider_First_Name;
    }

    public String getProvider_Middle_Name() {
        return Provider_Middle_Name;
    }

    public void setProvider_Middle_Name(String Provider_Middle_Name) {
        this.Provider_Middle_Name = Provider_Middle_Name;
    }

    public String getProvider_Name_Prefix_Text() {
        return Provider_Name_Prefix_Text;
    }

    public void setProvider_Name_Prefix_Text(String Provider_Name_Prefix_Text) {
        this.Provider_Name_Prefix_Text = Provider_Name_Prefix_Text;
    }

    public String getProvider_Name_Suffix_Text() {
        return Provider_Name_Suffix_Text;
    }

    public void setProvider_Name_Suffix_Text(String Provider_Name_Suffix_Text) {
        this.Provider_Name_Suffix_Text = Provider_Name_Suffix_Text;
    }

    public String getProvider_Credential_Text() {
        return Provider_Credential_Text;
    }

    public void setProvider_Credential_Text(String Provider_Credential_Text) {
        this.Provider_Credential_Text = Provider_Credential_Text;
    }

    public String getProvider_Other_Organization_Name() {
        return Provider_Other_Organization_Name;
    }

    public void setProvider_Other_Organization_Name(String Provider_Other_Organization_Name) {
        this.Provider_Other_Organization_Name = Provider_Other_Organization_Name;
    }

    public String getProvider_Other_Organization_Name_Type_Code() {
        return Provider_Other_Organization_Name_Type_Code;
    }

    public void setProvider_Other_Organization_Name_Type_Code(String Provider_Other_Organization_Name_Type_Code) {
        this.Provider_Other_Organization_Name_Type_Code = Provider_Other_Organization_Name_Type_Code;
    }

    public String getProvider_Other_Last_Name() {
        return Provider_Other_Last_Name;
    }

    public void setProvider_Other_Last_Name(String Provider_Other_Last_Name) {
        this.Provider_Other_Last_Name = Provider_Other_Last_Name;
    }

    public String getProvider_Other_First_Name() {
        return Provider_Other_First_Name;
    }

    public void setProvider_Other_First_Name(String Provider_Other_First_Name) {
        this.Provider_Other_First_Name = Provider_Other_First_Name;
    }

    public String getProvider_Other_Middle_Name() {
        return Provider_Other_Middle_Name;
    }

    public void setProvider_Other_Middle_Name(String Provider_Other_Middle_Name) {
        this.Provider_Other_Middle_Name = Provider_Other_Middle_Name;
    }

    public String getProvider_Other_Name_Prefix_Text() {
        return Provider_Other_Name_Prefix_Text;
    }

    public void setProvider_Other_Name_Prefix_Text(String Provider_Other_Name_Prefix_Text) {
        this.Provider_Other_Name_Prefix_Text = Provider_Other_Name_Prefix_Text;
    }

    public String getProvider_Other_Name_Suffix_Text() {
        return Provider_Other_Name_Suffix_Text;
    }

    public void setProvider_Other_Name_Suffix_Text(String Provider_Other_Name_Suffix_Text) {
        this.Provider_Other_Name_Suffix_Text = Provider_Other_Name_Suffix_Text;
    }

    public String getProvider_Other_Credential_Text() {
        return Provider_Other_Credential_Text;
    }

    public void setProvider_Other_Credential_Text(String Provider_Other_Credential_Text) {
        this.Provider_Other_Credential_Text = Provider_Other_Credential_Text;
    }

    public String getProvider_Other_Last_Name_Type_Code() {
        return Provider_Other_Last_Name_Type_Code;
    }

    public void setProvider_Other_Last_Name_Type_Code(String Provider_Other_Last_Name_Type_Code) {
        this.Provider_Other_Last_Name_Type_Code = Provider_Other_Last_Name_Type_Code;
    }

    public String getProvider_Enumeration_Date() {
        return Provider_Enumeration_Date;
    }

    public void setProvider_Enumeration_Date(String Provider_Enumeration_Date) {
        this.Provider_Enumeration_Date = Provider_Enumeration_Date;
    }

    public String getLast_Update_Date() {
        return Last_Update_Date;
    }

    public void setLast_Update_Date(String Last_Update_Date) {
        this.Last_Update_Date = Last_Update_Date;
    }

    public String getNPI_Deactivation_Reason_Code() {
        return NPI_Deactivation_Reason_Code;
    }

    public void setNPI_Deactivation_Reason_Code(String NPI_Deactivation_Reason_Code) {
        this.NPI_Deactivation_Reason_Code = NPI_Deactivation_Reason_Code;
    }

    public String getNPI_Deactivation_Date() {
        return NPI_Deactivation_Date;
    }

    public void setNPI_Deactivation_Date(String NPI_Deactivation_Date) {
        this.NPI_Deactivation_Date = NPI_Deactivation_Date;
    }

    public String getNPI_Reactivation_Date() {
        return NPI_Reactivation_Date;
    }

    public void setNPI_Reactivation_Date(String NPI_Reactivation_Date) {
        this.NPI_Reactivation_Date = NPI_Reactivation_Date;
    }

    public String getProvider_Gender_Code() {
        return Provider_Gender_Code;
    }

    public void setProvider_Gender_Code(String Provider_Gender_Code) {
        this.Provider_Gender_Code = Provider_Gender_Code;
    }

    public String getAuthorized_Official_Last_Name() {
        return Authorized_Official_Last_Name;
    }

    public void setAuthorized_Official_Last_Name(String Authorized_Official_Last_Name) {
        this.Authorized_Official_Last_Name = Authorized_Official_Last_Name;
    }

    public String getAuthorized_Official_First_Name() {
        return Authorized_Official_First_Name;
    }

    public void setAuthorized_Official_First_Name(String Authorized_Official_First_Name) {
        this.Authorized_Official_First_Name = Authorized_Official_First_Name;
    }

    public String getAuthorized_Official_Middle_Name() {
        return Authorized_Official_Middle_Name;
    }

    public void setAuthorized_Official_Middle_Name(String Authorized_Official_Middle_Name) {
        this.Authorized_Official_Middle_Name = Authorized_Official_Middle_Name;
    }

    public String getAuthorized_Official_Title_or_Position() {
        return Authorized_Official_Title_or_Position;
    }

    public void setAuthorized_Official_Title_or_Position(String Authorized_Official_Title_or_Position) {
        this.Authorized_Official_Title_or_Position = Authorized_Official_Title_or_Position;
    }

    public String getAuthorized_Official_Telephone_Number() {
        return Authorized_Official_Telephone_Number;
    }

    public void setAuthorized_Official_Telephone_Number(String Authorized_Official_Telephone_Number) {
        this.Authorized_Official_Telephone_Number = Authorized_Official_Telephone_Number;
    }

    public String getAuthorized_Official_Name_Prefix_Text() {
        return Authorized_Official_Name_Prefix_Text;
    }

    public void setAuthorized_Official_Name_Prefix_Text(String Authorized_Official_Name_Prefix_Text) {
        this.Authorized_Official_Name_Prefix_Text = Authorized_Official_Name_Prefix_Text;
    }

    public String getAuthorized_Official_Name_Suffix_Text() {
        return Authorized_Official_Name_Suffix_Text;
    }

    public void setAuthorized_Official_Name_Suffix_Text(String Authorized_Official_Name_Suffix_Text) {
        this.Authorized_Official_Name_Suffix_Text = Authorized_Official_Name_Suffix_Text;
    }

    public String getAuthorized_Official_Credential_Text() {
        return Authorized_Official_Credential_Text;
    }

    public void setAuthorized_Official_Credential_Text(String Authorized_Official_Credential_Text) {
        this.Authorized_Official_Credential_Text = Authorized_Official_Credential_Text;
    }


    public static String[] getMapping(){
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

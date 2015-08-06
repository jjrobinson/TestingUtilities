/*
 * 
 */
package com.boiseitoncall.utilities.npigen.models;

/**
 * Stores an address
 */
public class Address {
    private String addressTypeName;
    private String addressLine1;
    private String addressLine2;
    private String cityName;
    private String stateName;
    private String postalCode;
    private String countryCodeIfoutsideUS;
    private String telephoneNumber;
    private String faxNumber;

    public String getAddressTypeName() {
        return addressTypeName;
    }

    public void setAddressTypeName(String addressTypeName) {
        this.addressTypeName = addressTypeName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCodeIfoutsideUS() {
        return countryCodeIfoutsideUS;
    }

    public void setCountryCodeIfoutsideUS(String countryCodeIfoutsideUS) {
        this.countryCodeIfoutsideUS = countryCodeIfoutsideUS;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }
    
    
}

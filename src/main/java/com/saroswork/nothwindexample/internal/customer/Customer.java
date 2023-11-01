package com.saroswork.nothwindexample.internal.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Customers")
public class Customer implements Serializable {
    @Id
    @NotEmpty
    @Column(columnDefinition = "NCHAR(5)")
    private String customerID;

    @NotEmpty
    @Column(name = "CompanyName", length = 40)
    private String companyName;

    @NotEmpty
    @Column(name = "ContactName", length = 30)
    private String contactName;

    @NotEmpty
    @Column(name = "ContactTitle", length = 30)
    private String contactTitle;


    @Column(name = "Address", length = 60)
    private String address;

    @NotEmpty
    @Column(name = "City", length = 15)
    private String city;
    @Column(name = "Region", length = 15)
    private String region;
    @Column(name = "PostalCode", length = 10)
    private String postalCode;

    @NotEmpty
    @Column(name = "Country", length = 15)
    private String country;

    @NotEmpty
    @Column(name = "Phone", length = 24)
    private String phone;
    @Column(name = "Fax", length = 24)
    private String fax;


    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        if(this.customerID != null)
            throw new CustomerException("ERROR: You cannot change customerID");
        this.customerID = customerID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerID, customer.customerID) && Objects.equals(companyName, customer.companyName) && Objects.equals(contactName, customer.contactName) && Objects.equals(contactTitle, customer.contactTitle) && Objects.equals(address, customer.address) && Objects.equals(city, customer.city) && Objects.equals(region, customer.region) && Objects.equals(postalCode, customer.postalCode) && Objects.equals(country, customer.country) && Objects.equals(phone, customer.phone) && Objects.equals(fax, customer.fax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID, companyName, contactName, contactTitle, address, city, region, postalCode, country, phone, fax);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", companyName='" + companyName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactTitle='" + contactTitle + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                '}';
    }
}

package com.example.Biografen.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Staff {

    @Id
    @GeneratedValue
    private Long idstaff;
    private String firstName;
    private String lastName;
    private String address;
    private String city;

    //TODO - ev sätt postalCode till | private Integer alt | int
    private String postalCode;
    private String phone;
    private String email;
    private String socialSecurityNo;

    protected Staff(){
    }


    public Staff(String firstName,
                 String lastName,
                 String address,
                 String city,
                 String postalCode,
                 String phone,
                 String email,
                 String socialSecurityNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.phone = phone;
        this.email = email;
        this.socialSecurityNo = socialSecurityNo;
    }


    public Long getIdstaff(){return idstaff;}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() { return lastName; }

    public String getAddress() { return address; }

    public String getCity() { return city; }

    public String getPostalCode() { return postalCode; }

    public String getPhone() { return phone; }

    public String getEmail() { return email; }

    public String getSocialSecurityNo() { return socialSecurityNo; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setAddress(String address) { this.address = address; }

    public void setCity(String city) { this.city = city; }

    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public void setPhone(String phone) { this.phone = phone; }

    public void setEmail(String email) { this.email = email; }

    public void setSocialSecurityNo(String socialSecurityNo) { this.socialSecurityNo = socialSecurityNo; }


    @Override
    public String toString() {
        return  "Staff | " +
                idstaff + " _ " +
                firstName + " - " +
                lastName + " - " +
                address + " - " +
                city + " - " +
                postalCode + " - " +
                phone + " - " +
                email + " - " +
                socialSecurityNo + " |";
    }
}

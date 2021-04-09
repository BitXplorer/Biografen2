package com.example.Biografen.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Staff {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String postalCode;
    private String phone;
    private String email;
    private String socialSecurityNo;


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

    public String getFirstName() {
        return firstName;
    }
    public Integer getId(){return id;}

    @Override
    public String toString() {
        return  "Staff | " +
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

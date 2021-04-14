package com.example.Biografen.Objects;

import javax.persistence.*;

@Table(name = "staff", schema = "cinema")
@Entity

public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_staff")
    private Long id_staff;
    @Column(name="FirstName")
    private String firstName;
    @Column(name="LastName")
    private String lastName;
    @Column(name="Address")
    private String address;
    @Column(name="City")
    private String city;

    //TODO - ev sätt postalCode till | private Integer alt | int
    @Column(name="PostalCode")
    private String postalCode;
    @Column(name="Phone")
    private String phone;
    @Column(name = "Email", unique = true)
    private String email;
    @Column(name="Shifts_idShifts")
    private String shift;
    @Column(name="SocialSecurityNo")
    private String socialSecurityNo;
    protected Staff(){
    }


    public Staff(String FirstName,
                 String LastName,
                 String Address,
                 String City,
                 String PostalCode,
                 String Phone,
                 String Email,
                 String Shift,
                 String SocialSecurityNo) {
        this.firstName = FirstName;
        this.lastName = LastName;
        this.address = Address;
        this.city = City;
        this.postalCode = PostalCode;
        this.phone = Phone;
        this.email = Email;
        this.shift = Shift;
        this.socialSecurityNo = SocialSecurityNo;
    }


    public Long getId_staff(){return id_staff;}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() { return lastName; }

    public String getAddress() { return address; }

    public String getCity() { return city; }

    public String getPostalCode() { return postalCode; }

    public String getPhone() { return phone; }

    public String getEmail() { return email; }

    public String getShift() { return shift; }

    public String getSocialSecurityNo() { return socialSecurityNo; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setAddress(String address) { this.address = address; }

    public void setCity(String city) { this.city = city; }

    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public void setPhone(String phone) { this.phone = phone; }

    public void setEmail(String email) { this.email = email; }

    public void setShift(String shift) {this.shift = shift; }

    public void setSocialSecurityNo(String socialSecurityNo) { this.socialSecurityNo = socialSecurityNo; }


    @Override
    public String toString() {
        return String.format("Staff[id=%d, firstName='%s', lastName='%s', adress='%s', city='%s', " +
                "postalCode='%s', phone='%s', email='%s', shift='%s', socialSecurityNo='%s]", id_staff,firstName,lastName,
                address,city,postalCode,phone,email, shift, socialSecurityNo);

    }
}

package com.example.Biografen.Objects;

import javax.persistence.*;

@Table(name = "booking")
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long booking_id;

    private String first_name;
    private String last_name;
    private String Phone;
    private String Email;
    private String time_booked;
    private String booked_seats;
    private Long saloon_id_saloon;

    protected Booking() {}

    public Booking(String first_name, String last_name, String phone,
                   String email, String time_booked,
                   String booked_seats, Long saloon_id_saloon) {
        this.first_name = first_name;
        this.last_name = last_name;
        Phone = phone;
        Email = email;
        this.time_booked = time_booked;
        this.booked_seats = booked_seats;
        this.saloon_id_saloon = saloon_id_saloon;
    }

    public Long getBooking_id() {
        return booking_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getTime_booked() {
        return time_booked;
    }

    public String getBooked_seats() {
        return booked_seats;
    }

    public Long getSaloon_id_saloon() {
        return saloon_id_saloon;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setTime_booked(String time_booked) {
        this.time_booked = time_booked;
    }

    public void setBooked_seats(String booked_seats) {
        this.booked_seats = booked_seats;
    }

    public void setSaloon_id_saloon(Long saloon_id_saloon) {
        this.saloon_id_saloon = saloon_id_saloon;
    }

    @Override
    public String toString(){
        return String.format("Booking[id=%d, firstName='%s', lastName=%s, phone='%s', email='%s', " +
                        "time_booked='%s', booked_seats='%s', saloon_id='%d']",
                booking_id, first_name, last_name, Phone, Email, time_booked, booked_seats, saloon_id_saloon);
    }
}

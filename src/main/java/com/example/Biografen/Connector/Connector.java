package com.example.Biografen.Connector;

import com.example.Biografen.Objects.Staff;

import java.sql.SQLException;
import java.util.Optional;

public interface Connector {

    Optional <Staff> getPerson(String firstName);
    void callAddMovie(String name, String length, String genre) throws SQLException;
    void callAddSaloon(String name, String seats) throws SQLException;
    void callAddShift(String name, String length) throws SQLException;
    void callAddStaff(String firstName,
                      String lastName,
                      String adress,
                      String city,
                      String postalCode,
                      String phone,
                      String email,
                      String socialSecurityNo
    )

            throws SQLException;
    void callcreate_booking(Integer MovieID, String FirstName, String LastName, String Phone,
                           String Email, Integer BookedSeats) throws SQLException;

}

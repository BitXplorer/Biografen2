package com.example.Biografen.Connector;

import com.example.Biografen.Objects.Staff;

import java.sql.SQLException;
import java.util.Optional;

public interface Connector {

    public Optional <Staff> getPerson (String firstName);
    public void callAddMovie (String name, String length, String genre ) throws SQLException;
    public void callAddShift (String name, String length ) throws SQLException;
    public void callAddStaff (String firstName,
                              String lastName,
                              String adress,
                              String city,
                              String postalCode,
                              String phone,
                              String email,
                              String socialSecurityNo
    ) throws SQLException;

}

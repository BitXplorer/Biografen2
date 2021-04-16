package com.example.Biografen;

import com.example.Biografen.Connector.Connector;
import com.example.Biografen.Objects.Staff;

import java.sql.*;
import java.util.Optional;

public class ConnectorMySQL implements Connector {
    //TODO
    //Skapa en connector till databasen
    private Connection myConn;

    public ConnectorMySQL() {
        try {
            String url = "jdbc:mysql://90.228.222.153/cinema";
            String user = "mikael";
            String password = "newton";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            myConn = DriverManager.getConnection(url, user, password);

            System.out.println("YOU ARE NOW --- Connected to Database: -> " + url + "\n");

        } catch (Exception ex) {
            System.out.println("Oops, there´s an error");
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Staff> getPerson(String firstName) {
        return Optional.empty();
    }

    @Override
    public void callAddMovie(String name, String length, String genre) throws SQLException {

    }

    @Override
    public void callAddSaloon(String name, String seats) throws SQLException {

    }

    @Override
    public void callAddShift(String name, String length) throws SQLException {

    }

    @Override
    public void callAddStaff(String firstName, String lastName, String adress, String city, String postalCode, String phone, String email, String socialSecurityNo) throws SQLException {

    }

    @Override
    public void callcreate_booking(Integer MovieID, String FirstName, String LastName, String Phone, String Email, Integer BookedSeats) throws SQLException {

    }
}
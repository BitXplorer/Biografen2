package com.example.Biografen.Connector;




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

    /**
     * @param firstName Använd förnamnet för att plocka allt som finns i staff tabellen.
     * @return
     */
    @Override
    public Optional<Staff> getPerson(String firstName) {
        try (PreparedStatement ps = myConn.prepareStatement("SELECT * FROM staff WHERE FirstName = ?")) {
            ps.setString(1, firstName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new Staff(
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Address"),
                        rs.getString("City"),
                        rs.getString("PostalCode"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getString("SocialSecurityNo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }


    /**
     * Call till att addera en ny film till databasen.
     * @param name
     * @param length
     * @param genre
     * @throws SQLException
     */
    public void callAddMovie(String name, String length, String genre) throws SQLException {
        String sql = " {CALL add_movie (?,?,?)}";
        try (CallableStatement myStmt = myConn.prepareCall(sql)) {

            myStmt.setString(1, name);
            myStmt.setString(2, length);
            myStmt.setString(3, genre);

            boolean hadResults = myStmt.execute();

            System.out.println("=================================");
            System.out.println(" You have just - ADDED A MOVIE!");
            System.out.println("=================================");
            System.out.println(" The movie: " + name + " | Length: " + length + " | Genre: " + genre + " | " );
            System.out.println("=================================");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Call till att addera ett nytt skift till databasen.
     * @param name
     * @param length
     * @throws SQLException
     */
    public void callAddShift(String name, String length) throws SQLException {
        String sql = " {CALL add_shift (?,?)}";
        try (CallableStatement myStmt = myConn.prepareCall(sql)) {

            myStmt.setString(1, name);
            myStmt.setString(2, length);

            boolean hadResults = myStmt.execute();

            System.out.println("=================================");
            System.out.println(" You have just - ADDED A SHIFT!");
            System.out.println("=================================");
            System.out.println(" The shift: " + name + " | Length: " + length + " | " );
            System.out.println("=================================");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Call till att addera en ny staff till databasen.
     * @param firstName
     * @param lastName
     * @param adress
     * @param city
     * @param postalCode
     * @param phone
     * @param email
     * @param socialSecurityNo
     * @throws SQLException
     */
    public void callAddStaff(String firstName,
                             String lastName,
                             String adress,
                             String city,
                             String postalCode,
                             String phone,
                             String email,
                             String socialSecurityNo
    ) throws SQLException {
        String sql = " {CALL add_staff (?,?,?,?,?,?,?,?)}";
        try (CallableStatement myStmt = myConn.prepareCall(sql)) {

            myStmt.setString(1,firstName);
            myStmt.setString(2,lastName);
            myStmt.setString(3,adress);
            myStmt.setString(4,city);
            myStmt.setString(5,postalCode);
            myStmt.setString(6,phone );
            myStmt.setString(7,email);
            myStmt.setString(8,socialSecurityNo );

            boolean hadResults = myStmt.execute();

            System.out.println("=================================");
            System.out.println(" You have just - ADDED A STAFF!");
            System.out.println("=================================");
            System.out.println(" The Staff: " + firstName + " " + lastName + " | " );
            System.out.println("=================================");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
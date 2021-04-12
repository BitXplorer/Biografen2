package com.example.Biografen.Objects;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff,Long> {
    //TODO
    //Lägg till parametrar
    //Connector till SQL databasen
    //Plocka ut parametrar från databasen
    List<Staff> findByFirstNameStartsWithIgnoreCase (String firstName);
    List<Staff> findByLastNameStartsWithIgnoreCase (String lastName);
}

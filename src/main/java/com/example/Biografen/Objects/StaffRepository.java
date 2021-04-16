package com.example.Biografen.Objects;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff,Long> {

    List<Staff> findByFirstNameStartsWithIgnoreCase (String FirstName);
    List<Staff> findByLastNameStartsWithIgnoreCase (String LastName);
}

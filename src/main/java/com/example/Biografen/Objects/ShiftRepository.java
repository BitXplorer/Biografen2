package com.example.Biografen.Objects;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findByNameStartsWithIgnoreCase (String name);
}

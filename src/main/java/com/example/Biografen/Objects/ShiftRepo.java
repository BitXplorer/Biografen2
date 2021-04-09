package com.example.Biografen.Objects;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShiftRepo extends JpaRepository<Shift, Integer> {
    List<Shift> findByNameStartsWithIgnoreCase (String name);
}

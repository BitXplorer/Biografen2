package com.example.Biografen.Objects;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaloonRepository extends JpaRepository<Saloon, Long> {

    List<Saloon> findByNameStartsWithIgnoreCase (String saloonName);
}

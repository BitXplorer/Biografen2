package com.example.Biografen.Objects;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByNameStartsWithIgnoreCase (String movieName);

}

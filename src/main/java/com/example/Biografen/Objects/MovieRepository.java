package com.example.Biografen.Objects;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    //TODO
    //Lägg till parametrar
    //Connector till SQL databasen
    //Plocka ut parametrar från databasen
    List<Movie> findByNameStartsWithIgnoreCase (String movieName);

}

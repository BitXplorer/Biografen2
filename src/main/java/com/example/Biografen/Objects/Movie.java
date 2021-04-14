package com.example.Biografen.Objects;

import javax.persistence.*;

@Table(name = "Movie")
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_movies;
    private String name;
    private String length;
    private String genre;

    protected Movie(){
    }

    public Movie(String movieName, String length, String genre){
        this.name = movieName;
        this.length = length;
        this.genre = genre;
    }
    public Long getId_movies(){return id_movies;}

    public String getMovieName(){return name;}

    public void setMovieName(String movieName){this.name =movieName;}

    public String getLength(){return length;}

    public void setLength(String length){this.length = length;}

    public String getGenre() {return genre;}

    public void setGenre(String genre) {this.genre = genre;}

    @Override
    public String toString(){
        return String.format("Movie[id=%d, movieName='%s', length=%d]", id_movies, name, length);
    }
}

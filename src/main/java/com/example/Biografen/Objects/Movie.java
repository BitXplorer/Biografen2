package com.example.Biografen.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Movie")
@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Long idmovies;
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
    public Long getIdmovies(){return idmovies;}

    public String getMovieName(){return name;}

    public void setMovieName(String movieName){this.name =movieName;}

    public String getLength(){return length;}

    public void setLength(String length){this.length = this.length;}

    public String getGenre() {return genre;}

    public void setGenre(String genre) {this.genre = genre;}

    @Override
    public String toString(){
        return String.format("Movie[id=%d, movieName='%s', length=%d]", idmovies, name, length);
    }
}

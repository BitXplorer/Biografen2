package com.example.Biografen.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private long length;

    protected Movie(){
    }

    public Movie(String movieName, long length){
        this.name = movieName;
        this.length = length;
    }
    public Integer getId(){return id;}
    public String getMovieName(){return name;}
    public void setMovieName(String movieName){this.name =movieName;}
    public Long getLength(){return length;}
    public void setLength(Long length){this.length=length;}

    @Override
    public String toString(){
        return String.format("Movie[id=%d, movieName='%s', length=%d]", id, name,length);
    }
}

package com.example.Biografen.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Shift {

    @Id
    @GeneratedValue
    private Long idshifts;
    private String name;
    private String length;

    protected Shift(){
    }

    public Shift (String name, String length){
        this.name = name;
        this.length = length;
    }

    public Long getIdshifts() {
        return idshifts;
    }

    public String getName(){return name;}

    public void setShiftName(String name){this.name=name;}

    public String getLength(){return length;}

    public void setLength(String length){this.length=length;}

    @Override
    public String toString(){
        return String.format("Shift[id=%d, name='%s', length='%s'", idshifts, name, length);
    }

}

package com.example.Biografen.Objects;

import javax.persistence.*;

@Table(name = "shift")
@Entity
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_shifts")
    private Long id_shifts;
    private String name;
    private String length;

    protected Shift(){
    }

    public Shift (String name, String length){
        this.name = name;
        this.length = length;
    }

    public Long getId_shifts() {
        return id_shifts;
    }

    public String getName(){return name;}

    public void setName(String name){this.name=name;}

    public String getLength(){return length;}

    public void setLength(String length){this.length=length;}

    @Override
    public String toString(){
        return String.format("Shift[id=%d, name='%s', length='%s'", id_shifts, name, length);
    }

}

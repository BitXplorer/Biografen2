package com.example.Biografen.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Saloon {

    @Id
    @GeneratedValue
    private Long id_saloon;
    private String name;
    private String seats;

    protected Saloon(){
    }

    public Saloon(String saloonName, String seats) {
        this.name = saloonName;
        this.seats = seats;
    }

    public Long getId_saloon() { return id_saloon; }

    public String getSaloonName() { return name; }

    public void setSaloonName(String saloonName) { this.name = saloonName; }

    public String getSeats() { return seats; }

    public void setSeats(String seats) { this.seats = seats; }

    @Override
    public String toString(){
        return String.format("Saloon[id=%d, saloonName='%s', seats=%d]", id_saloon, name, seats);
    }
}

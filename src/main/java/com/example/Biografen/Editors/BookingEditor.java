package com.example.Biografen.Editors;

import com.example.Biografen.Connector.ConnectorMySQL;
import com.example.Biografen.Objects.Booking;
import com.example.Biografen.Objects.BookingRepository;
import com.example.Biografen.Objects.Movie;
import com.example.Biografen.Objects.MovieRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public class BookingEditor extends Editor {

    TextField movieName, firstName, lastName, phone, email, booked_seats;

    Button confirmBookingButton = new Button("Book Tickets");
    ConnectorMySQL connectorMySQL;


    @Autowired
    public BookingEditor(MovieRepository movieRepo, BookingRepository bookingRepository) {
        this.movieRepository = movieRepo;
        this.bookingRepository = bookingRepository;
        this.movieBinder = new Binder<>(Movie.class);
        this.bookingBinder = new Binder<>(Booking.class);
        this.firstName = new TextField("First Name");
        this.lastName = new TextField("Last Name");
        this.phone = new TextField("Phone Number");
        this.email = new TextField("Email");
        this.booked_seats = new TextField("Number of Seats");
        this.movieName = new TextField("movieName");
        this.connectorMySQL = new ConnectorMySQL();




        add(firstName, lastName, phone, email, booked_seats, confirmBookingButton);

        movieBinder.bindInstanceFields(this);
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> saveCatcher());
        confirmBookingButton.addClickListener(e -> saveCatcher());
        cancel.addClickListener(e->editBooking(movie));
        setVisible(false);
    }

    void saveCatcher(){
        try{
            connectorMySQL.callcreate_booking(Integer.valueOf(movie.getId_movies().toString()),firstName.getValue(),
                    lastName.getValue(),phone.getValue(),
                    email.getValue(),Integer.valueOf(booked_seats.getValue()));

            System.out.println("Booking complete!");
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public void editBooking(Movie selectedMovie) {
        if (selectedMovie == null){
            setVisible(false);
            return;
        }
        final boolean persisted = selectedMovie.getId_movies() != null;
        if (persisted){
            movie = movieRepository.findById(selectedMovie.getId_movies()).get();
        }else {
            movie = selectedMovie;
        }
        cancel.setVisible(persisted);

        setVisible(true);
    }

}

package com.example.Biografen.Editors;

import com.example.Biografen.Objects.Booking;
import com.example.Biografen.Objects.Movie;
import com.example.Biografen.Objects.MovieRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public class BookingEditor extends Editor {

    TextField firstName, lastName, phone, email, noOfSeats;
    H3 movieName = new H3();
    Button confirmBookingButton = new Button("Book Tickets");
    Binder<Booking> bookingBinder;

    @Autowired
    public BookingEditor(MovieRepository movieRepo) {
        this.movieRepository = movieRepo;
        this.movieBinder = new Binder<>(Movie.class);
        this.bookingBinder = new Binder<>(Booking.class);
        this.firstName = new TextField("First Name");
        this.lastName = new TextField("Last Name");
        this.phone = new TextField("Phone Number");
        this.email = new TextField("Email");
        this.noOfSeats = new TextField("Number of Seats");


        add(firstName, lastName, phone, email, noOfSeats, confirmBookingButton);

        movieBinder.bindInstanceFields(this);
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        confirmBookingButton.addClickListener(e -> saveCatcher());
        cancel.addClickListener(e->editBooking(movie));
    }

    void saveCatcher(){
        try{
            bookingBinder.writeBean(booking);
            confirmBooking(booking);
        } catch (SQLException | ValidationException throwables){
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

        movieBinder.setBean(movie);

        setVisible(true);
    }

    void confirmBooking(Booking booking) throws SQLException {
        bookingRepository.save(booking);
        changeHandler.onChange();
    }

    private void configureSelectSeats() {}

    private void configureConfirmBookingButton() {}
}

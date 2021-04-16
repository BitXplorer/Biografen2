package com.example.Biografen.Editors;

import com.example.Biografen.Objects.Movie;
import com.example.Biografen.Objects.MovieRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class BookingEditor extends Editor {

    TextField firstName, lastName, phone, email, noOfSeats;
    H3 movieName = new H3();
    Button confirmBookingButton = new Button("Book Tickets");

    public BookingEditor(MovieRepository movieRepo) {
        this.movieRepository = movieRepo;
        this.movieBinder = new Binder<>(Movie.class);
        this.firstName = new TextField("First Name");
        this.lastName = new TextField("Last Name");
        this.phone = new TextField("Phone Number");
        this.email = new TextField("Email");
        this.noOfSeats = new TextField("Number of Seats");
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

    private void configureSelectSeats() {}

    private void configureConfirmBookingButton() {}
}

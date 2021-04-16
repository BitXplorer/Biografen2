package com.example.Biografen.Editors;

import com.example.Biografen.Connector.Connector;
import com.example.Biografen.Objects.Booking;
import com.example.Biografen.Objects.BookingRepository;
import com.example.Biografen.Objects.Movie;
import com.example.Biografen.Objects.MovieRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingEditor extends Editor {

    TextField movieName, firstName, lastName, phone, email, time_booked, booked_seats;

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
        Button confirmBookingButton = new Button("Book Tickets");

        add(firstName, lastName, phone, email, booked_seats, confirmBookingButton);

        movieBinder.bindInstanceFields(this);
        //movieBinder.forField(firstName).bind(Movie::getMovieName,Movie::setMovieName);
        bookingBinder.bindInstanceFields(this);
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        //delete.getElement().getThemeList().add("error");

        confirmBookingButton.addClickListener(e -> saveCatcher());
        cancel.addClickListener(e->editBooking(movie));
        setVisible(false);
    }

    void saveCatcher(){
        setBookingTime();
        bookingRepository.save(
                new Booking(
                        firstName.getValue(),
                        lastName.getValue(),
                        phone.getValue(),
                        email.getValue(),
                        time_booked.getValue(),
                        booked_seats.getValue(),
                        2L
                )
        );
        changeHandler.onChange();
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

        //movieBinder.setBean(movie);
        //booking  = new Booking("","","","","","",1L);
        //bookingBinder.setBean(booking);
        setVisible(true);
    }

    void confirmBooking(Booking booking) throws SQLException {
        bookingRepository.save(booking);
        changeHandler.onChange();
    }

    private void setBookingTime() {
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        time_booked.setValue(dateTime);
    }

}

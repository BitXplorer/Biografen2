package com.example.Biografen.Editors;

import com.example.Biografen.Objects.Movie;
import com.example.Biografen.Objects.MovieRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public class MovieEditor extends Editor{

    TextField movieName, length, genre;

    @Autowired
    public MovieEditor (MovieRepository repo){
        this.movieRepository = repo;
        this.movieBinder = new Binder<>(Movie.class);
        movieName = new TextField("Movie name");
        length = new TextField("Length");
        genre = new TextField("Genre");

        //Lägger till knapparna
        add(movieName,length,genre, actions);

        movieBinder.bindInstanceFields(this);
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        //Listener som trycker på "save" om man trycker på enter
        addKeyPressListener(Key.ENTER, e -> saveCatcher());

        //Wire action buttons to save, delete and reset
        save.addClickListener(e -> saveCatcher());
        delete.addClickListener(e-> deleteMovie());
        cancel.addClickListener(e-> editMovie(movie));
        setVisible(false);
    }

    void saveCatcher(){
        try{
            movieBinder.writeBean(movie);
            saveMovie(movie);
        } catch (SQLException | ValidationException throwables){
            throwables.printStackTrace();
        }
    }

    void deleteMovie(){
        movieRepository.delete(movie);
        changeHandler.onChange();
    }
    void saveMovie(Movie movie) throws SQLException {
        movieRepository.save(movie);
        changeHandler.onChange();
    }
    public final void editMovie(Movie m){
        if (m == null){
            setVisible(false);
            return;
        }
        final boolean persisted = m.getId_movies() != null;
        if (persisted){
            movie = movieRepository.findById(m.getId_movies()).get();
        }else {
            movie = m;
        }
        cancel.setVisible(persisted);

        movieBinder.setBean(movie);

        setVisible(true);

        movieName.focus();
    }



}


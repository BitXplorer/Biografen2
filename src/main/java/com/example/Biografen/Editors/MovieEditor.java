package com.example.Biografen.Editors;

import com.example.Biografen.Objects.Movie;
import com.example.Biografen.Objects.MovieRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public class MovieEditor extends Editor{

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

        save.getElement().getThemeList().add("Primary");
        delete.getElement().getThemeList().add("error");

        //Listener som trycker på "save" om man trycker på enter
        addKeyPressListener(Key.ENTER, e -> {
            try {
                saveMovie(movieName.toString(),length.toString(),genre.toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        //Wire action buttons to save, delete and reset
        save.addClickListener(e -> {
            try {
                saveMovie(movieName.toString(),length.toString(),genre.toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        delete.addClickListener(e-> deleteMovie());
        cancel.addClickListener(e-> editMovie(movie));
        setVisible(false);
    }


    void deleteMovie(){
        movieRepository.delete(movie);
        changeHandler.onChange();
    }
    void saveMovie(String name, String length, String genre) throws SQLException {
        movieRepository.save(movie);
        connector.callAddMovie(name,length,genre);
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


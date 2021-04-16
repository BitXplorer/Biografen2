package com.example.Biografen.Views.UserViews;

import com.example.Biografen.Objects.Movie;
import com.example.Biografen.Objects.MovieRepository;
import com.example.Biografen.Views.layout.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "booking", layout = MainLayout.class)
@PageTitle("Booking | Newton Cinema")
public class UserView extends VerticalLayout {

    private TextField searchField = new TextField();
    private MovieRepository movieRepo;
    private Grid<Movie> movieGrid = new Grid<>(Movie.class);

    public UserView(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
        configureSearchField();
        add(searchField);
        configureGrid();
        add(movieGrid);
        searchMovies();
    }

    private void configureGrid() {
        movieGrid.setHeight("400px");
        movieGrid.setColumns("movieName", "length", "genre");
        movieGrid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void configureSearchField() {
        searchField.setPlaceholder("Find Movie...");
        searchField.setClearButtonVisible(true);
        searchField.setValueChangeMode(ValueChangeMode.LAZY);
        searchField.addValueChangeListener(e -> searchMovies());
    }

    private void searchMovies() {
        movieGrid.setItems(movieList());
    }

    private List<Movie> movieList() {
        String searchStr = searchField.getValue();
        if ( searchStr == null || searchStr.equals("") ) {
            return movieRepo.findAll();
        }
        else {
            return movieRepo.findByNameStartsWithIgnoreCase(searchStr);
        }
    }

}

package com.example.Biografen.Views.statistics;

import com.example.Biografen.Objects.MovieRepository;
import com.example.Biografen.Views.layout.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "movie_statistics", layout = MainLayout.class)
@PageTitle("statistics movies - Newton Cinema")
public class MovieStatisticsView extends VerticalLayout {

    private final MovieRepository repo;

    public MovieStatisticsView(MovieRepository repo) {
        this.repo = repo;

        add(getMovieAmount());
    }

    private Component getMovieAmount() {
        Span amount = new Span("Number of movies: " + repo.count());
        amount.addClassName("movie-amount");
        return amount;
    }
}

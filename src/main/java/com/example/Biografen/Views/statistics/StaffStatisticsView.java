package com.example.Biografen.Views.statistics;

import com.example.Biografen.Objects.StaffRepository;
import com.example.Biografen.Views.layout.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "staff_statistics", layout = MainLayout.class)
@PageTitle("statistics staff - Newton Cinema")
public class StaffStatisticsView extends VerticalLayout {

    private final StaffRepository repo;

    public StaffStatisticsView(StaffRepository repo) {
        this.repo = repo;

        add(getStaffAmount());
    }

    private Component getStaffAmount() {
        Span amount = new Span("Number of movies: " + repo.count());
        amount.addClassName("movie-amount");
        return amount;
    }
}


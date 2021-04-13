package com.example.Biografen.Views.statistics;

import com.example.Biografen.Objects.SaloonRepository;
import com.example.Biografen.Views.layout.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "saloon_statistics", layout = MainLayout.class)
@PageTitle("statistics saloon - Newton Cinema")
public class SaloonStatisticsView extends VerticalLayout {

    private final SaloonRepository repo;

    public SaloonStatisticsView(SaloonRepository repo) {
        this.repo = repo;

        add(getSaloonAmount());
    }

    private Component getSaloonAmount() {
        Span amount = new Span("Number of saloon: " + repo.count());
        amount.addClassName("saloon-amount");
        return amount;
    }
}
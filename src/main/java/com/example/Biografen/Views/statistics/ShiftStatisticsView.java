package com.example.Biografen.Views.statistics;

import com.example.Biografen.Objects.ShiftRepository;
import com.example.Biografen.Views.layout.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "shifts_statistics", layout = MainLayout.class)
@PageTitle("statistics shifts - Newton Cinema")
public class ShiftStatisticsView extends VerticalLayout {

    private final ShiftRepository repo;

    public ShiftStatisticsView(ShiftRepository repo) {
        this.repo = repo;

        add(getShiftAmount());
    }

    private Component getShiftAmount() {
        Span amount = new Span("Number of shifts: " + repo.count());
        amount.addClassName("shift-amount");
        return amount;
    }
}


package com.example.Biografen.Views;

import com.example.Biografen.Views.layout.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Main | Newton Cinema")
public class MainView extends VerticalLayout {

    public MainView() {
        Button button = new Button("Click me to get you date and time");
        DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.setDatePlaceholder("Pick you date");

        HorizontalLayout layout = new HorizontalLayout(dateTimePicker, button);
        layout.setDefaultVerticalComponentAlignment(Alignment.END);

        add(layout);

        button.addClickListener(click -> add(new Paragraph("Clicked: " + dateTimePicker.getValue())));


    }
}

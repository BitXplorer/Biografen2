package com.example.Biografen.Views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        Button button = new Button("Click me");
        Button button2 = new Button("Click me to");
        Button button3 = new Button("Don´t click me");
        DateTimePicker dateTimePicker = new DateTimePicker("DateTimePicker");
        TextField text = new TextField("Hello");

        HorizontalLayout layout = new HorizontalLayout(dateTimePicker, button, button2, button3);

        layout.setDefaultVerticalComponentAlignment(Alignment.END);
        add(layout);
    }
}

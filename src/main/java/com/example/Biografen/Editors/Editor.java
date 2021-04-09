package com.example.Biografen.Editors;

import com.example.Biografen.Connector.Connector;
import com.example.Biografen.Objects.*;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class Editor extends VerticalLayout implements KeyNotifier {

    protected Connector connector;
    protected  MovieRepo movieRepo;
    protected StaffRepo staffRepo;
    protected ShiftRepo shiftRepo;

    protected Movie movie;
    protected Staff staff;
    protected Shift shift;


    TextField movieName, length, genre, firstname, lastName, address, city, postalCode, phone, email, socialSecurityNo,shiftName,shiftLength;

    Button save = new Button("Save", VaadinIcon.PLUS.create());
    Button cancel = new Button("Cancel", VaadinIcon.PLUS.create());
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Movie> movieBinder;
    Binder<Staff> staffBinder;
    Binder<Shift> shiftBinder;

    protected ChangeHandler changeHandler;

    public interface ChangeHandler {
        void onChange();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }
}



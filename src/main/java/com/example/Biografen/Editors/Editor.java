package com.example.Biografen.Editors;


import com.example.Biografen.Objects.*;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class Editor extends VerticalLayout implements KeyNotifier {



    protected MovieRepository movieRepository;
    protected StaffRepository staffRepository;
    protected ShiftRepository shiftRepository;
    protected SaloonRepository saloonRepository;
    protected BookingRepository bookingRepository;

    protected Movie movie;
    protected Staff staff;
    protected Shift shift;
    protected Saloon saloon;
    protected Booking booking;


    Button save = new Button("Save", VaadinIcon.PLUS.create());
    Button cancel = new Button("Cancel", VaadinIcon.PLUS.create());
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Movie> movieBinder;
    Binder<Staff> staffBinder;
    Binder<Shift> shiftBinder;
    Binder<Saloon> saloonBinder;
    Binder<Booking> bookingBinder;

    protected ChangeHandler changeHandler;

    public interface ChangeHandler {
        void onChange();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }
}



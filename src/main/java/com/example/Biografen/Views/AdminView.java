package com.example.Biografen.Views;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.NavigationHandler;
import com.vaadin.flow.router.Route;


import org.springframework.util.StringUtils;


@Route (value = "main")
public class AdminView extends VerticalLayout{

    private final Button staff, movies, salong;

    public AdminView(){
        this.staff = new Button("Staff",VaadinIcon.PLUS.create());
        this.movies = new Button("Movies",VaadinIcon.PLUS.create());
        this.salong = new Button("Salong",VaadinIcon.PLUS.create());

        //Layout
        HorizontalLayout actions = new HorizontalLayout(staff,movies,salong);
        add(actions);

        //buttonListeners
        staff.addClickListener(e -> UI.getCurrent().navigate("staff"));



    }


}

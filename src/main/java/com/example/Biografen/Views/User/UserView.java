package com.example.Biografen.Views.User;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("user")
public class UserView extends VerticalLayout {

    public UserView() {
        add(new H1("Welcom user!"));
    }
}

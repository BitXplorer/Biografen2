package com.example.Biografen.Views.layout;

import com.example.Biografen.Views.HelloWorldView;
import com.example.Biografen.Views.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;


@PWA(
        name = "NewtonCinema",
        shortName = "Cinema",
offlineResources = {
        "./styles/offline.css",
        "./images/offline.png"
}, enableInstallPrompt = false
)
@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Newton Cinema | USER");
        logo.addClassName("logo");

        Anchor logout = new Anchor("/logout",VaadinIcon.SIGN_OUT.create());

        Button staff = new Button("Staff", VaadinIcon.PLUS.create());
        Button movies = new Button("Movies",VaadinIcon.PLUS.create());
        Button salong = new Button("Salong",VaadinIcon.PLUS.create());

        //buttonListeners
        staff.addClickListener(e -> UI.getCurrent().navigate("staff"));
        movies.addClickListener(e -> UI.getCurrent().navigate("movies"));
        salong.addClickListener(e -> UI.getCurrent().navigate("salong"));

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, staff,movies,salong, logout);

        header.expand(logout);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100");
        header.addClassName("header");


        addToNavbar(header);
    }

    // Hamburger meny:en
    private void createDrawer() {
        RouterLink mainLink = new RouterLink("Main", MainView.class);
        mainLink.setHighlightCondition(HighlightConditions.sameLocation());

        // Ny länk i hamburger menyn:en
        RouterLink helloWorldLink = new RouterLink("HelloWorld", HelloWorldView.class);
         ;

        addToDrawer(new VerticalLayout(
                helloWorldLink));
    }
}

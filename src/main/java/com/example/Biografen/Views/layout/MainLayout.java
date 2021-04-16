package com.example.Biografen.Views.layout;

import com.example.Biografen.Views.AdminViews.AdminViewMovies;
import com.example.Biografen.Views.AdminViews.AdminViewSaloon;
import com.example.Biografen.Views.AdminViews.AdminViewShifts;
import com.example.Biografen.Views.AdminViews.AdminViewStaff;
import com.example.Biografen.Views.HelloWorldView;
import com.example.Biografen.Views.MainView;
import com.example.Biografen.Views.UserViews.UserView;
import com.example.Biografen.Views.statistics.MovieStatisticsView;
import com.example.Biografen.Views.statistics.SaloonStatisticsView;
import com.example.Biografen.Views.statistics.ShiftStatisticsView;
import com.example.Biografen.Views.statistics.StaffStatisticsView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
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
        name = "Newton Cinema",
        shortName = "Cinema",
offlineResources = {
        "./styles/offline.css",
        "./images/offline.png"
},
        enableInstallPrompt = false)

@JsModule("./js/os-theme-switcher.js")
@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Newton Cinema | USER");
        logo.addClassName("logo");
        Button cart = new Button("",VaadinIcon.CART.create());
        Button home = new Button("",VaadinIcon.HOME.create());

        Anchor logout = new Anchor("logout",VaadinIcon.EXIT.create());

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), home, logo, cart, logout);

        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");

        // | .navigate("") -> Bestämmer till vilken vy man skall gå till.
        home.addClickListener(e-> UI.getCurrent().navigate(""));
        cart.addClickListener(e-> UI.getCurrent().navigate("cart"));

        addToNavbar(header);
    }

    // Hamburger meny:en
    private void createDrawer() {
        RouterLink mainLink = new RouterLink("Main", MainView.class);
        mainLink.setHighlightCondition(HighlightConditions.sameLocation());

        // Ny länk i hamburger menyn:en
        RouterLink helloWorldLink = new RouterLink("HelloWorld", HelloWorldView.class);
        RouterLink adminViewMoviesLink = new RouterLink("Movies", AdminViewMovies.class);
        RouterLink adminViewShiftsLink = new RouterLink("Shifts", AdminViewShifts.class);
        RouterLink adminViewStaffLink = new RouterLink("Staff", AdminViewStaff.class);
        RouterLink adminViewSaloonLink = new RouterLink("Saloon", AdminViewSaloon.class);
        RouterLink movieStatisticsLink = new RouterLink("Movie Statistics", MovieStatisticsView.class);
        RouterLink shiftStatisticsLink = new RouterLink("Shift Statistics", ShiftStatisticsView.class);
        RouterLink staffStatisticsLink = new RouterLink("Staff Statistics", StaffStatisticsView.class);
        RouterLink salonStatisticsLink = new RouterLink("Saloon Statistics", SaloonStatisticsView.class);
        RouterLink bookingLink = new RouterLink("Booking", UserView.class);

        addToDrawer(new VerticalLayout(
                helloWorldLink,

                adminViewMoviesLink,
                adminViewShiftsLink,
                adminViewStaffLink,
                adminViewSaloonLink,

                movieStatisticsLink,
                shiftStatisticsLink,
                staffStatisticsLink,
                salonStatisticsLink,

                bookingLink
        ));
    }
}

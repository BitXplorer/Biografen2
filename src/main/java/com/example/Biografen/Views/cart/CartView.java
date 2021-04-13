package com.example.Biografen.Views.cart;

import com.example.Biografen.Views.layout.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "cart", layout = MainLayout.class)
@PageTitle("Cart | Newton Cinema")
public class CartView extends HorizontalLayout {

    public CartView() {
        addClassName("cart-view");

        Details component = new Details();
        component.setSummaryText("Shopping cart");
        component.addContent(new H2("Era bokade biljetter finner ni  här"), new Text("Ni har bokat följande biljetter..."));
        component.addThemeVariants(DetailsVariant.SMALL);
        add(component);

    }
}

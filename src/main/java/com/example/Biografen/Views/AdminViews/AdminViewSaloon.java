package com.example.Biografen.Views.AdminViews;


import com.example.Biografen.Editors.SaloonEditor;
import com.example.Biografen.Objects.Saloon;
import com.example.Biografen.Objects.SaloonRepository;
import com.example.Biografen.Views.layout.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;


@Route(value = "adminSaloon", layout = MainLayout.class)
@PageTitle("ADMIN Saloon | Newton Cinema")
public class AdminViewSaloon extends VerticalLayout {

    private final SaloonRepository repo;
    final SaloonEditor editor;
    final Grid<Saloon> grid;
    final TextField filterSaloonName;
    private final Button addSaloon, back;

    public AdminViewSaloon (SaloonRepository repo){
        this.repo = repo;
        this.grid = new Grid<>(Saloon.class);
        this.editor = new SaloonEditor(repo);
        this.filterSaloonName = new TextField();
        this.addSaloon = new Button("New Saloon", VaadinIcon.PLUS.create());
        this.back = new Button("Back",VaadinIcon.HOME.create());

        //Build layout
        HorizontalLayout actions = new HorizontalLayout(filterSaloonName, addSaloon, back);
        add(actions,grid);

        grid.setHeight("400px");
        grid.setColumns("id_saloon", "saloonName", "seats");
        grid.getColumnByKey("id_saloon").setWidth("50px").setFlexGrow(0);

        //Hook logic
        //Replace listing with filter
        filterSaloonName.setValueChangeMode(ValueChangeMode.EAGER);
        filterSaloonName.addValueChangeListener(e-> listSaloon(e.getValue()));

        //Connect selected movie to editor or hide
        grid.asSingleSelect().addValueChangeListener(e-> {editor.editSaloon(e.getValue());
        });

        //Instantiate and edit new movie
        addSaloon.addClickListener(e-> {
            editor.editSaloon(new Saloon("", ""));
        });

        //Back button | .navigate("") -> Bestämmer till vilken vy man skall gå till.
        back.addClickListener(e-> UI.getCurrent().navigate(""));

        //Listen to changes made by the editor and refresh data
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listSaloon(filterSaloonName.getValue());
        });

        //Initialize listing
        listSaloon(null);
    }
    void listSaloon(String filterText){
        if (StringUtils.isEmpty(filterText)){
            grid.setItems(repo.findAll());
        }
    }

}

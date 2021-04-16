package com.example.Biografen.Views.AdminViews;

import com.example.Biografen.Editors.ShiftEditor;
import com.example.Biografen.Objects.Shift;
import com.example.Biografen.Objects.ShiftRepository;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StringUtils;


@Route(value = "adminShifts", layout = MainLayout.class)
@PageTitle("ADMIN Shifts | Newton Cinema")
@Secured("ROLE_Admin")
public class AdminViewShifts extends VerticalLayout {

    private final ShiftRepository repo;
    final ShiftEditor editor;
    final Grid<Shift> grid;
    final TextField filterName;
    private final Button addShift, back;

    @Autowired
    public AdminViewShifts (ShiftRepository repo){
        this.repo = repo;
        this.grid = new Grid<>(Shift.class);
        this.editor = new ShiftEditor(repo);
        this.filterName = new TextField();
        this.filterName.setPlaceholder("Filter by name");
        this.addShift = new Button("New Shift", VaadinIcon.PLUS.create());
        this.back = new Button("Back", VaadinIcon.HOME.create());

        //Build layout
        HorizontalLayout actions = new HorizontalLayout(filterName, addShift, back);
        add(actions, grid, editor);
        actions.setDefaultVerticalComponentAlignment(Alignment.BASELINE);


        grid.setHeight("300px");
        grid.setColumns("id_shifts","name","length");
        //grid.addColumn(Shift::getId_shifts).setHeader("Shift ID").setWidth("50px").setFlexGrow(0);
        //grid.addColumn(Shift::getName).setHeader("Name");
        //grid.addColumn(Shift::getLength).setHeader("Length");

        //Hook logic
        //Replace listing with filter
        filterName.setValueChangeMode(ValueChangeMode.EAGER);
        filterName.addValueChangeListener(e-> listShifts(e.getValue()));

        //TODO:
        //VARFÖR ÄR e.getValue() FORTFARANDE NULL!??
        grid.asSingleSelect().addValueChangeListener(e-> {editor.editShift(e.getValue());
        });

        //Instantiate and edit new shift
        addShift.addClickListener(e -> editor.editShift(new Shift("","")));

        //back button | .navigate("") -> Bestämmer till vilken vy man skall gå till.
        back.addClickListener(e-> UI.getCurrent().navigate(""));

        //Listen to changes made by the editor and refresh data
        editor.setChangeHandler(()-> {
            editor.setVisible(false);
            listShifts(filterName.getValue());
        });
        //Initialize listing
        listShifts(null);
    }
    void listShifts(String filterText){
        if (StringUtils.isEmpty(filterText)){
            grid.setItems(repo.findAll());
        }else {
            grid.setItems(repo.findByNameStartsWithIgnoreCase(filterText));
        }
    }
}

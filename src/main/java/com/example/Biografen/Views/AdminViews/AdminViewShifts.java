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
import org.springframework.util.StringUtils;


@Route(value = "adminShifts", layout = MainLayout.class)
@PageTitle("ADMIN Shifts | Newton Cinema")
public class AdminViewShifts extends VerticalLayout {

    private final ShiftRepository repo;
    final ShiftEditor editor;
    final Grid<Shift> grid;
    final TextField filterShiftName;
    private final Button addShift, back;

    public AdminViewShifts (ShiftRepository repo){
        this.repo = repo;
        this.grid = new Grid<>(Shift.class);
        this.editor = new ShiftEditor(repo);
        this.filterShiftName = new TextField("Filter by name");
        this.addShift = new Button("New Shift", VaadinIcon.PLUS.create());
        this.back = new Button("Back", VaadinIcon.HOME.create());

        //Build layout
        HorizontalLayout actions = new HorizontalLayout(filterShiftName, addShift, back);
        add(actions, grid, editor);
        actions.setDefaultVerticalComponentAlignment(Alignment.BASELINE);


        grid.setHeight("300px");
        grid.setColumns("id_shifts","name","length");
        grid.getColumnByKey("id_shifts").setWidth("50px").setFlexGrow(0);

        //Hook logic
        //Replace listing with filter
        filterShiftName.setValueChangeMode(ValueChangeMode.EAGER);
        filterShiftName.addValueChangeListener(e-> listShifts(e.getValue()));

        //Connect selected shift to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e-> {editor.editShift(e.getValue());
        });

        //Instantiate and edit new shift
        addShift.addClickListener(e -> editor.editShift(new Shift("","")));

        //back button | .navigate("") -> Bestämmer till vilken vy man skall gå till.
        back.addClickListener(e-> UI.getCurrent().navigate(""));

        //Listen to changes made by the editor and refresh data
        editor.setChangeHandler(()-> {
            editor.setVisible(false);
            listShifts(filterShiftName.getValue());
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
package com.example.Biografen.Views;

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

@Route(value = "shifts", layout = MainLayout.class)
@PageTitle("ADMIN Shifts | Newton Cinema")
public class AdminViewShifts extends VerticalLayout {

    private final ShiftRepository repo;
    final ShiftEditor editor;
    final Grid<Shift> grid;
    final TextField filterName;
    private final Button addShift, back;

    public AdminViewShifts (ShiftRepository repo){
        this.repo = repo;
        this.grid = new Grid<>(Shift.class);
        this.editor = new ShiftEditor(repo);
        this.filterName = new TextField();
        this.addShift = new Button("New shift", VaadinIcon.PLUS.create());
        this.back = new Button("Back", VaadinIcon.PLUS.create());

        //Build layout
        HorizontalLayout actions = new HorizontalLayout(filterName,addShift,back);
        add(grid,actions);

        grid.setHeight("300px");
        grid.setColumns("idshifts","name","length");
        grid.getColumnByKey("idshifts").setWidth("50px").setFlexGrow(0);

        //Hook logic
        //Replace listing with filter
        filterName.setValueChangeMode(ValueChangeMode.EAGER);
        filterName.addValueChangeListener(e-> listShifts(e.getValue()));

        //Connect selected shift to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e-> {editor.editShift(e.getValue());
        });

        //Instantiate and edit new shift
        addShift.addClickListener(e -> editor.editShift(new Shift("","")));

        //back button
        back.addClickListener(e-> UI.getCurrent().navigate("main"));

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
        }
    }
}

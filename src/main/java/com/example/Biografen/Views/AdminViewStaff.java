package com.example.Biografen.Views;

import com.example.Biografen.Editors.StaffEditor;
import com.example.Biografen.Objects.Staff;
import com.example.Biografen.Objects.StaffRepo;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;

@Route(value = "staff")
public class AdminViewStaff  extends VerticalLayout {

    private final StaffRepo Repo;
    final StaffEditor staffEditor;
    final Grid<Staff> Grid;
    final TextField filterFirstNAme, filterLastNAme;
    private final Button addStaff, back;

    public AdminViewStaff(StaffRepo Repo){
        this.Repo = Repo;
        this.Grid = new Grid<>(Staff.class);
        this.staffEditor = new StaffEditor(Repo);
        this.filterFirstNAme = new TextField();
        this.filterLastNAme = new TextField();
        this.addStaff = new Button("New staff", VaadinIcon.PLUS.create());
        this.back = new Button("Back", VaadinIcon.PLUS.create());

        //Build layout
        HorizontalLayout actions = new HorizontalLayout(filterFirstNAme,filterLastNAme,addStaff, back);
        add(actions, Grid);

        Grid.setHeight("300px");
        Grid.setColumns("id","firstName", "lastName","address","city","postalCode","phone","email","socialSecurityNo");
        Grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);



        //Hook logic

        //Replace listing with filter
        filterFirstNAme.setValueChangeMode(ValueChangeMode.EAGER);
        filterFirstNAme.addValueChangeListener(e -> listStaff(e.getValue(),1));
        filterLastNAme.setValueChangeMode(ValueChangeMode.EAGER);
        filterLastNAme.addValueChangeListener(e -> listStaff(e.getValue(),2));

        //Connect selected staff to editor or hide if none is selected
        Grid.asSingleSelect().addValueChangeListener(e -> { staffEditor.editStaff(e.getValue());
        });

        //instantiate end edit new staff
        addStaff.addClickListener (e -> staffEditor.editStaff(new Staff("","","","","",
                "","","")));

        //back button
        back.addClickListener(e -> UI.getCurrent().navigate("main"));

        //Listen changes made by the editor, refresh data
        staffEditor.setChangeHandler(() -> {
        staffEditor.setVisible(false);
        listStaff(filterFirstNAme.getValue(),1);
        listStaff(filterLastNAme.getValue(),2);
        });

        //Initialize listing
        listStaff(null,0);
    }
    void listStaff(String filterText, int choice){
        if (StringUtils.isEmpty(filterText)){
            Grid.setItems((Staff) Repo.findAll());
        }else {
            if (choice == 1){
                Grid.setItems(Repo.findByFirstNameStartsWithIgnoreCase(filterText));
            }else if (choice == 2){
                Grid.setItems(Repo.findByLastNameStartsWithIgnoreCase(filterText));
            }
        }
    }

}

package com.example.Biografen.Views.AdminViews;

import com.example.Biografen.Editors.StaffEditor;
import com.example.Biografen.Objects.Staff;
import com.example.Biografen.Objects.StaffRepository;
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

@Route(value = "adminStaff", layout = MainLayout.class)
@PageTitle("ADMIN Staff | Newton Cinema")
@Secured("ROLE_Admin")
public class AdminViewStaff  extends VerticalLayout {

    private final StaffRepository repo;
    final StaffEditor editor;
    final Grid<Staff> grid;
    final TextField filterFirstName, filterLastName;
    private final Button addStaff, back;

    @Autowired
    public AdminViewStaff(StaffRepository repo){
        this.repo = repo;
        this.grid = new Grid<>(Staff.class);
        this.editor = new StaffEditor(repo);
        this.filterFirstName = new TextField();
        this.filterFirstName.setPlaceholder("Filter by first name");
        this.filterLastName = new TextField();
        this.filterLastName.setPlaceholder("Filter by last name");
        this.addStaff = new Button("New staff", VaadinIcon.PLUS.create());
        this.back = new Button("Back", VaadinIcon.HOME.create());

        //Build layout
        HorizontalLayout actions = new HorizontalLayout(filterFirstName, filterLastName, addStaff, back);
        add(actions, grid, editor);
        actions.setDefaultVerticalComponentAlignment(Alignment.BASELINE);


        grid.setHeight("400px");
        grid.setColumns("id_staff", "firstName", "lastName", "address", "city", "postalCode", "phone", "email", "shift", "socialSecurityNo");
        grid.getColumnByKey("id_staff").setWidth("50px").setFlexGrow(0);


        //Hook logic
        //Replace listing with filter
        filterFirstName.setValueChangeMode(ValueChangeMode.EAGER);
        filterFirstName.addValueChangeListener(e -> listStaff(e.getValue(),1));
        filterLastName.setValueChangeMode(ValueChangeMode.EAGER);
        filterLastName.addValueChangeListener(e -> listStaff(e.getValue(),2));

        //Connect selected staff to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> { editor.editStaff(e.getValue());
        });

        //instantiate end edit new staff
        addStaff.addClickListener (e -> editor.
                editStaff(new Staff("","","","","",
                "","",null, "")));

        //back button | .navigate("") -> Best??mmer till vilken vy man skall g?? till.
        back.addClickListener(e -> UI.getCurrent().navigate(""));

        //Listen changes made by the editor, refresh data
        editor.setChangeHandler(() -> {
        editor.setVisible(false);
        listStaff(filterFirstName.getValue(),1);
        listStaff(filterLastName.getValue(),2);
        });

        //Initialize listing
        listStaff(null,2);
    }
    void listStaff(String filterText, int choice){
        if (StringUtils.isEmpty(filterText)){
            grid.setItems(repo.findAll());
        }else {
            if (choice == 1){
                grid.setItems(repo.findByFirstNameStartsWithIgnoreCase(filterText));
            }else if (choice == 2){
                grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
            }
        }
    }

}

package com.example.Biografen.Editors;

import com.example.Biografen.Objects.Staff;
import com.example.Biografen.Objects.StaffRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;

import java.sql.SQLException;

public class StaffEditor extends Editor{

    TextField firstName,
            lastName,
            address,
            city,
            postalCode,
            phone,
            email,
            socialSecurityNo;

    public StaffEditor (StaffRepository repo){
        this.staffRepository = repo;
        this.staffBinder = new Binder<>(Staff.class);
        firstName = new TextField("First Name");
        lastName = new TextField("Last Name");
        address = new TextField("Address");
        city = new TextField("City");
        postalCode = new TextField("PostalCode");
        phone = new TextField("Phone");
        email = new TextField("Email");
        socialSecurityNo = new TextField("SocialSecurityNo");

        add(firstName, lastName,address,city, postalCode,phone,email, socialSecurityNo, actions);

        staffBinder.bindInstanceFields(this);
        setSpacing(true);

        save.getElement().getThemeList().add("Primary");
        delete.getElement().getThemeList().add("error");

        //Listener som trycker på "save" om man trycker på enter
        addKeyPressListener(Key.ENTER, e -> saveCatcher());

        //Wire action buttons to save, delete and cancel
        save.addClickListener(e -> saveCatcher());
        delete.addClickListener(e-> deleteStaff());
        cancel.addClickListener(e -> editStaff(staff));
        setVisible(false);
    }

    void saveCatcher(){
        try{
            staffBinder.writeBean(staff);
            saveStaff(staff);
        } catch (SQLException | ValidationException throwables){
            throwables.printStackTrace();
        }
    }

    void deleteStaff(){
        staffRepository.delete(staff);
        changeHandler.onChange();
    }
    void saveStaff (Staff staff) throws SQLException {
        staffRepository.save(staff);
        changeHandler.onChange();
    }
    public final void editStaff(Staff s){
        if (s == null){
            setVisible(false);
            return;
        }
        final boolean persisted = s.getId_staff() != null;
        if (persisted){
            staff = staffRepository.findById(s.getId_staff()).get();
        }else {
            staff = s;
        }
        cancel.setVisible(persisted);

        staffBinder.setBean(staff);

        setVisible(true);

        firstName.focus();
    }
}

package com.example.Biografen.Editors;

import com.example.Biografen.Objects.Staff;
import com.example.Biografen.Objects.StaffRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.sql.SQLException;

public class StaffEditor extends Editor{

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
        addKeyPressListener(Key.ENTER, e -> {
            try {
                saveStaff(firstName.toString(), lastName.toString(),address.toString(),
                        city.toString(), postalCode.toString(),phone.toString(),email.toString(), socialSecurityNo.toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        //Wire action buttons to save, delete and cancel
        save.addClickListener(e -> {
            try {
                saveStaff(firstName.toString(), lastName.toString(),address.toString(),
                        city.toString(), postalCode.toString(),phone.toString(),email.toString(), socialSecurityNo.toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        delete.addClickListener(e-> deleteStaff());
        cancel.addClickListener(e -> editStaff(staff));
        setVisible(false);
    }



    void deleteStaff(){
        staffRepository.delete(staff);
        changeHandler.onChange();
    }
    void saveStaff (String firstName, String lastName, String address,String city,String postalCode,String phone,
                    String email,String socialSecurityNo) throws SQLException {
        staffRepository.save(staff);
        connector.callAddStaff(firstName, lastName, address, city, postalCode, phone, email, socialSecurityNo);
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

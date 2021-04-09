package com.example.Biografen.Editors;

import com.example.Biografen.Objects.Staff;
import com.example.Biografen.Objects.StaffRepo;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.sql.SQLException;

public class StaffEditor extends Editor{

    public StaffEditor (StaffRepo repo){
        this.staffRepo = repo;
        this.staffBinder = new Binder<>(Staff.class);
        firstname = new TextField("firstName");
        lastName = new TextField("lastName");
        address = new TextField("address");
        city = new TextField("city");
        postalCode = new TextField("postalCode");
        phone = new TextField("phone");
        email = new TextField("email");
        socialSecurityNo = new TextField("socialSecurityNo");

        add(firstname,lastName,address,city,postalCode,phone,email,socialSecurityNo,actions);

        staffBinder.bindInstanceFields(this);
        setSpacing(true);

        save.getElement().getThemeList().add("Primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> {
            try {
                saveStaff(firstname.toString(),lastName.toString(),address.toString(),
                        city.toString(),postalCode.toString(),phone.toString(),email.toString(),socialSecurityNo.toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        //Wire action buttons to save, delete and cancel
        save.addClickListener(e -> {
            try {
                saveStaff(firstname.toString(),lastName.toString(),address.toString(),
                        city.toString(),postalCode.toString(),phone.toString(),email.toString(),socialSecurityNo.toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        delete.addClickListener(e-> deleteStaff());
        cancel.addClickListener(e -> editStaff(staff));
        setVisible(false);
    }



    void deleteStaff(){
        staffRepo.delete(staff);
        changeHandler.onChange();
    }
    void saveStaff (String firstname, String lastName, String address,String city,String postalCode,String phone,
                    String email,String socialSecurityNo) throws SQLException {
        staffRepo.save(staff);
        connector.callAddStaff(firstname, lastName, address, city, postalCode, phone, email, socialSecurityNo);
        changeHandler.onChange();
    }
    public final void editStaff(Staff s){
        if (s == null){
            setVisible(false);
            return;
        }
        final boolean persisted = s.getId() != null;
        if (persisted){
            staff = staffRepo.findById(staff.getId()).get();
        }else {
            staff = s;
        }
        cancel.setVisible(persisted);

        staffBinder.setBean(staff);

        setVisible(true);

        firstname.focus();
    }
}

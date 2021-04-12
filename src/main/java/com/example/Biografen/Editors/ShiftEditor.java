package com.example.Biografen.Editors;

import com.example.Biografen.Objects.Shift;
import com.example.Biografen.Objects.ShiftRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.sql.SQLException;

public class ShiftEditor extends Editor{

    public ShiftEditor (ShiftRepository repo){
        this.shiftRepository = repo;
        this.shiftBinder = new Binder<>(Shift.class);
        shiftName = new TextField("shiftName");
        shiftLength = new TextField("shiftLength");

        add(shiftName,shiftLength, actions);

        shiftBinder.bindInstanceFields(this);
        setSpacing(true);

        save.getElement().getThemeList().add("Primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> {
           try {
               saveShift(shiftName.toString(), shiftLength.toString());
           } catch (SQLException throwables){
               throwables.printStackTrace();
           }
        });

        save.addClickListener(e -> {
            try {
                saveShift(shiftName.toString(), shiftLength.toString());
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }
        });
        delete.addClickListener(e-> deleteShift());
        cancel.addClickListener(e-> editShift(shift));
        setVisible(false);
    }

    void deleteShift(){
        shiftRepository.delete(shift);
        changeHandler.onChange();
    }
    void saveShift(String name, String length) throws SQLException {
        shiftRepository.save(shift);
        connector.callAddShift(name, length);
        changeHandler.onChange();
    }
    public final void editShift(Shift s){
        if (s== null){
            setVisible(false);
            return;
        }
        final boolean persisted = s.getId() != null;
        if (persisted){
            shift = shiftRepository.findById(shift.getId()).get();
        }else {
            shift = s;
        }
        cancel.setVisible(persisted);

        shiftBinder.setBean(shift);
        setVisible(true);
        shiftName.focus();
    }
}

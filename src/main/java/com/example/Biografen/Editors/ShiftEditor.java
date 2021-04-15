package com.example.Biografen.Editors;

import com.example.Biografen.Objects.Shift;
import com.example.Biografen.Objects.ShiftRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public class ShiftEditor extends Editor{

    TextField name, length;

    @Autowired
    public ShiftEditor (ShiftRepository repo){
        this.shiftRepository = repo;
        this.shiftBinder = new Binder<>(Shift.class);
        name = new TextField("Shift Name");
        length = new TextField("Shift Length");

        add(name,length, actions);


        //shiftBinder.bindInstanceFields(shift);
        shiftBinder.forField(name).bind(Shift::getName,Shift::setName);
        shiftBinder.forField(length).bind(Shift::getLength, Shift::setLength);
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> saveCatcher());

        save.addClickListener(e -> saveCatcher());
        delete.addClickListener(e-> deleteShift());
        cancel.addClickListener(e-> editShift(shift));
        setVisible(false);
    }

    void saveCatcher(){
        try{
            shiftBinder.writeBean(shift);
            saveShift(shift);
        } catch (SQLException | ValidationException throwables) {
            throwables.printStackTrace();
        }
    }

    void deleteShift(){
        shiftRepository.delete(shift);
        changeHandler.onChange();
    }
    void saveShift(Shift shift) throws SQLException {
        shiftRepository.save(shift);
        changeHandler.onChange();
    }
    public final void editShift(Shift s){
        if (s== null){
            setVisible(false);
            return;
        }
        final boolean persisted = s.getId_shifts() != null;
        if (persisted){
            shift = shiftRepository.findById(shift.getId_shifts()).get();
        }else {
            shift = s;
        }
        cancel.setVisible(persisted);

        shiftBinder.setBean(shift);

        setVisible(true);

        name.focus();
    }
}

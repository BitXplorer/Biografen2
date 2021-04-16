package com.example.Biografen.Editors;

import com.example.Biografen.Objects.Saloon;
import com.example.Biografen.Objects.SaloonRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;

import java.sql.SQLException;

public class SaloonEditor extends Editor {

    TextField saloonName, seats;

    public SaloonEditor (SaloonRepository repo){
        this.saloonRepository = repo;
        this.saloonBinder = new Binder<>(Saloon.class);
        saloonName = new TextField("Saloon name");
        seats = new TextField("Seats");


        //Lägger till knapparna
        add(saloonName, seats, actions);

        saloonBinder.bindInstanceFields(this);
        setSpacing(true);

        save.getElement().getThemeList().add("Primary");
        delete.getElement().getThemeList().add("error");

        //Listener som trycker på "save" om man trycker på enter
        addKeyPressListener(Key.ENTER, e -> saveCatcher());

        //Wire action buttons to save, delete and reset
        save.addClickListener(e -> saveCatcher());
        delete.addClickListener(e-> deleteSaloon());
        cancel.addClickListener(e-> editSaloon(saloon));
        setVisible(false);
    }

    void saveCatcher(){
        try{
            saloonBinder.writeBean(saloon);
            saveSaloon(saloon);
        } catch (SQLException | ValidationException throwables){
            throwables.printStackTrace();
        }
    }

    void deleteSaloon(){
        saloonRepository.delete(saloon);
        changeHandler.onChange();
    }

    void saveSaloon(Saloon saloon) throws SQLException {
        saloonRepository.save(saloon);
        changeHandler.onChange();
    }
    public final void editSaloon(Saloon s){
        if (s == null){
            setVisible(false);
            return;
        }
        final boolean persisted = s.getId_saloon() != null;
        if (persisted){
            saloon = saloonRepository.findById(s.getId_saloon()).get();
        }else {
            saloon = s;
        }
        cancel.setVisible(persisted);

        saloonBinder.setBean(saloon);

        setVisible(true);

        saloonName.focus();
    }
    
}

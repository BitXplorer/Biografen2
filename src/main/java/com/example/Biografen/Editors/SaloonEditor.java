package com.example.Biografen.Editors;

import com.example.Biografen.Objects.Saloon;
import com.example.Biografen.Objects.SaloonRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.sql.SQLException;

public class SaloonEditor extends Editor {

    public SaloonEditor (SaloonRepository repo){
        this.saloonRepository = repo;
        this.saloonBinder = new Binder<>(Saloon.class);
        saloonName = new TextField("Saloon name");
        saloonSeats = new TextField("Seats");


        //Lägger till knapparna
        add(saloonName, saloonSeats, actions);


        //TODO  - Kolla Kolla saloonBinder
        saloonBinder.bindInstanceFields(this);
        setSpacing(true);

        save.getElement().getThemeList().add("Primary");
        delete.getElement().getThemeList().add("error");

        //Listener som trycker på "save" om man trycker på enter
        addKeyPressListener(Key.ENTER, e -> {
            try {
                saveSaloon(saloonName.toString(),saloonSeats.toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        //Wire action buttons to save, delete and reset
        save.addClickListener(e -> {
            try {
                saveSaloon(saloonName.toString(),saloonSeats.toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        delete.addClickListener(e-> deleteSaloon());
        cancel.addClickListener(e-> editSaloon(saloon));
        setVisible(false);
    }


    void deleteSaloon(){
        saloonRepository.delete(saloon);
        changeHandler.onChange();
    }

    // TODO - Fixa procedure för addSaloon i databasen. Ligger en nu men den strular på [MoviesidMovies]
    void saveSaloon(String name, String seats) throws SQLException {
        saloonRepository.save(saloon);
        connector.callAddSaloon(name,seats);
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

        movieName.focus();
    }
    
}

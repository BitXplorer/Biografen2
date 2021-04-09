package com.example.Biografen.Views;


import com.example.Biografen.Editors.MovieEditor;
import com.example.Biografen.Objects.Movie;
import com.example.Biografen.Objects.MovieRepo;
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


@Route(value = "adminMovies")
public class AdminViewMovies  extends VerticalLayout {

    private final MovieRepo repo;
    final MovieEditor editor;
    final Grid<Movie> grid;
    final TextField filterMovieName;
    private final Button addMovie, back;

    public AdminViewMovies (MovieRepo repo){
        this.repo = repo;
        this.grid = new Grid<>(Movie.class);
        this.editor = new MovieEditor(repo);
        this.filterMovieName = new TextField();
        this.addMovie = new Button("New Movie", VaadinIcon.PLUS.create());
        this.back = new Button("Back",VaadinIcon.PLUS.create());

        //Build layout
        HorizontalLayout actions = new HorizontalLayout(filterMovieName,addMovie,back);
        add(actions,grid);

        grid.setHeight("300px");
        grid.setColumns("id","movieName", "movieLength");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        //Hook logic
        //Replace listing with filter
        filterMovieName.setValueChangeMode(ValueChangeMode.EAGER);
        filterMovieName.addValueChangeListener(e-> listMovies(e.getValue()));

        //Connect selected movie to editor or hide
        grid.asSingleSelect().addValueChangeListener(e-> {editor.editMovie(e.getValue());
        });

        //Instantiate and edit new movie
        addMovie.addClickListener(e-> editor.editMovie(new Movie("",0)));

        //Back button
        back.addClickListener(e-> UI.getCurrent().navigate("main"));

        //Listen to changes made by the editor and refresh data
        editor.setChangeHandler(()->{
            editor.setVisible(false);
            listMovies(filterMovieName.getValue());
        });

        //Initialize listing
        listMovies(null);
    }
    void listMovies(String filterText){
        if (StringUtils.isEmpty(filterText)){
            grid.setItems((Movie) repo.findAll());
        }
    }

}

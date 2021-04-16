package com.example.Biografen.Views.AdminViews;


import com.example.Biografen.Editors.MovieEditor;
import com.example.Biografen.Objects.Movie;
import com.example.Biografen.Objects.MovieRepository;
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


@Route(value = "adminMovies", layout = MainLayout.class)
@PageTitle("ADMIN Movies | Newton Cinema")
@Secured("ROLE_Admin")
public class AdminViewMovies  extends VerticalLayout {

    private final MovieRepository repo;
    final MovieEditor editor;
    final Grid<Movie> grid;
    final TextField filterMovieName;
    private final Button addMovie, back;

    @Autowired
    public AdminViewMovies (MovieRepository repo){
        this.repo = repo;
        this.grid = new Grid<>(Movie.class);
        this.editor = new MovieEditor(repo);
        this.filterMovieName = new TextField();
        this.filterMovieName.setPlaceholder("Filter by name");
        this.addMovie = new Button("New Movie", VaadinIcon.PLUS.create());
        this.back = new Button("Back",VaadinIcon.HOME.create());

        //Build layout
        HorizontalLayout actions = new HorizontalLayout(filterMovieName, addMovie, back);
        add(actions,grid, editor);
        actions.setDefaultVerticalComponentAlignment(Alignment.BASELINE);


        grid.setHeight("400px");
        grid.setColumns("id_movies", "movieName", "length");
        grid.getColumnByKey("id_movies").setWidth("50px").setFlexGrow(0);

        //Hook logic
        //Replace listing with filter
        filterMovieName.setValueChangeMode(ValueChangeMode.EAGER);
        filterMovieName.addValueChangeListener(e-> listMovies(e.getValue()));

        //Connect selected movie to editor or hide
        grid.asSingleSelect().addValueChangeListener(e-> {editor.editMovie(e.getValue());
        });

        //TODO - Kolla mot AdminViewStaff
        //Instantiate and edit new movie
        addMovie.addClickListener(e-> {
            editor.editMovie(new Movie("", "", ""));
        });

        //Back button | .navigate("") -> Bestämmer till vilken vy man skall gå till.
        back.addClickListener(e-> UI.getCurrent().navigate(""));

        //Listen to changes made by the editor and refresh data
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listMovies(filterMovieName.getValue());
        });

        //Initialize listing
        listMovies(null);
    }
    void listMovies(String filterText){
        if (StringUtils.isEmpty(filterText)){
            grid.setItems(repo.findAll());
        }else {
            grid.setItems(repo.findByNameStartsWithIgnoreCase(filterText));
        }
    }

}

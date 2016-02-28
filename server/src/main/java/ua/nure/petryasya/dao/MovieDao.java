package ua.nure.petryasya.dao;

import org.springframework.stereotype.Repository;
import ua.nure.petryasya.model.Movie;

import java.util.List;

public interface MovieDao {

    List<Movie> getAll();

    Movie get(int id);

    void delete(int id);

    void insert(Movie movie);

    void update(Movie movie);
}

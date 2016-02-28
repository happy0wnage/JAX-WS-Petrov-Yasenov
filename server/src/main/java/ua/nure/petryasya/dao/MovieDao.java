package ua.nure.petryasya.dao;

import ua.nure.petryasya.model.Movie;

import java.util.List;

public interface MovieDao {

    List<Movie> getAll();

    List<Movie> getByUser(int idUser);

    Movie get(int id);

    void delete(int id);

    void insert(Movie movie);

    void update(Movie movie);
}

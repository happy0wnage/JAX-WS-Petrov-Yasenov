package ua.nure.petryasya.dao;

import ua.nure.petryasya.model.Film;

import java.util.List;

public interface MovieDao {

    List<Film> getAll();

    Film get(int id);

    void delete(int id);

    void insert(Film movie);

    void update(Film movie);
}

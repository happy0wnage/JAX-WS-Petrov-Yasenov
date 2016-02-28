package ua.nure.petryasya.dao;

import ua.nure.petryasya.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAll();

    User get(int id);

    void delete(int id);

    void insert(User user);

    void update(User user);

    void addMovie(int idMovie, int idUser);

    void deleteMovie(int idMovie, int idUser);
}

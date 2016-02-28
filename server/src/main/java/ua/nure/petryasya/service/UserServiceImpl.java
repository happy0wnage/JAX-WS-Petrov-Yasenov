package ua.nure.petryasya.service;

import ua.nure.petryasya.dao.UserDAO;
import ua.nure.petryasya.dao.jdbcImpl.UserDAOJDBCImpl;
import ua.nure.petryasya.model.User;
import ua.nure.petryasya.model.Users;

import javax.jws.WebService;

@WebService(endpointInterface = "ua.nure.petryasya.service.UserService")
public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOJDBCImpl();

    @Override
    public Users getAll() {
        Users users = new Users();
        users.setUserList(userDAO.getAll());
        return users;
    }

    @Override
    public User get(int id) {
        return userDAO.get(id);
    }

    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }

    @Override
    public void insert(User user) {
        userDAO.insert(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void addMovie(int idMovie, int idUser) {
        userDAO.addMovie(idMovie, idUser);
    }

    @Override
    public void deleteMovie(int idMovie, int idUser) {
        userDAO.deleteMovie(idMovie, idUser);
    }
}

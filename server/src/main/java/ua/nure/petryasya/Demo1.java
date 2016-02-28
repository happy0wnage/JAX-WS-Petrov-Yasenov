package ua.nure.petryasya;

import ua.nure.petryasya.dao.MovieDao;
import ua.nure.petryasya.dao.UserDAO;
import ua.nure.petryasya.dao.jdbcImpl.MovieDaoJDBCImpl;
import ua.nure.petryasya.dao.jdbcImpl.UserDAOJDBCImpl;
import ua.nure.petryasya.exception.DBLayerException;
import ua.nure.petryasya.model.Movie;

public class Demo1 {

    public static void main(String[] args) {
        MovieDao movieDao = new MovieDaoJDBCImpl();
        UserDAO userDAO = new UserDAOJDBCImpl();
        System.out.println(userDAO.getAll());

//        movieDao.get(3);
        userDAO.addMovie(1,2);

    }
}

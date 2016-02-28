package ua.nure.petryasya;

import ua.nure.petryasya.dao.MovieDao;
import ua.nure.petryasya.dao.MovieDaoImpl;
import ua.nure.petryasya.exception.DBLayerException;

public class Demo1 {

    public static void main(String[] args) {
        MovieDao movieDao = new MovieDaoImpl();

        System.out.println(movieDao.getAll());

        try {
            movieDao.delete(2);
        } catch (DBLayerException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

package ua.nure.petryasya.service;

import ua.nure.petryasya.dao.MovieDao;
import ua.nure.petryasya.dao.MovieDaoImpl;
import ua.nure.petryasya.model.Movie;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "ua.nure.petryasya.service.MovieService")
public class MovieServiceImpl implements MovieService {

    private MovieDao movieDao = new MovieDaoImpl();

    @Override
    public ArrayList<Movie> getMovies() {
        return (ArrayList<Movie>) movieDao.getAll();
    }

    @Override
    public Movie getMovie(int id) {
        return movieDao.get(id);
    }

    @Override
    public void addMovie(Movie movie) {
        movieDao.insert(movie);
    }
}

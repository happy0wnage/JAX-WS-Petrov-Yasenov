package ua.nure.petryasya.service;

import ua.nure.petryasya.dao.MovieDao;
import ua.nure.petryasya.dao.MovieDaoImpl;
import ua.nure.petryasya.model.Film;
import ua.nure.petryasya.model.Movies;

import javax.jws.WebService;

@WebService(endpointInterface = "ua.nure.petryasya.service.MovieService")
public class MovieServiceImpl implements MovieService {

    private MovieDao movieDao = new MovieDaoImpl();

    @Override
    public Movies getMovies() {
        Movies movies = new Movies();
        movies.setMovieList(movieDao.getAll());
        return movies;
    }

    @Override
    public Film getMovie(int id) {
        return movieDao.get(id);
    }

    @Override
    public void addMovie(Film movie) {
        movieDao.insert(movie);
    }

    @Override
    public void deleteMovie(int id) {
        movieDao.delete(id);
    }


}

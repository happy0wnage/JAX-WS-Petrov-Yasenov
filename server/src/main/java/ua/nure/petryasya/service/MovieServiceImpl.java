package ua.nure.petryasya.service;

import ua.nure.petryasya.dao.MovieDao;
import ua.nure.petryasya.dao.jdbcImpl.MovieDaoJDBCImpl;
import ua.nure.petryasya.model.Movie;
import ua.nure.petryasya.model.Movies;

import javax.jws.WebService;

@WebService(endpointInterface = "ua.nure.petryasya.service.MovieService")
public class MovieServiceImpl implements MovieService {

    private MovieDao movieDao = new MovieDaoJDBCImpl();

    @Override
    public Movies getMovies() {
        Movies movies = new Movies();
        movies.setMovieList(movieDao.getAll());
        return movies;
    }

    @Override
    public Movie getMovie(int id) {
        return movieDao.get(id);
    }

    @Override
    public void addMovie(Movie movie) {
        movieDao.insert(movie);
    }

    @Override
    public void deleteMovie(int id) {
        movieDao.delete(id);
    }

    @Override
    public Movies getByUser(int idUser) {
        Movies movies = new Movies();
        movies.setMovieList(movieDao.getByUser(idUser));
        return movies;
    }


}

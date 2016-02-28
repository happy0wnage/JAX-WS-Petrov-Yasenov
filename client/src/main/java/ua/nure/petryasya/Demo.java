package ua.nure.petryasya;

import ua.nure.petryasya.client.ArrayList;
import ua.nure.petryasya.client.Movie;
import ua.nure.petryasya.client.MovieService;
import ua.nure.petryasya.client.MovieServiceImplService;

import java.util.List;

public class Demo {

    public static void main(String[] args) {
        MovieServiceImplService movieService = new MovieServiceImplService();
        MovieService movie = movieService.getMovieServiceImplPort();

        ArrayList movies = movie.getMovies();
        List<Movie> movieList = (List<Movie>) movie.getMovies();
    }
}

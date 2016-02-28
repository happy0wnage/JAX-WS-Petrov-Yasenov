package ua.nure.petryasya.dao;

import ua.nure.petryasya.model.Movie;
import ua.nure.petryasya.service.MovieServiceImpl;

public class Demo {

    public static void main(String[] args) {
        MovieServiceImpl service = new MovieServiceImpl();
        for (Movie movie : service.getMovies()) {
            System.out.println(movie);
        }
    }
}

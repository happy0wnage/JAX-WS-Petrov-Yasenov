package ua.nure.petryasya.model;

import java.util.ArrayList;
import java.util.List;

public class Movies {

    private List<Movie> movieList = new ArrayList<>();

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "movieList=" + movieList +
                '}';
    }
}

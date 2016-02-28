package ua.nure.petryasya.model;

import java.util.ArrayList;
import java.util.List;

public class Movies {

    private List<Film> movieList = new ArrayList<>();

    public List<Film> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Film> movieList) {
        this.movieList = movieList;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "movieList=" + movieList +
                '}';
    }
}

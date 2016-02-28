package ua.nure.petryasya.service;

import ua.nure.petryasya.model.Movie;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebService
@SOAPBinding(style = Style.RPC)
public interface MovieService {

	@WebMethod
    ArrayList<Movie> getMovies();

	@WebMethod
    Movie getMovie(int id);

    @WebMethod
    void addMovie(Movie movie);

}

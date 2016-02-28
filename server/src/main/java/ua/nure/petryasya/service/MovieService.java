package ua.nure.petryasya.service;

import ua.nure.petryasya.model.Movie;
import ua.nure.petryasya.model.Movies;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import java.util.List;

@WebService
@SOAPBinding(style = Style.RPC)
public interface MovieService {

	@WebMethod
    Movies getMovies();

	@WebMethod
    Movie getMovie(int id);

    @WebMethod
    void addMovie(Movie movie);

    @WebMethod
    void deleteMovie(int id);

    @WebMethod
    Movies getByUser(int idUser);

}

package ua.nure.petryasya.service;

import ua.nure.petryasya.model.Film;
import ua.nure.petryasya.model.Movies;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface MovieService {

	@WebMethod
    Movies getMovies();

	@WebMethod
    Film getMovie(int id);

    @WebMethod
    void addMovie(Film movie);

    @WebMethod
    void deleteMovie(int id);

}

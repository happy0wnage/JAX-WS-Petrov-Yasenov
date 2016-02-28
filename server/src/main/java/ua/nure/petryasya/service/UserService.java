package ua.nure.petryasya.service;

import ua.nure.petryasya.model.User;
import ua.nure.petryasya.model.Users;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface UserService {

    @WebMethod
    Users getAll();

    @WebMethod
    User get(int id);

    @WebMethod
    void delete(int id);

    @WebMethod
    void insert(User user);

    @WebMethod
    void update(User user);

    @WebMethod
    void addMovie(int idMovie, int idUser);

    @WebMethod
    void deleteMovie(int idMovie, int idUser);


}

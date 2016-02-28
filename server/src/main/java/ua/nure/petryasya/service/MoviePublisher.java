package ua.nure.petryasya.service;

import javax.xml.ws.Endpoint;

public class MoviePublisher {

    public static void main(String[] args) {
        Endpoint movie = Endpoint.create(new MovieServiceImpl());
        movie.publish("http://localhost:9090/movieService");

        Endpoint user = Endpoint.create(new UserServiceImpl());
        user.publish("http://localhost:9090/userService");


    }

}

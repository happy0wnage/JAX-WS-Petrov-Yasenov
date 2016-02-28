package ua.nure.petryasya.service;

import javax.xml.ws.Endpoint;

public class MoviePublisher {

	public static void main(String[] args) {
		Endpoint ep = Endpoint.create(new MovieServiceImpl());
		ep.publish("http://localhost:9090/movieServer");
	}

}

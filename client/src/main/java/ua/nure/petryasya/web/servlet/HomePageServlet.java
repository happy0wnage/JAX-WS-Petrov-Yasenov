package ua.nure.petryasya.web.servlet;

import ua.nure.petryasya.client.MovieService;
import ua.nure.petryasya.client.MovieServiceImplService;
import ua.nure.petryasya.client.Movies;
import ua.nure.petryasya.web.constants.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "index", urlPatterns = "/index.html")
public class HomePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MovieServiceImplService movieService = new MovieServiceImplService();
        MovieService movie = movieService.getMovieServiceImplPort();

        Movies movies = movie.getMovies();
        req.setAttribute("movies", movies);
        req.getRequestDispatcher(Page.INDEX).forward(req, resp);
    }
}

package ua.nure.petryasya.web.servlet;

import ua.nure.petryasya.client.MovieService;
import ua.nure.petryasya.client.MovieServiceImplService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteMovie", urlPatterns = "/deleteMovie")
public class DeleteMoviesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idMovie = Integer.parseInt(req.getParameter("id_movie"));
        MovieServiceImplService movieService = new MovieServiceImplService();
        MovieService movie = movieService.getMovieServiceImplPort();
        movie.deleteMovie(idMovie);
        resp.sendRedirect(req.getHeader("referer"));
    }
}

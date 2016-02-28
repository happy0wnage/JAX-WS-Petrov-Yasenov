package ua.nure.petryasya.web.servlet;

import ua.nure.petryasya.constants.Constatnts;
import ua.nure.petryasya.core.movie.MovieService;
import ua.nure.petryasya.core.movie.MovieServiceImplService;
import ua.nure.petryasya.core.user.User;
import ua.nure.petryasya.web.constants.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "mymovies", urlPatterns = "/mymovies")
public class MyMoviesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        MovieServiceImplService movieService = new MovieServiceImplService();
        MovieService movie = movieService.getMovieServiceImplPort();

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(Constatnts.SessionParap.LOGGED_USER);

        req.setAttribute(Constatnts.ReqParap.MOVIES, movie.getByUser(user.getId()));
        req.getRequestDispatcher(Page.MOVIES).forward(req, resp);
    }
}

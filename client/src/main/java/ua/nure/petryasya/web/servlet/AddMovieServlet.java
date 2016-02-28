package ua.nure.petryasya.web.servlet;

import ua.nure.petryasya.constants.Constatnts;
import ua.nure.petryasya.core.user.Movie;
import ua.nure.petryasya.core.user.User;
import ua.nure.petryasya.core.user.UserService;
import ua.nure.petryasya.core.user.UserServiceImplService;
import ua.nure.petryasya.web.constants.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "addMovie", urlPatterns = "/addMovie")
public class AddMovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int idMovie = Integer.parseInt(req.getParameter(Constatnts.ReqParap.ID_MOVIE));

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(Constatnts.SessionParap.LOGGED_USER);

        UserServiceImplService userServiceImplService = new UserServiceImplService();
        UserService userService = userServiceImplService.getUserServiceImplPort();

        for (Movie movie : user.getMovieSet()) {
            if (movie.getId() != idMovie) {
                userService.addMovie(idMovie, user.getId());
                resp.sendRedirect(Page.Servlet.MY_MOVIES);
                return;
            } else {
                session.setAttribute(Constatnts.ERROR_MESSAGE, "Movie is already exists in your list");
                resp.sendRedirect(req.getHeader(Constatnts.REFERER));
                return;
            }
        }



    }
}

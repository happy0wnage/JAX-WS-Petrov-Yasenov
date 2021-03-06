package ua.nure.petryasya.web.servlet;

import ua.nure.petryasya.constants.Constatnts;
import ua.nure.petryasya.core.movie.MovieService;
import ua.nure.petryasya.core.movie.MovieServiceImplService;
import ua.nure.petryasya.core.user.User;
import ua.nure.petryasya.core.user.UserService;
import ua.nure.petryasya.core.user.UserServiceImplService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "deleteMovie", urlPatterns = "/deleteMovie")
public class DeleteMoviesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idMovie = Integer.parseInt(req.getParameter(Constatnts.ReqParap.ID_MOVIE));

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(Constatnts.SessionParap.LOGGED_USER);

        UserServiceImplService userServiceImplService = new UserServiceImplService();
        UserService userService = userServiceImplService.getUserServiceImplPort();

        userService.deleteMovie(idMovie, user.getId());
        resp.sendRedirect(req.getHeader(Constatnts.REFERER));
    }

}

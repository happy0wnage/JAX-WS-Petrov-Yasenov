package ua.nure.petryasya.web.servlet;

import ua.nure.petryasya.constants.Constatnts;
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

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserServiceImplService userServiceImplService = new UserServiceImplService();
        UserService userService = userServiceImplService.getUserServiceImplPort();

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        HttpSession session = req.getSession();
        for (User u : userService.getAll().getUserList()) {
            if (u.equals(user)) {
                session.setAttribute(Constatnts.SessionParap.LOGGED_USER, u);
                resp.sendRedirect(Page.Servlet.MY_MOVIES);
                return;
            }
        }

        session.setAttribute(Constatnts.ERROR_MESSAGE, "Incorrect email/password");
        resp.sendRedirect(req.getHeader(Constatnts.REFERER));
        return;

    }
}

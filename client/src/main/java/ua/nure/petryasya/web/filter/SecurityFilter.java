
package ua.nure.petryasya.web.filter;

import ua.nure.petryasya.constants.Constatnts;
import ua.nure.petryasya.core.user.User;
import ua.nure.petryasya.security.ParserUtil;
import ua.nure.petryasya.security.Rule;
import ua.nure.petryasya.security.Security;
import ua.nure.petryasya.web.constants.Page;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


@WebFilter("/*")
public class SecurityFilter implements Filter {

    private static final String RESOURCES = "resources";
    private static final String COMMON = "common";
    private static final String USER = "user";

    private Set<String> userURIs;
    private Set<String> commonURIs;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Security security = ParserUtil.unmarshal(Security.class, Constatnts.ParserPath.SECURITY_XML);
        for (Rule rule : security.getRules()) {
            if (rule.getRole().equals(COMMON)) {
                commonURIs = new HashSet<>(rule.getUrls());
            }
            if (rule.getRole().equals(USER)) {
                userURIs = new HashSet<>(rule.getUrls());
            }
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (accessGranted(httpServletRequest)) {
            filterChain.doFilter(httpServletRequest, servletResponse);
        } else {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.sendRedirect(Page.Servlet.HOME);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean accessGranted(HttpServletRequest request) {
        HttpSession userSession = request.getSession();
        String requestURI = request.getServletPath();

        if (isResources(requestURI)) {
            return true;
        }

        User usersCurrent = (User) userSession.getAttribute(Constatnts.SessionParap.LOGGED_USER);

        if (usersCurrent == null) {
            return commonURIs.contains(requestURI);
        }

        return userURIs.contains(requestURI);
    }

    private boolean isResources(String URI) {
        return URI.contains(RESOURCES);
    }
}

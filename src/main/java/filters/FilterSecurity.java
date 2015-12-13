package filters;

import dao.UserDao;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 03.12.2015.
 */
@WebFilter(filterName = "FilterSecurity")
public class FilterSecurity implements Filter {

    private static final Logger log = LoggerFactory.getLogger(FilterSecurity.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, resp);
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        //session.getAttribute("login", 10);
        session.getAttribute("password");
        UserDao userDao = new UserDao();

    }

    public void init(FilterConfig config) throws ServletException {

    }

}

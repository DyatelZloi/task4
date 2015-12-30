package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 03.12.2015.
 */
@WebFilter(filterName = "FilterSecurity", urlPatterns = "/*")
public class FilterSecurity implements Filter {

    private static final Logger log = LoggerFactory.getLogger(FilterSecurity.class);
    private ResourceBundle bundle;
    private Map map = new HashMap<String, String>();


    /**
     *
     */
    public void destroy() {
    }

    /**
     *
     * @param request
     * @param resp
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    public void doFilter(ServletRequest request, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse response = (HttpServletResponse) resp;
        User user = null;
        HttpSession session;
        session = req.getSession(false);
        if(session != null) {
            session.setAttribute("bundle", bundle);
            System.out.println(session.getAttribute("login"));
            System.out.println(session.getAttribute("role"));
            System.out.println(request.getParameter("action"));
        } else {
            session = req.getSession();
            session.setAttribute("bundle", bundle);
        }

    //    String action = req.getParameter("action");
    //    Set<Role> roles = urlMapping.get(action);
    //    if(!roles.contains(user.getRole())){
    //        session.setAttribute("roleError", "У вас нет прав на данные действия. Пожалуйста, авторизуйтесь для данного действия");
    //        resp.sendRedirect("/controller?action=sign-in-page");
    //        return;
    //    }
            chain.doFilter(req, resp);

    }

    /**
     * Инициализация фильтра безопастности
     * @param config
     * @throws ServletException
     */
    public void init(FilterConfig config) throws ServletException {
        bundle = ResourceBundle.getBundle("words");
    }
}

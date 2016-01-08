package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.*;

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
    private Map map = new HashMap();
    private Map user = new HashMap();
    private Map guest = new HashMap();
    private Map admin = new HashMap();
    private Map teacher = new HashMap();

    /**
     *
     */
    public void destroy() {
        log.info("Security filter destroyed");
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
            System.out.println(session.getAttribute("user"));
            System.out.println(request.getParameter("action"));
            System.out.println(request.getParameter("directory"));
            System.out.println(request.getParameter("action")+request.getParameter("directory"));
            user = (User) session.getAttribute("user");
            Map map1 = (Map) map.get(user.getRole());
            String containKey = request.getParameter("action")+request.getParameter("directory");
            System.out.println(map1.get(containKey));
            if (!map1.containsKey(containKey)){
                response.sendRedirect("/servlet?action=move-to&directory=sign-up");
                return;
            }
            System.out.println(map1.containsKey(request.getParameter("action")));
        } else {
            session = req.getSession();
            user = new User();
            user.setLogin("guest");
            user.setPassword("guest");
            user.setRole("guest");
            session.setAttribute("bundle", bundle);
            session.setAttribute("user", user);
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
        log.info("Security filter started");

        bundle = ResourceBundle.getBundle("words");

        user.put("find-all-coursesfind-all-course-g", null);
        guest.put("find-all-coursesfind-all-course-g", null);
        admin.put("find-all-coursesfind-all-course-g", null);
        teacher.put("find-all-coursesfind-all-course-g", null);

        user.put("move-tolog-out", null);
        admin.put("move-tolog-out", null);
        teacher.put("move-tolog-out", null);

        guest.put("move-tolog-in", null);
        teacher.put("move-tolog-in", null);
        admin.put("move-tolog-in", null);
        user.put("move-tolog-in", null);

        guest.put("log-inindex", null);
        teacher.put("log-inindex", null);
        admin.put("log-inindex", null);
        user.put("log-inindex", null);

        guest.put("move-toindex", null);
        teacher.put("move-toindex", null);
        admin.put("move-toindex", null);
        user.put("move-toindex", null);

        guest.put("log-in-null", null);
        teacher.put("log-in-null", null);
        admin.put("log-in-null", null);
        user.put("log-in-null", null);

        teacher.put("move-touser-home", null);
        admin.put("move-touser-home", null);
        user.put("move-touser-home", null);

        teacher.put("move-toupdate-user", null);
        admin.put("move-toupdate-user", null);
        user.put("move-toupdate-user", null);

        teacher.put("update-user-2update-user", null);
        admin.put("update-user-2update-user", null);
        user.put("update-user-2update-user", null);

        user.put("find-all-coursefind-all-course-g", null);
        guest.put("find-all-coursefind-all-course-g", null);
        admin.put("find-all-coursefind-all-course-g", null);
        teacher.put("find-all-coursefind-all-course-g", null);

        admin.put("update-user-update-user", null);

        guest.put("move-tosign-up", null);

        teacher.put("move-tocreate-course", null);

        teacher.put("create-courseindex", null);

        teacher.put("find-by-id-teachercreated-courses", null);

        teacher.put("update-courseindex", null);

        teacher.put("course-deleteindex", null);

        teacher.put("find-by-id-teachercreated-courses", null);

        teacher.put("find-by-id-courseregistred-students", null);

        teacher.put("update-listindex", null);

        admin.put("find-all-userscreated-users", null);

        admin.put("update-userindex", null);

        admin.put("delete-userindex", null);

        map.put("user", user);
        map.put("guest", guest);
        map.put("admin", admin);
        map.put("teacher", teacher);
    }


}

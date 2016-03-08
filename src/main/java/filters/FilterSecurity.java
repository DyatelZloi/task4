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
 * Created by Malkov Nikifor on 03.12.2015.
 */
@WebFilter(filterName = "FilterSecurity", urlPatterns = "/*")
public class FilterSecurity implements Filter {

    private static final Logger log = LoggerFactory.getLogger(FilterSecurity.class);
    private ResourceBundle bundle;
    private Map<String, String> map = new HashMap<>();

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
        HttpSession session = null;
        session = req.getSession(false);
        if(session != null) {
            session.setAttribute("bundle", bundle);
            user = (User) session.getAttribute("user");
            String containKey = request.getParameter("action");
                if (!map.containsKey(containKey) | !checkRole(map.get(containKey), user.getRole())){
                    response.sendRedirect("/servlet?action=move-to&directory=sign-up");
                    return;
                }
        } else {
            session = req.getSession();
            user = new User();
            user.setLogin("guest");
            user.setPassword("guest");
            user.setRole("guest");
            session.setAttribute("bundle", bundle);
            session.setAttribute("user", user);
        }
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

        map.put("find-all-courses", "user-guest-admin-teacher");
        map.put("move-to", "guest-teacher-admin-user");
        map.put("log-in", "guest-teacher-admin-user");
        map.put("update-user-2", "teacher-admin-user");
        map.put("find-all-course", "user-guest-admin-teacher");
        map.put("update-user-update-user", "admin");
        map.put("create-course", "teacher");
        map.put("find-by-id-teacher", "teacher");
        map.put("update-course", "teacher");
        map.put("course-delete", "teacher");
        map.put("find-by-id-teacher", "teacher");
        map.put("find-by-id-course", "teacher");
        map.put("update-list", "teacher");
        map.put("find-all-users", "admin");
        map.put("update-user", "admin");
        map.put("delete-user", "admin");
        map.put("log-out","user-admin-teacher");
        map.put("find-by-all-id-user", "user");
        map.put("registration", "user-admin-teacher-guest");
    }

    /**
     *  Проверка доступна ли такая роль
     * @param role - доступные роли
     * @param userRole - роль самого юзера
     * @return true, если роль есть и false, если её нет
     */
    private boolean checkRole (String role, String userRole) {
        String[] roles = role.split("-");
        for (String string : roles)
            if (string.equalsIgnoreCase(userRole)) return true;
        return false;
    }
}

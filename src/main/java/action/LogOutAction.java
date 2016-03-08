package action;

import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Malkov Nikifor on 30.12.2015.
 */
public class LogOutAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(LogOutAction.class);

    private static final String DIRECTORY = "/WEB-INF/index.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = new User();
        user.setLogin("guest");
        user.setPassword("guest");
        user.setRole("guest");
        session.setAttribute("user", user);
        return DIRECTORY;
    }
}

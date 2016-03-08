package servlet;

import action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import action.ExceptionAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Malkov Nikifor on 24.11.2015.
 */
public class ControllerServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(ControllerServlet.class);

    /**
     *
     */
    public static final String ACTION_PARAMETER_NAME = "action";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        String actionName = request.getParameter(ACTION_PARAMETER_NAME);
        Action context = new Action();
        try {
            context.setStrategy(actionName);
        } catch (IllegalAccessException | InstantiationException e) {
            log.error("Error when creating events");
            throw new ExceptionServlet(e);
        }
        try {
            request.getRequestDispatcher(context.executeStrategy(request,response)).forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("Error when redirecting");
            throw new ExceptionServlet(e);
        }

    }
}
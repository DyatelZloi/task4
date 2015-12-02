package servlet;

import action.Action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by DiZi on 24.11.2015.
 */
@WebServlet(name = "MyServlet", urlPatterns = "/servlet")
public class MyServlet extends HttpServlet {

    public static final String ACTION_PARAMETER_NAME = "action";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionName = request.getParameter(ACTION_PARAMETER_NAME);

        Action context = new Action();
        try {
            context.setStrategy(actionName);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        context.executeStrategy(request,response);
    }
}
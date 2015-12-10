package servlet;

import action.Action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * Created by DiZi on 24.11.2015.
 */
@WebServlet(name = "ControllerServlet", urlPatterns = "/servlet")
public class ControllerServlet extends HttpServlet {

    public static final String ACTION_PARAMETER_NAME = "action";

    // TO DO сделать сессии
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionName = request.getParameter(ACTION_PARAMETER_NAME);
        //Выпили эксперименты с бандлами
        //ResourceBundle bundle = ResourceBundle.getBundle("words");
        Action context = new Action();
        try {
            context.setStrategy(actionName);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        context.executeStrategy(request,response);
    }
}
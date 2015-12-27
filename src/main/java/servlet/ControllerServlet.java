package servlet;

import action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 24.11.2015.
 */
public class ControllerServlet extends HttpServlet {

    /**
     *
     */
    private static final Logger log = LoggerFactory.getLogger(ControllerServlet.class);

    /**
     *
     */
    public static final String ACTION_PARAMETER_NAME = "action";

    // TODO сделать сессии
    // TODO логи, всюду должны быть логи!

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
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
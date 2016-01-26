package action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Malkov Nikifor on 30.12.2015.
 */
public class MoveToAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(MoveToAction.class);
    private static final String DIRECTORY = "directory";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String directory = request.getParameter(DIRECTORY);
        String moveDirectory = "/WEB-INF/" + directory + ".jsp";
        try {
            request.getRequestDispatcher(moveDirectory).forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("Error when redirecting");
            throw new ExceptionAction(e);
        }
    }
}

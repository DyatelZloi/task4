package action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Malkov Nikifor on 30.12.2015.
 */
public class MoveToAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(MoveToAction.class);
    private static final String DIRECTORY = "directory";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String directory = request.getParameter(DIRECTORY);
        return "/WEB-INF/" + directory + ".jsp";
    }
}

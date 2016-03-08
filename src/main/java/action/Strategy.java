package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by DiZi on 02.12.2015.
 */
public interface Strategy {

    /**
     *
     * @param request
     * @param response
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
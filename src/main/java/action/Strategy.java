package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by DiZi on 02.12.2015.
 */
public interface Strategy {
    void execute(HttpServletRequest request, HttpServletResponse response);
}
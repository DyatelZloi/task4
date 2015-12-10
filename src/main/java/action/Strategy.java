package action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by DiZi on 02.12.2015.
 */
public interface Strategy {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
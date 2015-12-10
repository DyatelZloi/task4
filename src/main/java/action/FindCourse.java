package action;

import dao.CourseDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by DiZi on 10.12.2015.
 */
public class FindCourse implements Strategy {

    public static final String ID = "id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(ID));
        CourseDao courseDao = new CourseDao();
        request.setAttribute("createdcourses",courseDao.find(id));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/created-courses.jsp");
        dispatcher.forward(request, response);
        try {
            response.sendRedirect("/created-courses.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

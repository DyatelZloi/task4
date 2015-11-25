package servlet;

import dao.CourseDao;
import model.OptionalCourse;

import javax.servlet.RequestDispatcher;
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
    public static final String NAME_PARAMETER_NAME = "name";
    public static final String COURSE_DESCRIPTION_PARAMETER_NAME = "course-description";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionName = request.getParameter(ACTION_PARAMETER_NAME);
        switch(actionName){
            case "create-course":
                String name = request.getParameter(NAME_PARAMETER_NAME);
                String courseDescription = request.getParameter(COURSE_DESCRIPTION_PARAMETER_NAME);
                OptionalCourse course = new OptionalCourse();
                course.setName(name);
                course.setCourseDescription(courseDescription);
                CourseDao courseDao = new CourseDao();
                courseDao.create(course);
                response.sendRedirect("/course-created.jsp");
                break;
        }
    }
}
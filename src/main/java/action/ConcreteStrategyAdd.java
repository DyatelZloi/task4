package action;

import dao.CourseDao;
import entity.OptionalCourse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 02.12.2015.
 */
public class ConcreteStrategyAdd implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(ConcreteStrategyAdd.class);

    public static final String NAME_PARAMETER_NAME = "name";
    public static final String COURSE_DESCRIPTION_PARAMETER_NAME = "course-description";
    public static  final int textA = 0;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String courseDescription = request.getParameter(COURSE_DESCRIPTION_PARAMETER_NAME);
        OptionalCourse course = new OptionalCourse();
        course.setName(name);
        course.setCourseDescription(courseDescription);
        CourseDao courseDao = new CourseDao();
        courseDao.create(course);
        request.setAttribute("textA", textA);
        ResourceBundle bundle = ResourceBundle.getBundle("words");
        request.setAttribute("bundle", bundle);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
        try {
            response.sendRedirect("/course-created.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
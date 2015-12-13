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
 * Created by DiZi on 10.12.2015.
 */
public class UpdateCourse implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(UpdateCourse.class);

    public static final String NAME_PARAMETER_NAME = "name";
    public static final String COURSE_DESCRIPTION_PARAMETER_NAME = "course-description";
    public static  final int textA = 0;
    public static final String ID = "id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String courseDescription = request.getParameter(COURSE_DESCRIPTION_PARAMETER_NAME);
        int id = Integer.parseInt(request.getParameter(ID));
        OptionalCourse course = new OptionalCourse();
        course.setName(name);
        course.setCourseDescription(courseDescription);
        CourseDao courseDao = new CourseDao();
        courseDao.update(course, id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
        try {
            response.sendRedirect("/course-created.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package action;

import dao.CourseDao;
import entity.OptionalCourse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by DiZi on 02.12.2015.
 */
public class ConcreteStrategyAdd implements Strategy {

    public static final String NAME_PARAMETER_NAME = "name";
    public static final String COURSE_DESCRIPTION_PARAMETER_NAME = "course-description";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Called ConcreteStrategyAdd's execute()");
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String courseDescription = request.getParameter(COURSE_DESCRIPTION_PARAMETER_NAME);
        OptionalCourse course = new OptionalCourse();
        course.setName(name);
        course.setCourseDescription(courseDescription);
        CourseDao courseDao = new CourseDao();
        courseDao.create(course);
        try {
            response.sendRedirect("/course-created.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
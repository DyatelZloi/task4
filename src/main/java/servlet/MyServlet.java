package servlet;

import action.Context;
import dao.CourseDao;
import dao.LecturerDao;
import dao.ParticipantListDao;
import dao.StudentDao;
import entity.Lecturer;
import entity.OptionalCourse;
import entity.ParticipantList;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DiZi on 24.11.2015.
 */
@WebServlet(name = "MyServlet", urlPatterns = "/servlet")
public class MyServlet extends HttpServlet {

    public static final String ACTION_PARAMETER_NAME = "action";
    public static final String NAME_PARAMETER_NAME = "name";
    public static final String COURSE_DESCRIPTION_PARAMETER_NAME = "course-description";
    public static  final String SURNAME_PARAMETER_NAME = "surname";
    public static final String STUDENT_ID = "id-student";
    public static final String COURSE_ID = "id-course";
    public static final String SCORE = "score";
    public static final String SHORT_COMMENT = "short-comment";
    private static final String LECTURER_ID = "lecturer-id";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionName = request.getParameter(ACTION_PARAMETER_NAME);
        String name;
        String surname;
        //TO DO избавиться от свича

        Context context = new Context();
        try {
            context.setStrategy("create-course");
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        context.executeStrategy(request,response);

        switch(actionName){
            case "create-course":
                name = request.getParameter(NAME_PARAMETER_NAME);
                String courseDescription = request.getParameter(COURSE_DESCRIPTION_PARAMETER_NAME);
                OptionalCourse course = new OptionalCourse();
                course.setName(name);
                course.setCourseDescription(courseDescription);
                CourseDao courseDao = new CourseDao();
                courseDao.create(course);
                response.sendRedirect("/course-created.jsp");
                break;
            case "create-student":
                name = request.getParameter(NAME_PARAMETER_NAME);
                surname = request.getParameter(SURNAME_PARAMETER_NAME);
                Student student = new Student();
                student.setName(name);
                student.setSurname(surname);
                StudentDao studentDao = new StudentDao();
                studentDao.create(student);
                response.sendRedirect("/student-created.jsp");
                break;
            case "create-lecturer":
                name = request.getParameter(NAME_PARAMETER_NAME);
                surname = request.getParameter(SURNAME_PARAMETER_NAME);
                Lecturer lecturer = new Lecturer();
                lecturer.setName(name);
                lecturer.setSurname(surname);
                LecturerDao lecturerDao = new LecturerDao();
                lecturerDao.create(lecturer);
                response.sendRedirect("/lecturer-created.jsp");
                break;
            case "participiant-list-create":
                String idStudent = request.getParameter(STUDENT_ID);
                String idCourse = request.getParameter(COURSE_ID);
                String score = request.getParameter(SCORE);
                String shortComment = request.getParameter(SHORT_COMMENT);
                ParticipantList participantList = new ParticipantList();
                participantList.setIdCourse(idCourse);
                participantList.setIdStudent(idStudent);
                participantList.setScore(score);
                participantList.setShortComment(shortComment);
                ParticipantListDao participantListDao = new ParticipantListDao();
                participantListDao.create(participantList);
                response.sendRedirect("/list-created.jsp");
                break;
        }
    }
}
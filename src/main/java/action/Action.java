package action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 02.12.2015.
 */
public class Action {

    private static final Logger log = LoggerFactory.getLogger(Action.class);

        //TODO переименовать, имя не подхожящее

    private Strategy strategy;
    private Map map = new HashMap<String, Strategy>();


    /**
     * Заполнение мапы
     */
    private void any () {

        //TODO переименуй, чтобы было более систематизировано
        // тут всё на нашей совести, осторожно

        map.put("create-course", new CreateCourseAction());
        map.put("create-lecturer", new CreateTeacherAction());
        map.put("create-student", new CreateStudentAction());
        map.put("participiant-list-create", new CreatePatricipiantListAction());
        map.put("registration", new CreateUserAction());
        map.put("course-created", new FindCourseAction());

        map.put("update-course", new UpdateCourseAction());
        map.put("update-user", new UpdateUserAction());
        map.put("update-lecturer", new UpdateTeacherAction());
        map.put("update-student", new UpdateStudentAction());
        map.put("update-list", new UpdatePatricipiantListAction());

        map.put("delete-user", new DeleteUserAction());
        map.put("delete-list", new DeletePatricipiantListAction());
        map.put("delete-student", new DeleteStudentAction());
        map.put("delete-lecturer", new DeleteTeacherAction());
        map.put("course-delete", new DeleteCourseAction());

        map.put("find-lecturer", new FindTeacherAction());
        map.put("find-user", new FindUserAction());
        map.put("find-student", new FindStudentAction());
        map.put("find-list", new FindPatricipiantListAction());
        map.put("find-course", new FindCourseAction());

        map.put("find-by-login", new FindByStringAction());

        map.put("find-all-courses", new FindAllCourseAction());
        map.put("find-all-user", new FindAllUsersAction());
        map.put("find-all-lecturer", new FindAllTeachersAction());
        map.put("find-all-students", new FindAllStudentAction());

        map.put("log-out", new LogOutAction());

        map.put("to-registration", new MoveToRegistrationPageAction());
        map.put("to-log-in", new MoveToLogInAction());
        map.put("to-user-home", new MoveToUserHomeAction());
    }

    /**
     *
     */
    public Action() {
        any();
    }

    /**
     *
     * @param actionName
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void setStrategy(String actionName) throws IllegalAccessException, InstantiationException {
        this.strategy = (Strategy) map.get(actionName);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void executeStrategy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        strategy.execute(request, response);
    }
}
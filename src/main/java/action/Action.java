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
 * Created by Malkov Nikifor on 02.12.2015.
 */
public class Action {

    private static final Logger log = LoggerFactory.getLogger(Action.class);

    private Strategy strategy;
    private Map map = new HashMap<String, Strategy>();

    /**
     * Заполнение мапы
     */
    private void any () {

        map.put("create-course", new CreateCourseAction());
        map.put("create-list", new CreateSheetListAction());
        map.put("registration", new CreateUserAction());
        map.put("course-created", new FindCourseAction());

        map.put("update-course", new UpdateCourseAction());
        map.put("update-user", new UpdateUserAction());
        map.put("update-list", new UpdateSheetListAction());
        map.put("update-user-2", new UpdateUserAction2());

        map.put("delete-user", new DeleteUserAction());
        map.put("delete-list", new DeleteSheetListAction());
        map.put("course-delete", new DeleteCourseAction());

        map.put("find-user", new FindUserAction());
        map.put("find-list", new FindSheetListAction());
        map.put("find-course", new FindCourseAction());

        map.put("log-in", new FindByStringAction());
        map.put("find-by-id-teacher", new FindByIdTeacherAction());
        map.put("find-by-id-course", new FindAllByCourseId());
        map.put("find-by-all-id-user", new FindAllByUserIdAction());

        map.put("find-all-courses", new FindAllCourseAction());
        map.put("find-all-users", new FindAllUsersAction());

        map.put("log-out", new LogOutAction());

        map.put("move-to", new MoveToAction());

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
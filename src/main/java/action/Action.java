package action;

import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.UnknownServiceException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DiZi on 02.12.2015.
 */
public class Action {
        //TO DO переименовать, имя не подхожящее
    private Strategy strategy;

    private Map map = new HashMap<String, Strategy>();

    private void any () {

        //TO DO переименуй, чтобы было более систематизировано

        map.put("create-course", ConcreteStrategyAdd.class);
        map.put("create-lecturer",  CreateLecturerStrategy.class);
        map.put("create-student", CreateStudentStrategy.class);
        map.put("participiant-list-create", PatricipiantListCreateStrategy.class);
        map.put("registration", User.class);
        map.put("course-created", FindCourse.class);

        map.put("update-course", UpdateCourse.class);
        map.put("update-user", UpdateUser.class);
        map.put("update-lecturer", UpdateLecturer.class);
        map.put("update-student", UpdateStudent.class);
        map.put("update-list", UpdatePatricipiantList.class);

        map.put("delete-user", DeleteUser.class);
        map.put("delete-list", DeletePatricipiantList.class);
        map.put("delete-student", DeleteStudent.class);
        map.put("delete-lecturer", DeleteLecturer.class);
        map.put("course-delete", DeleteCourse.class);

        map.put("find-lecturer", FindLecturer.class);
        map.put("find-user", FindUser.class);
        map.put("find-student", FindStudent.class);
        map.put("find-list", FindPatricipiantList.class);
        map.put("find-course", FindCourse.class);

    }

    public Action() {
        any();
    }

    public void setStrategy(String actionName) throws IllegalAccessException, InstantiationException {
        Class e = (Class) map.get(actionName);
        this.strategy = (Strategy) e.newInstance();
    }

    public void executeStrategy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        strategy.execute(request, response);
    }
}
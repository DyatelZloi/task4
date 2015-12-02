package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DiZi on 02.12.2015.
 */
public class Action {

    private Strategy strategy;

    private Map map = new HashMap<String, Strategy>();

    private void any () {
        map.put("create-course", new ConcreteStrategyAdd());
        map.put("create-lecturer", new CreateLecturerStrategy());
        map.put("create-student", new CreateStudentStrategy());
        map.put("participiant-list-create", new PatricipiantListCreateStrategy());
    }

    public Action() {
    }

    public void setStrategy(String actionName) throws IllegalAccessException, InstantiationException {
        any();
        Class e = (Class) map.get(actionName);
        this.strategy = (Strategy) e.newInstance();
    }

    public void executeStrategy(HttpServletRequest request, HttpServletResponse response) {
        strategy.execute(request, response);
    }
}
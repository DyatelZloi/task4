package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DiZi on 02.12.2015.
 */
// Класс контекста использующий интерфейс стратегии
public class Context {

    private Strategy strategy;

    public Map map = new HashMap<String, Strategy>();

    public void any () {
        map.put("create-course", ConcreteStrategyAdd.class);
    }

    // Constructor
    public Context() {
    }

    // Set new strategy
    public void setStrategy(String actionName) throws IllegalAccessException, InstantiationException {
        any();
        Class e = (Class) map.get(actionName);
        Strategy o = (Strategy) e.newInstance();
        this.strategy = o;
    }

    public void executeStrategy(HttpServletRequest request, HttpServletResponse response) {
        strategy.execute(request, response);
    }
}
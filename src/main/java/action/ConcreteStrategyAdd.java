package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by DiZi on 02.12.2015.
 */
// Реализуем алгоритм с использованием интерфейса стратегии
public class ConcreteStrategyAdd implements Strategy {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Called ConcreteStrategyAdd's execute()");
    }
}
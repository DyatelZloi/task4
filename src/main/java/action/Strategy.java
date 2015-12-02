package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by DiZi on 02.12.2015.
 */
// Класс реализующий конкретную стратегию, должен наследовать этот интерфейс
// Класс контекста использует этот интерфейс для вызова конкретной стратегии
public interface Strategy {
    void execute(HttpServletRequest request, HttpServletResponse response);
}
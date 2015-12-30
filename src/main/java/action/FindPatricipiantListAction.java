package action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.FactoryDao;
import dao.GenericDao;
import dao.ParticipantListDao;
import entity.ParticipantList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 10.12.2015.
 */
public class FindPatricipiantListAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(FindPatricipiantListAction.class);

    private static final String ID = "id";

    /**
     * Find a registered student by id
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter(ID));

        FactoryDao factoryDao = FactoryDao.getInstance();
        factoryDao.beginTransaction();
        GenericDao genericDao = factoryDao.getDao(ParticipantListDao.class);
        ParticipantList participantList = (ParticipantList) genericDao.find(id);
        factoryDao.commit();

        request.setAttribute("entity", participantList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}

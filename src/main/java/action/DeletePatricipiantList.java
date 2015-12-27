package action;

import dao.ParticipantListDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.factory.DaoFactory;
import dao.factory.GenericDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 10.12.2015.
 */
public class DeletePatricipiantList implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(DeletePatricipiantList.class);

    public static final String ID = "id";

    /**
     * Delete Patricipiant List by id
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter(ID));

        DaoFactory daoFactory = DaoFactory.getInstance();
        daoFactory.beginTransaction();
        GenericDao genericDao = daoFactory.getDao(ParticipantListDao.class);
        genericDao.delete(id);
        daoFactory.commit();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
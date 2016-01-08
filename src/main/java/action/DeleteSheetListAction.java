package action;

import dao.ExceptionDao;
import dao.SheetListDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.FactoryDao;
import dao.GenericDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 10.12.2015.
 */
public class DeleteSheetListAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(DeleteSheetListAction.class);

    private static final String ID = "id";
    private static final String DIRECTORY = "directory";

    /**
     * Delete a sheet list by id
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        log.info("Begin to delete a sheet registration");
        String directory = request.getParameter(DIRECTORY);
        String moveDirectory = "/WEB-INF/" + directory + ".jsp";
        int id = Integer.parseInt(request.getParameter(ID));
        log.debug("Fields are filled");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(SheetListDao.class);
        factoryDao.beginTransaction();
        try{
            log.debug("Query execution");
            genericDao.delete(id);
        } catch (ExceptionDao e) {
            log.error("Unable to execute query");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("Delete sheet list completed");
        try {
            request.getRequestDispatcher(moveDirectory).forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExceptionDao;
import dao.FactoryDao;
import dao.GenericDao;
import dao.SheetListDao;
import entity.SheetList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Malkov Nikifor on 10.12.2015.
 */
public class FindSheetListAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(FindSheetListAction.class);

    private static final String ID = "id";
    private static final String DIRECTORY = "directory";

    /**
     * Find a registered student by id
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Begin search a sheet registration");
        String directory = request.getParameter(DIRECTORY);
        String moveDirectory = "/WEB-INF/" + directory + ".jsp";
        int id = Integer.parseInt(request.getParameter(ID));
        log.debug("Fields are filled");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(SheetListDao.class);
        factoryDao.beginTransaction();
        SheetList sheetList = null;
        try{
            log.debug("Query execution");
            sheetList = (SheetList) genericDao.find(id);
        } catch (ExceptionDao e) {
            log.error("Unable to execute query");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("Search sheet list completed");
        request.setAttribute("entity", sheetList);
        return moveDirectory;
    }
}

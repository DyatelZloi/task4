package action;

import dao.ExceptionDao;
import dao.FactoryDao;
import dao.GenericDao;
import dao.SheetListDao;
import entity.SheetList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Malkov Nikifor on 26.01.2016.
 */
public class FindAllByUserIdAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(FindAllByUserIdAction.class);

    private static final String ID = "id-user";
    private static final String DIRECTORY = "/WEB-INF/sheet-registrations.jsp";

    /**
     * Find a registered student by id
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Begin search a sheet registration");
        long id = Long.parseLong(request.getParameter(ID));
        log.debug("Fields are filled");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(SheetListDao.class);
        factoryDao.beginTransaction();
        List<SheetList> sheetList = null;
        try{
            log.debug("Query execution");
            sheetList = genericDao.findAllBy(id);
        } catch (ExceptionDao e) {
            log.error("Unable to execute query");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("Search sheet list completed");
        request.setAttribute("entity", sheetList);
        return DIRECTORY;
    }
}

package action;

import dao.*;
import entity.SheetList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Malkov Nikifor on 10.12.2015.
 */
public class UpdateSheetListAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(UpdateSheetListAction.class);

    private static final String SCORE = "score";
    private static final String SHORT_COMMENT = "short-comment";
    private static final String ID = "id";
    private static final String DIRECTORY = "/WEB-INF/index.jsp";

    /**
     * Update a sheet list by id
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Begin update a sheet list");
        int score = Integer.parseInt(request.getParameter(SCORE));
        String shortComment = request.getParameter(SHORT_COMMENT);
        int id = Integer.parseInt(request.getParameter(ID));
        SheetList sheetList = new SheetList();
        sheetList.setScore(score);
        sheetList.setShortComment(shortComment);
        log.debug("Fields are filled");
        FactoryDao factoryDao = FactoryDao.getInstance();
        factoryDao.beginTransaction();
        GenericDao genericDao = factoryDao.getDao(SheetListDao.class);
        try{
            log.debug("Query execution");
            genericDao.update(sheetList, id);
        } catch (ExceptionDao e) {
            log.error("Unable to execute query");
            factoryDao.rollback();
        }
        factoryDao.commit();
        return DIRECTORY;
    }
}
package action;

import dao.ParticipantListDao;
import entity.ParticipantList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by DiZi on 10.12.2015.
 */
public class UpdatePatricipiantList implements Strategy {

    public static final String ID_COURSE = "id-course";
    public static final String ID_STUDENT = "id-student";
    public static final String SCORE = "score";
    public static final String SHORT_COMMENT = "short-comment";
    public static final String ID = "id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCourse = Integer.parseInt(request.getParameter(ID_COURSE));
        int idStudent = Integer.parseInt(request.getParameter(ID_STUDENT));
        int score = Integer.parseInt(request.getParameter(SCORE));
        String shortComment = request.getParameter(SHORT_COMMENT);
        int id = Integer.parseInt(request.getParameter(ID));
        ParticipantList participantList = new ParticipantList();
        participantList.setIdStudent(idStudent);
        participantList.setIdCourse(idCourse);
        participantList.setScore(score);
        participantList.setShortComment(shortComment);
        ParticipantListDao participantListDao = new ParticipantListDao();
        participantListDao.update(participantList, id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-list.jsp");
        dispatcher.forward(request, response);
        try {
            response.sendRedirect("/update-list.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
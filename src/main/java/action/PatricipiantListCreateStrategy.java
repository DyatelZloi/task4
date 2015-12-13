package action;

import dao.ParticipantListDao;
import entity.ParticipantList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 02.12.2015.
 */
public class PatricipiantListCreateStrategy implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(PatricipiantListCreateStrategy.class);

    public static final String STUDENT_ID = "id-student";
    public static final String COURSE_ID = "id-course";
    public static final String SCORE = "score";
    public static final String SHORT_COMMENT = "short-comment";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String idStudent = request.getParameter(STUDENT_ID);
        String idCourse = request.getParameter(COURSE_ID);
        String score = request.getParameter(SCORE);
        String shortComment = request.getParameter(SHORT_COMMENT);
        ParticipantList participantList = new ParticipantList();
        participantList.setIdCourse(idCourse);
        participantList.setIdStudent(idStudent);
        participantList.setScore(score);
        participantList.setShortComment(shortComment);
        ParticipantListDao participantListDao = new ParticipantListDao();
        participantListDao.create(participantList);
        try {
            response.sendRedirect("/list-created.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package action;

import dao.ParticipantListDao;
import entity.ParticipantList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 07.12.2015.
 */
public class UserCreate implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(UserCreate.class);

    public static final String USER_NAME = "user-name";
    public static final String USER_PASSWORD = "password";



        @Override
        public void execute(HttpServletRequest request, HttpServletResponse response) {
            String idStudent = request.getParameter(USER_NAME);
            String idCourse = request.getParameter(USER_NAME);
            String score = request.getParameter(USER_NAME);
            String shortComment = request.getParameter(USER_NAME);
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

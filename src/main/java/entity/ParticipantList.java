package entity;

import java.sql.Clob;

/**
 * Created by DiZi on 26.11.2015.
 */
public class ParticipantList extends BaseEntity {

    private long id;
    private long idStudent;
    private long idCourse;
    private int score;
    private String shortComment;

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public long getIdStudent() {
        return idStudent;
    }

    /**
     *
     * @param idStudent
     */
    public void setIdStudent(long idStudent) {
        this.idStudent = idStudent;
    }

    /**
     *
     * @return
     */
    public long getIdCourse() {
        return idCourse;
    }

    /**
     *
     * @param idCourse
     */
    public void setIdCourse(long idCourse) {
        this.idCourse = idCourse;
    }

    /**
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     *
     * @return
     */
    public String getShortComment() {
        return shortComment;
    }

    /**
     *
     * @param shortComment
     */
    public void setShortComment(String shortComment) {
        this.shortComment = shortComment;
    }

    /**
     *
     * @param score
     */
    public void setScore(String score) {
        this.score = Integer.valueOf(score);
    }

    /**
     *
     * @param idStudent
     */
    public void setIdStudent(String idStudent) {
        this.idStudent = Integer.valueOf(idStudent);
    }

    /**
     *
     * @param idCourse
     */
    public void setIdCourse(String idCourse) {
        this.idCourse = Integer.valueOf(idCourse);
    }
}

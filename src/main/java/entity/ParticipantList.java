package entity;

/**
 * Created by DiZi on 26.11.2015.
 */
public class ParticipantList {

    private long id;

    private long idStudent;

    private long idCourse;

    private int score;

    private String shortComment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(long idStudent) {
        this.idStudent = idStudent;
    }

    public long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(long idCourse) {
        this.idCourse = idCourse;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getShortComment() {
        return shortComment;
    }

    public void setShortComment(String shortComment) {
        this.shortComment = shortComment;
    }

    public void setScore(String score) {
        this.score = Integer.valueOf(score);
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = Integer.valueOf(idStudent);
    }

    public void setIdCourse(String idCourse) {
        this.idCourse = Integer.valueOf(idCourse);
    }
}

package entity;

/**
 * Created by Malkov Nikifor on 26.11.2015.
 */
public class SheetList extends BaseEntity {

    private int id;
    private int idStudent;
    private int idCourse;
    private int score;
    private String shortComment;
    private String name;
    private String surname;
    private String courseName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "SheetList{" +
                "score=" + score +
                ", shortComment='" + shortComment + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getIdStudent() {
        return idStudent;
    }

    /**
     *
     * @param idStudent
     */
    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    /**
     *
     * @return
     */
    public int getIdCourse() {
        return idCourse;
    }

    /**
     *
     * @param idCourse
     */
    public void setIdCourse(int idCourse) {
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

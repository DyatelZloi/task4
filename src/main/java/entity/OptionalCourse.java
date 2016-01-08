package entity;

/**
 * Created by DiZi on 25.11.2015.
 */
public class OptionalCourse extends BaseEntity{

    private long id;
    private String name;
    private int lecturer;
    private String courseDescription;

    /**
     *
     * @return
     */
    public String getCourseDescription() {
        return courseDescription;
    }

    /**
     *
     * @param courseDescription
     */
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

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
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public int getLecturer() {
        return lecturer;
    }

    /***
     *
     * @param lecturer
     */
    public void setLecturer(int lecturer) {
        this.lecturer = lecturer;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "OptionalCourse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lecturer=" + lecturer +
                ", courseDescription='" + courseDescription + '\'' +
                '}';
    }
}

package entity;

/**
 * Created by DiZi on 25.11.2015.
 */
public class OptionalCourse extends BaseEntity{

    private long id;
    private String name;
    private long lecturer;
    private String courseDescription;

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLecturer() {
        return lecturer;
    }

    public void setLecturer(long lecturer) {
        this.lecturer = lecturer;
    }

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

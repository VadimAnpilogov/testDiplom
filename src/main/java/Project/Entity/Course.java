package Project.Entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String PrepName;

    public  Course(){}
    public Course(String courseName, String prepName) {
        this.courseName = courseName;
        PrepName = prepName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPrepName() {
        return PrepName;
    }

    public void setPrepName(String prepName) {
        PrepName = prepName;
    }
}

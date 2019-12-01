package Project.Entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SignUpCourse {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String UserCourseName;


    public SignUpCourse(){}

    public SignUpCourse(String courseName, String userCourseName) {
        this.courseName = courseName;
        this.UserCourseName = userCourseName;
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

    public String getUserCourseName() {
        return UserCourseName;
    }

    public void setUserCourseName(String userCourseName) {
        UserCourseName = userCourseName;
    }
}

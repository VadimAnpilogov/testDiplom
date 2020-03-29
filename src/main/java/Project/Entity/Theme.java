package Project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameTheme;
    private String nameCourse;
    private String PrepName;
    public String date;

    public Theme(String nameTheme,String nameCourse, String prepName, String date) {
        this.nameTheme = nameTheme;
        this.nameCourse = nameCourse;
        this.PrepName = prepName;
        this.date = date;
    }

    public Theme() {
    }


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "CourseTheme",
            joinColumns = {@JoinColumn(name = "ThemeId")},
            inverseJoinColumns = {@JoinColumn(name = "CourseId")}
    )
    private Course CourseThemes;

    public void addCourse(Course course){
        this.CourseThemes = course;
    }

    public Course getCourseThemes() {
        return CourseThemes;
    }

    public void setCourseThemes(Course courseThemes) {
        CourseThemes = courseThemes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTheme() {
        return nameTheme;
    }

    public void setNameTheme(String nameTheme) {
        this.nameTheme = nameTheme;
    }

    public String getPrepName() {
        return PrepName;
    }

    public void setPrepName(String prepName) {
        PrepName = prepName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

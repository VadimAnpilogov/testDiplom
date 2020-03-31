package Project.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String PrepName;
    private String description;
    private String region;
    private String price;
    private String priceType;
    private String format;

    @JsonIgnore
    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "CourseFollowers",
            joinColumns = {@JoinColumn(name = "CourseId")},
            inverseJoinColumns = {@JoinColumn(name = "UserId")}
    )
    private Set<Users> usersFol = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "CourseTheme",
            joinColumns = {@JoinColumn(name = "CourseId")},
            inverseJoinColumns = {@JoinColumn(name = "ThemeId")}
    )
    private Set<Theme> ThemesCourse = new HashSet<>();

    public Set<Theme> getThemesCourse() {
        return ThemesCourse;
    }

    public void setThemesCourse(Set<Theme> themesCourse) {
        ThemesCourse = themesCourse;
    }

    public Set<Users> getUsersFol() {
        return usersFol;
    }

    public void setUsersFol(Set<Users> usersFol) {
        this.usersFol = usersFol;
    }


    public Course() {
    }

    public Course( String courseName, String PrepName, String description, String region, String price, String priceType, String format) {
        this.courseName = courseName;
        this.PrepName = PrepName;
        this.description = description;
        this.region = region;
        this.price = price;
        this.priceType = priceType;
        this.format = format;
    }


    public Long getId() {
        return id;
    }
    public Long getId(String courseName) {
        this.courseName = courseName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}

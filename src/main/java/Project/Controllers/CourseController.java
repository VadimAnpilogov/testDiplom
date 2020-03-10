package Project.Controllers;


import Project.Entity.*;
import Project.Repository.CourseRepo;
import Project.Repository.SCourseRepo;
import Project.Repository.ThemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Controller
public class CourseController {

    @Autowired
    public CourseRepo courseRepo;
    @Autowired
    public SCourseRepo sCourseRepo;
    @Autowired
    public ThemeRepo themeRepo;

//    public  String PrepName;
    public String usernameCorse;
    public String course1;
    public String namePage = "Курсы";
    public String namePageCreate = "Создание курса";
    public String namePageTheme = "Темы курса";
    public String nameCourses;
    public String date;

    //Страница курсов
    @GetMapping("/course")
    public String course(Model model){
        model.addAttribute("namePage", namePage);
        Iterable<Course> course = courseRepo.findAllByOrderByIdAsc();
        model.addAttribute("QCourse1", course);
        return "course";
    }

//Страница создания курсов, добавление курса
    @GetMapping("addCourse")
    public String addCourse(
            @AuthenticationPrincipal User user,
            @RequestParam String courseName,
            @RequestParam String description,
            @RequestParam String region ,
            @RequestParam String price,
            @RequestParam String priceType,
            @RequestParam String format, Model model){
        model.addAttribute("namePage", namePageCreate);
        nameCourses = courseName;
        Course course = new Course(courseName, user.getUsername(), description, region, price, priceType, format);
        courseRepo.save(course);

        return  "redirect:/theme";
    }
//Страница создания курса
    @GetMapping("createCourse")
    public String CreateCourse(Model model){
        model.addAttribute("namePage", namePageCreate);
        Iterable<Course> course = courseRepo.findAllByOrderByIdAsc();
        model.addAttribute("QCourse1", course);
        return "CreateCourse";
    }
//Страница конкретного курса
    @GetMapping("SCourse")
    public String SCourse(Model model){
//        Iterable<Course> courses = courseRepo.findByCourseName(course1);
        model.addAttribute("namePage", namePage);
        Course courses = courseRepo.findByCourseName(course1);
        model.addAttribute("courses", courses);

        Iterable<Theme> theme = themeRepo.findByNameCourseOrderByDateAsc(course1);
        model.addAttribute("theme", theme);
        return "SCourse";
    }

//Получение названия курса
    @GetMapping("SCourse/{course}")
    public String SCourseC(@PathVariable String course, Model model){
        course1=course;
        model.addAttribute("namePage", namePage);
        Iterable<Theme> theme = themeRepo.findByNameCourseOrderByDateAsc(course1);
        model.addAttribute("theme", theme);
        Course courses = courseRepo.findByCourseName(course1);
        model.addAttribute("courses", courses);

        return "redirect:/SCourse";
    }
//Запись на курс
    @GetMapping("signCourse/{user}")
    public String signCourse(
            @AuthenticationPrincipal User users,
            @PathVariable String user,
            Course course,
            Model model){
        model.addAttribute("namePage", namePage);
        //usernameCorse=user;
//        SignUpCourse signUpCourse = new SignUpCourse(course1, usernameCorse);
//        sCourseRepo.save(signUpCourse);

//        Course course = new Course();
        users.getUsername();
//        course.getId(course1);
        course.addSignUp(users);

        courseRepo.save(course);
        Course courses = courseRepo.findByCourseName(course1);
        model.addAttribute("courses", courses);
        return "SCourse";
    }

    @GetMapping("addTheme")
    public String addTheme(
            @AuthenticationPrincipal User user,
            Model model, @RequestParam String nameTheme){
        model.addAttribute("namePage", namePageTheme);

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm");
        date=formatForDateNow.format(dateNow);

        Theme themes = new Theme(nameTheme, nameCourses, user.getUsername(), date);
        themeRepo.save(themes);

        Iterable<Theme> theme = themeRepo.findByNameCourseOrderByDateAsc(nameCourses);
        model.addAttribute("Theme", theme);
        return "redirect:/theme";
    }

    @GetMapping("theme")
    public String theme(Model model){
        model.addAttribute("namePage", namePageTheme);

        Iterable<Theme> theme = themeRepo.findByNameCourseOrderByDateAsc(nameCourses);
        model.addAttribute("Theme", theme);
        return "theme";
    }

}

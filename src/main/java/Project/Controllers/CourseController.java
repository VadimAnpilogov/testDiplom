package Project.Controllers;


import Project.Entity.Course;
import Project.Entity.SignUpCourse;
import Project.Repository.CourseRepo;
import Project.Repository.SCourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController {

    @Autowired
    public CourseRepo courseRepo;
    @Autowired
    public SCourseRepo sCourseRepo;

    public  String PrepName;
    public String usernameCorse;
    public String course1;
//Страница курсов
    @GetMapping("course")
    public String course(Model model){

        Iterable<Course> course = courseRepo.findAllByOrderByIdAsc();
        model.addAttribute("QCourse1", course);
        return "course";
    }
//Страница создания курсов, добавление курса
    @GetMapping("createCourse/addCourse")
    public String addCourse(@RequestParam String courseName, @RequestParam String description, @RequestParam String forUser, Model model){
//        CourseName = courseName;
        Course course = new Course(courseName, PrepName, description, forUser);
        courseRepo.save(course);
//        Iterable<Course> course1 = courseRepo.findAllByOrderByIdAsc();
//        model.addAttribute("QCourse1", course1);
        return  "CreateCourse";
    }
//Получение имени преподавателя для создания курса
    @GetMapping("createCourse/{prepName}")
    public String CreateCourse(@PathVariable String prepName, Model model){
        PrepName=prepName;
        Iterable<Course> course = courseRepo.findAllByOrderByIdAsc();
        model.addAttribute("QCourse1", course);
        return "CreateCourse";
    }
//Страница конкретного курса
    @GetMapping("SCourse")
    public String SCourse(Model model){
//        Iterable<Course> courses = courseRepo.findByCourseName(course1);
        Course courses = courseRepo.findByCourseName(course1);
        model.addAttribute("courses", courses);
        return "SCourse";
    }

//Получение названия курса
    @GetMapping("SCourse/{course}")
    public String SCourseC(@PathVariable String course, Model model){
        course1=course;
        Course courses = courseRepo.findByCourseName(course1);
        model.addAttribute("courses", courses);
        return "SCourse";
    }
//Запись на курс
    @GetMapping("signCourse/{user}")
    public String signCourse(@PathVariable String user, Model model){
        usernameCorse=user;
        SignUpCourse signUpCourse = new SignUpCourse(course1, usernameCorse);
        sCourseRepo.save(signUpCourse);
        Course courses = courseRepo.findByCourseName(course1);
        model.addAttribute("courses", courses);
        return "SCourse";
    }

}

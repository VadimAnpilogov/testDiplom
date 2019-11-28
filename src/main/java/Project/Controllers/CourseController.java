package Project.Controllers;


import Project.Entity.Course;
import Project.Repository.CourseRepo;
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

    public  String PrepName;
    public String CourseName;

    @GetMapping("course")
    public String course(Model model){

        Iterable<Course> course = courseRepo.findAllByOrderByIdAsc();
        model.addAttribute("QCourse1", course);
        return "course";
    }

    @GetMapping("createCourse/addCourse")
    public String addCourse(@RequestParam String courseName, Model model){
//        CourseName = courseName;
        Course course = new Course(courseName, PrepName);
        courseRepo.save(course);
        Iterable<Course> course1 = courseRepo.findAllByOrderByIdAsc();
        model.addAttribute("QCourse1", course1);
        return  "CreateCourse";
    }

    @GetMapping("createCourse/{prepName}")
    public String CreateCourse(@PathVariable String prepName, Model model){
        PrepName=prepName;
        Iterable<Course> course = courseRepo.findAllByOrderByIdAsc();
        model.addAttribute("QCourse1", course);
        return "CreateCourse";
    }

}

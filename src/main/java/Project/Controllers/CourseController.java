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
    @GetMapping("course/{prepName}")
    public String course(@PathVariable String prepName, Model model){
        PrepName=prepName;
        Iterable<Course> course = courseRepo.findAllByOrderByCourseNameAsc();
        model.addAttribute("Course1", course);
        return "course";
    }

    @GetMapping("addCourse")
    public String addCourse(@RequestParam String courseName, Model model){

        Iterable<Course> course1 = courseRepo.findAllByOrderByCourseNameAsc();
        model.addAttribute("Course1", course1);
        Course course = new Course(courseName, PrepName);
        courseRepo.save(course);
        return  "course";
    }



}

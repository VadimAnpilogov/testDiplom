package Project.Controllers;

import Project.Entity.Course;
import Project.Entity.Reviews;
import Project.Entity.SignUpCourse;
import Project.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import Project.Entity.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
//@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    public String username1="test1";
    public int roleA=0;
    public int roleU=1;
    public String PrepName;
    public String StudName;
    public String date = "test";
    public String namePage = "Пользователи";
    public String namePagePersonal = "Личный кабинет";
    public String namePagePers = "Страница пользователя";
    @Autowired
    private ReviewsRepo reviewsRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private UMessageRepo uMessageRepo;

    @Autowired
    private SCourseRepo sCourseRepo;

    //странца всех пользователей
    @GetMapping("users")
    public String users( Model model){
        model.addAttribute("namePage", namePage);
        Iterable<User> userRole = uMessageRepo.findAll();
        model.addAttribute("users", userRole);

        return "users";
    }

//Личный кабинет
    @GetMapping("/Personal")
    public String personalUser(Model model){
        model.addAttribute("namePage", namePagePersonal);
    User user1 = userRepo.findByUsername(username1);
      model.addAttribute("users", user1);
        Iterable<Reviews> reviews2 = reviewsRepo.findByUserReviews(username1);
        model.addAttribute("reviews", reviews2);

        Iterable<User> users = uMessageRepo.findByUsername(username1);
        model.addAttribute("personalData", users);

        Iterable<SignUpCourse> signUpCourses = sCourseRepo.findByCourseName(username1);
        model.addAttribute("signUpCourse", signUpCourses);

        Iterable<Course> course = courseRepo.findAll();
        model.addAttribute("course", course);



        return "Personal";
    }
//Получение имени пользователя для входа в личный кабинет
    @GetMapping("FilterUsernameUser/{username}")
    public String FilterFioUser (@PathVariable String username, Model model){
        model.addAttribute("namePage", namePagePersonal);
        username1 = username;
        User user1 = userRepo.findByUsername(username1);
        model.addAttribute("users", user1);

        Iterable<SignUpCourse> signUpCourses = sCourseRepo.findByCourseName(username1);
        model.addAttribute("signUpCourse", signUpCourses);
        return "redirect:/Personal";
    }


//Страница всех преподавтелей
    @GetMapping("Prep")
    public String Prep( Model model){
        model.addAttribute("namePage", namePagePersonal);
        Iterable<User> userRole = uMessageRepo.findAllByRl(roleA);
        model.addAttribute("prepod", userRole);

        return "Prep";
    }

//Страница всех студентов
    @GetMapping("Stud")
    public String Stud(Model model){
        model.addAttribute("namePage", namePagePersonal);
        Iterable<User> userRole = uMessageRepo.findAllByRl(roleU);
        model.addAttribute("student",userRole);
        return "Stud";
    }
    @GetMapping("studName/{studName}")
    public String studName(@PathVariable String studName, Model model){
        model.addAttribute("namePage", namePagePersonal);
        StudName=studName;
        Iterable<Reviews> reviews2 = reviewsRepo.findByUserReviews(StudName);
        model.addAttribute("reviews", reviews2);
        model.addAttribute("message", studName);

        Iterable<User> users = uMessageRepo.findByUsername(StudName);
        model.addAttribute("personalData", users);
        return "StudPers";
    }

    @GetMapping("prepName/{prepName}")
    public String prepName(@PathVariable String prepName, Model model){
        model.addAttribute("namePage", namePagePersonal);
        PrepName=prepName;
        Iterable<User> users = uMessageRepo.findByUsername(PrepName);
        model.addAttribute("personalData", users);
        Iterable<Reviews> reviews2 = reviewsRepo.findByUserReviews(PrepName);
        model.addAttribute("reviews", reviews2);
        model.addAttribute("message", prepName);


        return "PrepPers";
    }


    @GetMapping("studName/messageAdd")
    public String addReviewsStud(@RequestParam String reviews, Model model){

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm");
        date=formatForDateNow.format(dateNow);
        model.addAttribute("namePage", namePagePersonal);
        Reviews reviews1= new Reviews(reviews, StudName, username1, date);
        reviewsRepo.save(reviews1);
        Iterable<Reviews> reviews2 = reviewsRepo.findByUserReviews(StudName);
        model.addAttribute("reviews", reviews2);

        Iterable<User> users = uMessageRepo.findByUsername(StudName);
        model.addAttribute("personalData", users);
//        Iterable<Reviews> reviews2 = reviewsRepo.findByUserReviewsAndAutorReviews(StudName, autor);
//        model.addAttribute("reviews", reviews2);
        return "StudPers";
    }

    @GetMapping("prepName/messageAdd")
    public String addReviewsPrep(@RequestParam String reviews, Model model){
        model.addAttribute("namePage", namePagePersonal);
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm");
        date=formatForDateNow.format(dateNow);

        Reviews reviews1= new Reviews(reviews, PrepName, username1, date);
        reviewsRepo.save(reviews1);
        Iterable<User> users = uMessageRepo.findByUsername(PrepName);
        model.addAttribute("personalData", users);

        Iterable<Reviews> reviews2 = reviewsRepo.findByUserReviews(PrepName);
        model.addAttribute("reviews", reviews2);


        return "PrepPers";
    }
//страница пользователя
    @GetMapping("userPers/{username}")
    public String userPersFilter (@PathVariable String username, Model model){
        model.addAttribute("namePage", namePagePers);
        username1 = username;
        User user1 = userRepo.findByUsername(username1);
        model.addAttribute("users", user1);
        Iterable<Course> course = courseRepo.findAll();
        model.addAttribute("course", course);
        return "redirect:/userPers";
    }
 //страница пользователя
    @GetMapping("/userPers")
    public String userPers(Model model){
        model.addAttribute("namePage", namePagePers);
        Iterable<User> user = uMessageRepo.findByUsername(username1);
        model.addAttribute("users", user);

        Iterable<Course> course = courseRepo.findAll();
        model.addAttribute("course", course);
        return "userPers";
    }
}

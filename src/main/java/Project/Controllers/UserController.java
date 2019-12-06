package Project.Controllers;

import Project.Entity.Reviews;
import Project.Repository.ReviewsRepo;
import Project.Repository.UMessageRepo;
import Project.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import Project.Entity.User;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
//@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    public String username1="test1";
    public int roleA=0;
    public int roleU=1;
    public String PrepName;
    public String StudName;

    @Autowired
    private ReviewsRepo reviewsRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UMessageRepo uMessageRepo;

//Личный кабинет
    @GetMapping("/Personal")
    public String personalUser(Model model){
    User user1 = userRepo.findByUsername(username1);
      model.addAttribute("users", user1);
        Iterable<Reviews> reviews2 = reviewsRepo.findByUserReviews(username1);
        model.addAttribute("reviews", reviews2);

        Iterable<User> users = uMessageRepo.findByUsername(username1);
        model.addAttribute("personalData", users);
        return "Personal";
    }
//Получение имени пользователя для входа в личный кабинет
    @GetMapping("FilterUsernameUser/{username}")
    public String FilterFioUser (@PathVariable String username, Model model){
        username1 = username;
        User user1 = userRepo.findByUsername(username1);
        model.addAttribute("users", user1);

        return "redirect:/Personal";
    }

//Страница всех преподавтелей
    @GetMapping("Prep")
    public String Prep( Model model){
        Iterable<User> userRole = uMessageRepo.findAllByRl(roleA);
        model.addAttribute("prepod", userRole);

        return "Prep";
    }

//Страница всех студентов
    @GetMapping("Stud")
    public String Stud(Model model){

        Iterable<User> userRole = uMessageRepo.findAllByRl(roleU);
        model.addAttribute("student",userRole);
        return "Stud";
    }
    @GetMapping("studName/{studName}")
    public String studName(@PathVariable String studName, Model model){
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

        Reviews reviews1= new Reviews(reviews, StudName, username1);
        reviewsRepo.save(reviews1);
        Iterable<Reviews> reviews2 = reviewsRepo.findByUserReviews(StudName);
        model.addAttribute("reviews", reviews2);
//        Iterable<Reviews> reviews2 = reviewsRepo.findByUserReviewsAndAutorReviews(StudName, autor);
//        model.addAttribute("reviews", reviews2);
        return "StudPers";
    }

    @GetMapping("prepName/messageAdd")
    public String addReviewsPrep(@RequestParam String reviews, Model model){

        Reviews reviews1= new Reviews(reviews, PrepName, username1);
        reviewsRepo.save(reviews1);
        Iterable<Reviews> reviews2 = reviewsRepo.findByUserReviews(PrepName);
        model.addAttribute("reviews", reviews2);


        return "PrepPers";
    }





}

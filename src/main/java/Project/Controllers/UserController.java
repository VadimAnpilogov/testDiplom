package Project.Controllers;

import Project.Entity.*;
import Project.Repository.*;
import Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
    public String test = "test";
    public int boole = 0;

    @Autowired
    private ReviewsRepo reviewsRepo;
    @Autowired
    private UsersRepo userRepo;
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private UMessasgeRepo uMessageRepo;
    @Autowired
    private SCourseRepo sCourseRepo;

    @Autowired
    private UsersListRepo usersListRepo;
@Autowired
private UserService userSevice;


    //странца всех пользователей
    @GetMapping("users")
    public String users( Model model){
        model.addAttribute("namePage", namePage);
        Iterable<Users> userRole = usersListRepo.findAll();
        model.addAttribute("users", userRole);

        return "users";
    }

//Личный кабинет
    @GetMapping("/Personal")
    public String personalUser(
            @AuthenticationPrincipal Users user,
            Model model){
        model.addAttribute("namePage", namePagePersonal);
        model.addAttribute("users", userRepo.findByUsername(user.getUsername()));

        Iterable<Reviews> reviews2 = reviewsRepo.findByUserReviews(user.getUsername());
        model.addAttribute("reviews", reviews2);
        Iterable<Users> users = usersListRepo.findByUsername(user.getUsername());
        model.addAttribute("personalData", users);

        Iterable<SignUpCourse> signUpCourses = sCourseRepo.findByCourseName(user.getUsername());
        model.addAttribute("signUpCourse", signUpCourses);

        Iterable<Course> course = courseRepo.findAll();
        model.addAttribute("course", course);



        return "Personal";
    }
    @GetMapping("/PersonalData")
    public String PersonalData(
            @AuthenticationPrincipal Users user,
                                Model model){
        model.addAttribute("namePage", namePagePersonal);
        model.addAttribute("users", userRepo.findByUsername(user.getUsername()));

        Iterable<Users> users = usersListRepo.findByUsername(user.getUsername());
        model.addAttribute("personalDataEdit", users);

             return "PersonalData";
    }
    @GetMapping("/PersonalEdit")
    public String PersonalEdit(
            @AuthenticationPrincipal Users user,
            Model model){
        model.addAttribute("namePage", namePagePersonal);
        model.addAttribute("users", userRepo.findByUsername(user.getUsername()));
        if(test=="tester")
        {
            model.addAttribute("UserMessage", "Имя пользователя занято");
        }
//        test = "test";
        Iterable<Users> users = usersListRepo.findByUsername(user.getUsername());
        model.addAttribute("personalDataEdit", users);

        return "PersonalEdit";
    }
    @PostMapping("PersonalEdit")
    public String updateProfile(
            @AuthenticationPrincipal Users users,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String username,
            @RequestParam String fio,
            @RequestParam String phone,
            User user,
            Model model

    ){
//        username1=user.getUsername();
        Users userFromDB = userRepo.findByUsername(username);
        if(username.equals(users.getUsername()))
        {
            boole = 1;
        }


        if((userFromDB != null) && (boole == 0)){

            test = "tester";
            return "redirect:/PersonalEdit";
        }
        else
        {
            test = "test";
            userSevice.updateProfile(user, users ,password, email, username, fio, phone);
        }



        return "redirect:/PersonalData";
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

        Iterable<Users> users = usersListRepo.findByUsername(StudName);
        model.addAttribute("personalData", users);
        return "StudPers";
    }

    @GetMapping("prepName/{prepName}")
    public String prepName(@PathVariable String prepName, Model model){
        model.addAttribute("namePage", namePagePersonal);
        PrepName=prepName;
        Iterable<Users> users = usersListRepo.findByUsername(PrepName);
        model.addAttribute("personalData", users);
        Iterable<Reviews> reviews2 = reviewsRepo.findByUserReviews(PrepName);
        model.addAttribute("reviews", reviews2);
        model.addAttribute("message", prepName);


        return "PrepPers";
    }


    @GetMapping("studName/messageAdd")
    public String addReviewsStud(
            @AuthenticationPrincipal Users user,
            @RequestParam String reviews, Model model){

//        Date dateNow = new Date();
//        SimpleDateFormat formatForDateNow = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm");
//        date=formatForDateNow.format(dateNow);
        date = userSevice.date();
        model.addAttribute("namePage", namePagePersonal);
        Reviews reviews1= new Reviews(reviews, StudName, user.getUsername(), date);
        reviewsRepo.save(reviews1);
        Iterable<Reviews> reviews2 = reviewsRepo.findByUserReviews(StudName);
        model.addAttribute("reviews", reviews2);

        Iterable<Users> users = usersListRepo.findByUsername(StudName);
        model.addAttribute("personalData", users);
//        Iterable<Reviews> reviews2 = reviewsRepo.findByUserReviewsAndAutorReviews(StudName, autor);
//        model.addAttribute("reviews", reviews2);
        return "StudPers";
    }

    @GetMapping("prepName/messageAdd")
    public String addReviewsPrep(
            @AuthenticationPrincipal Users user,
            @RequestParam String reviews, Model model){
        model.addAttribute("namePage", namePagePersonal);
//        Date dateNow = new Date();
//        SimpleDateFormat formatForDateNow = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm");
//        date=formatForDateNow.format(dateNow);
        date = userSevice.date();
        Reviews reviews1= new Reviews(reviews, PrepName, user.getUsername(), date);
        reviewsRepo.save(reviews1);
        Iterable<Users> users = usersListRepo.findByUsername(PrepName);
        model.addAttribute("personalData", users);

        Iterable<Reviews> reviews2 = reviewsRepo.findByUserReviews(PrepName);
        model.addAttribute("reviews", reviews2);


        return "PrepPers";
    }
//страница пользователя
    @GetMapping("userPers/{users}")
    public String userPersFilter (
            @AuthenticationPrincipal User user,
            @PathVariable String users,
            Model model){
        model.addAttribute("namePage", namePagePers);
        username1 = users;
        model.addAttribute("users", userRepo.findByUsername(users));

//        Iterable<Course> courses = courseRepo.findByUsers(user);

        Iterable<Course> course = courseRepo.findAll();
        model.addAttribute("course", course);
        return "redirect:/userPers";
    }
// страница пользователя
    @GetMapping("/userPers")
    public String userPers(Model model){
        model.addAttribute("namePage", namePagePers);
        Iterable<Users> user = usersListRepo.findByUsername(username1);
        model.addAttribute("users", user);

        Iterable<Course> course = courseRepo.findAll();
        model.addAttribute("course", course);
        return "userPers";
    }


}

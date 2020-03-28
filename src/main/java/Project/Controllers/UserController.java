package Project.Controllers;

import Project.Entity.Reviews;
import Project.Entity.User;
import Project.Entity.Users;
import Project.Repository.*;
import Project.Service.MailSender;
import Project.Service.UserService;
import Project.message.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
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
    public String adminR = "[ADMIN]";
    public String userR = "[USER]";
    public String roles;
    @Autowired
    private ReviewsRepo reviewsRepo;
    @Autowired
    private UsersRepo userRepo;
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private UMessasgeRepo uMessageRepo;
    @Autowired
    private UsersListRepo usersListRepo;
    @Autowired
    private UserService userSevice;
    @Autowired
    private MailSender mailSender;

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

        roles = user.getRoles().toString();

        List<Users> users1 = usersListRepo.findByUsername(user.getUsername());
        ArrayList<Users> users2 = new ArrayList<>(users1);
        if(roles.equals(adminR)){
            if(users2.get(0).getAuthCourse().isEmpty()){
                return "Personal";
            }
            model.addAttribute("course", users2.get(0).getAuthCourse());
            model.addAttribute("status", "Преподаватель");
        }else {
            if(users2.get(0).getCourseFol().isEmpty()){
                return "Personal";
            }
            model.addAttribute("course", users2.get(0).getCourseFol());
            model.addAttribute("status", "Студент");
        }
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
            @RequestParam String username,
            @RequestParam String fio,
            @RequestParam String phone,
            User user,
            Model model

    ){
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
            userSevice.updateProfile(user, users , email, username, fio, phone);
        }



        return "redirect:/PersonalData";
    }



//страница пользователя
    @GetMapping("userPers/{users}")
    public String userPersFilter (
            @AuthenticationPrincipal User user,
            @PathVariable String users,
            Model model){
        model.addAttribute("namePage", namePagePers);
        username1 = users;
        Users users1 = userRepo.findByUsername(users);
        model.addAttribute("users", users1);
        roles = users1.getRoles().toString();

        return "redirect:/userPers";
    }
// страница пользователя
    @GetMapping("/userPers")
    public String userPers(Model model){
        model.addAttribute("namePage", namePagePers);
//        Iterable<Users> user = usersListRepo.findByUsername(username1);
        model.addAttribute("users", userRepo.findByUsername(username1));


        List<Users> users3 = usersListRepo.findByUsername(username1);
        ArrayList<Users> users2 = new ArrayList<>(users3);


        if(roles.equals(adminR)){
            model.addAttribute("status", "Преподаватель");
            if(users2.get(0).getAuthCourse().isEmpty()){
                return "userPers";
            }
            model.addAttribute("course", users2.get(0).getAuthCourse());

        }else {
            model.addAttribute("status", "Студент");
            if(users2.get(0).getCourseFol().isEmpty()){
                return "userPers";
            }
            model.addAttribute("course", users2.get(0).getCourseFol());

        }
        return "userPers";
    }
    @GetMapping("messageDev")
    public String messageDev(
            @AuthenticationPrincipal Users users,
            @RequestParam String messages
    ){

        String message = String.format(
                "Сообщение от %s! \n" +
                        "%s",
                users.getUsername(),
                messages
        );

        mailSender.send("vadick.anpilogov2015@yandex.ru", "Сообщения для разработчиков", message);
        mailSender.send("denis.moroz.98@gmail.com", "Сообщения для разработчиков", message);
        return "contacts";
    }

    @GetMapping("PersonalPassword")
    public String PersonalPassword(){

        return "PersonalPassword";
    }



    @PostMapping("PasswordNew")
    public String PasswordNew(
            @AuthenticationPrincipal Users users,
            @RequestParam String password,
            @RequestParam String passwordNew,
            @RequestParam String passwordNew2,
            Model model
    ){

       Users users1 = userRepo.findByUsername(users.getUsername());

        if(password.equals(users1.getPassword())){
            if(passwordNew.equals(passwordNew2)){
                users1.setPassword(passwordNew);
                userRepo.save(users1);
                //userSevice.updateProfile(user, users ,passwordNew, users.getUser().getEmail(), users.getUsername(), users.getUser().getFio(), users.getUser().getPhone());
            }
            else {
                model.addAttribute("errorPassword", "Пароли не совпадают");
                return "PersonalPassword";
            }

        }else {
            model.addAttribute("errorPassword", "Неверный пароль");
            return "PersonalPassword";
        }

        return "redirect:/PersonalData";
    }

    @GetMapping("Password")
    public String password(){

        return "Password";
    }

    @PostMapping("passwordMail")
    public String passwordMail(
            @RequestParam String username,
            @RequestParam String email,
            Model model
    ){
        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        String password = passwordGenerator.generate(12);

        Users users1 = userRepo.findByUsername(username);
        if(users1 != null){
            if(users1.getUser().getEmail().equals(email)){
                String message = String.format(
                        "Здравствуйте, %s! \n" +
                                "Вот ваш временный пароль, с помощью которого вы можете войти на сервис \n" +
                                "%s \n"+
                                "Если это не вы пытались воостановить пароль, то ваш аккаунт может быть под угрозой и вам нужно скорее сменить пароль.",
                        users1.getUsername(),
                        password
                );
                mailSender.send(users1.getUser().getEmail(), "Восстановление пароля", message);
            }else {
                model.addAttribute("message", "Невырный логин или пароль");
                return "Password";
            }
        }else {
            model.addAttribute("message", "Невырный логин или пароль");
            return "Password";
        }

        users1.setPassword(password);
        userRepo.save(users1);

        return "redirect:/login";
    }
}

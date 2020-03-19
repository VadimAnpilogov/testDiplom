package Project.Controllers;

import Project.Entity.Role;
import Project.Entity.User;
import Project.Entity.Users;
import Project.Repository.UserRepo;
import Project.Repository.UsersRepo;
import Project.Service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.UUID;

//import Project.Service.UserSevice;


@Controller
public class RegistrationController {


    @Autowired
    private UsersRepo userRepository;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MailSender mailSender;

    public String namePage = "Регистрация";

//Страница регистрации
    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("namePage", namePage);
        return "registration";
    }
//Старница регистрации студента
    @GetMapping("/user")
    public String userR (Model model){
        model.addAttribute("namePage", namePage);
        return "userR";
    }
//Страница регистрации преподавтеля
    @GetMapping("/admin")
    public String adminR(Model model){
        model.addAttribute("namePage", namePage);
        return "adminR";
    }

//Регистрация студента
    @PostMapping("/userR")
    public String addUser(@RequestParam String username, User user, Users users, Model model){
        model.addAttribute("namePage", namePage);
        Users userFromDB = userRepository.findByUsername(users.getUsername());
        if(userFromDB != null){
            model.addAttribute("messages", "User exists");
            return "userR";
        }
        user.setActive(true);
        user.setRl(1);
        users.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);
        users.addUser(user);
        userRepository.save(users);
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Mentor. Please, visit next link: http://localhost:8080/activate/%s",
                    users.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }

        return "course";
    }
//Регистрация преподавателя
    @PostMapping("/adminR")
    public String addAdmin(@RequestParam String username, User user,Users users, Model model){
        model.addAttribute("namePage", namePage);
        Users userFromDB = userRepository.findByUsername(users.getUsername());
        if(userFromDB != null){
            model.addAttribute("messages", "Имя пользователя занято");
            return "adminR";
        }

        user.setActive(true);
        user.setRl(0);
        users.setRoles(Collections.singleton(Role.ADMIN));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepo.save(user);
        users.addUser(user);
        userRepository.save(users);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Mentor. Please, visit next link: http://localhost:8080/activate/%s",
                    users.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }
        return "course";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        model.addAttribute("namePage", namePage);
        boolean isActivated = activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activation code is not found!");
        }

        return "redirect:/login";
    }
    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);

        userRepo.save(user);

        return true;
    }

}
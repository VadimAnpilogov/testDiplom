package Project.Controllers;

import Project.Repository.UserRepo;
import Project.Service.MailSender;
//import Project.Service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import Project.Entity.Role;
import Project.Entity.User;
import Project.Repository.UserRepo;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;


@Controller
public class RegistrationController {


    @Autowired
    private UserRepo userRepository;
//    @Autowired
//    private UserSevice userSevice;
    @Autowired
    private MailSender mailSender;

//Страница регистрации
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
//Старница регистрации студента
    @GetMapping("/user")
    public String userR (){
        return "userR";
    }
//Страница регистрации преподавтеля
    @GetMapping("/admin")
    public String adminR(){
        return "adminR";
    }

//Регистрация студента
    @PostMapping("/userR")
    public String addUser(@RequestParam String username, User user, Model model){
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if(userFromDB != null){
            model.addAttribute("messages", "User exists");
            return "userR";
        }
        user.setActive(true);
        user.setRl(1);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Mentor. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }

        return "home";
    }
//Регистрация преподавателя
    @PostMapping("/adminR")
    public String addAdmin(@RequestParam String username, User user,Model model){
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if(userFromDB != null){
            model.addAttribute("messages", "User exists");
            return "adminR";
        }

        user.setActive(true);
        user.setRl(0);
        user.setRoles(Collections.singleton(Role.ADMIN));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Mentor. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }
        return "home";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activation code is not found!");
        }

        return "login";
    }
    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);

        userRepository.save(user);

        return true;
    }

}
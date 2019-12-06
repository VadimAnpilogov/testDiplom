package Project.Controllers;

import Project.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import Project.Entity.Role;
import Project.Entity.User;
import Project.Repository.UserRepo;

import java.util.Collections;
import java.util.Map;


@Controller
public class RegistrationController {


    @Autowired
    private UserRepo userRepository;
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

        userRepository.save(user);


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

        userRepository.save(user);

        return "home";
    }

}
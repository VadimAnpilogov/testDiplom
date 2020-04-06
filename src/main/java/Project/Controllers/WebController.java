package Project.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping(value = {"/", "/NoAuthHome"})
    public String NoAuthHome() {
        return "NoAuthHome";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }


    @GetMapping("/ForPrep")
    public String ForPrep() {
        return "ForPrep";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

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

    @GetMapping("/rewiews")
    public String Rewiews()
    {
        return "rewiews";
    }

    @GetMapping("/help")
    public String help( ) {
        return "help";
    }

    @GetMapping("PersonalPassword")
    public String PersonalPassword(){
        return "PersonalPassword";
    }

    @GetMapping("Password")
    public String password(){
        return "Password";
    }

    //Страница конкретного курса
    @GetMapping("SCourse")
    public String SCourse(){
        return "SCourse";
    }



}
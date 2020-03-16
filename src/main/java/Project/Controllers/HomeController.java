package Project.Controllers;



import Project.Entity.Chat;
import Project.Repository.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HomeController {
    public String sender1 = "test";
    public String date = "test";
    public String namePage = "Контакты";
    public String namePageHelp = "Помощь";
    public String namePageHome = "Домашняя страница";
    public String namePageLogin = "Авторизация";
    @Autowired
    private ChatRepo chatRepo;

    @GetMapping(value = {"/", "/NoAuthHome"})
    public String NoAuthHome(Model model) {
        model.addAttribute("namePage", namePageHome);
        return "NoAuthHome";
    }


    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("namePage", namePageHome);
        return "home";
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("namePage", namePageLogin);
        return "login";
    }


    @GetMapping("/contacts")
    public String contacts(Model model) {
        model.addAttribute("namePage", namePage);
        return "contacts";
    }


    @GetMapping("/ForPrep")
    public String ForPrep(Model model) {
        model.addAttribute("namePage", namePage);
        return "ForPrep";
    }

}
package Project.Controllers;



import Project.Entity.User;
import Project.Repository.UMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/NoAuthHome"})
    public String NoAuthHome() {
        return "NoAuthHome";
    }


    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/help")
    public String help() {
        return "help";
    }


    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }


}
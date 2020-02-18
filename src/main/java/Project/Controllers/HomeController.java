package Project.Controllers;



import org.springframework.stereotype.Controller;
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
//    @GetMapping("/homePers")
//    public String homePers(){
//        return "redirect:/"
//    }

}
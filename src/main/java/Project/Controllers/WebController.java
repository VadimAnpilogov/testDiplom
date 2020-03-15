package Project.Controllers;

import Project.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	
    @GetMapping(value="/index")
    public String homepage(){
        return "index";
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

}
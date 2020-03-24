package Project.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	


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
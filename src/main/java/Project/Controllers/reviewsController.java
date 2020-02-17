package Project.Controllers;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class reviewsController {



    @GetMapping("/rewiews")
    public String Rewiews()
    {
        return "rewiews";
    }



}

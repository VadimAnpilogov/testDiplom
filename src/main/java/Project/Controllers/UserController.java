package Project.Controllers;

import Project.Repository.UMessageRepo;
import Project.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import Project.Entity.User;


@Controller
//@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    public String username1="test1";
    public int roleA=0;
    public int roleU=1;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UMessageRepo uMessageRepo;
    @GetMapping("/Personal")
    public String personalUser(Model model){
    User user1 = userRepo.findByUsername(username1);
      model.addAttribute("users", user1);
        return "Personal";
    }

    @GetMapping("FilterUsernameUser/{username}")
    public String FilterFioUser (@PathVariable String username, Model model){
        username1 = username;
        User user1 = userRepo.findByUsername(username1);
        model.addAttribute("users", user1);

        return "redirect:/Personal";
    }


    @GetMapping("Prep")
    public String Prep( Model model){
        Iterable<User> userRole = uMessageRepo.findAllByRl(roleA);
        model.addAttribute("prepod", userRole);

        return "Prep";
    }


    @GetMapping("Stud")
    public String Stud(Model model){

        Iterable<User> userRole = uMessageRepo.findAllByRl(roleU);
        model.addAttribute("student",userRole);
        return "Stud";
    }


}

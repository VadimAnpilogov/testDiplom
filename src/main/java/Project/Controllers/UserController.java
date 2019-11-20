package Project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import Project.Entity.User;
import Project.Repository.PersonalUserRepository;


import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    public String username1="testq";
    public String status="One";
    @Autowired
    private PersonalUserRepository userRepository;



    @GetMapping("/Personal")
    public String personalUser(Map<String, Object> model){

        Iterable<User> user1 = userRepository.findByUsername(username1);
        model.put("users", user1);
        return "Personal";
    }

//    @GetMapping("/PersonalAdmin")
//    public String personalAdmin(Map<String, Object> model){
//
//        Iterable<User> user1 = userRepository.findByUsername(username1);
//        model.put("users", user1);
//        return "PersonalAdmin";
//    }
    @GetMapping("FilterUsernameUser/{username}")
    public String FilterFioUser (@PathVariable String username, Map<String, Object> model){
        username1 = username;
        Iterable<User> user1 = userRepository.findByUsername(username);
        model.put("users", user1);

        return "redirect:/Personal";


    }
}

package Project.Controllers;


import Project.Entity.Messages;
import Project.Entity.User;
import Project.Repository.SMessageRepo;
import Project.Repository.UMessageRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MessagesController {

    public String sender1 = "test";
    public String recipient1 = "test";

    @Autowired
    public UMessageRepo uMessageRepo;
    @Autowired
    public SMessageRepo sMessageRepo;

    @GetMapping("message")
    public String message(Model model){
        Iterable<Messages> messS = sMessageRepo.findAll();
        model.addAttribute("messageS", messS);

        return "message";
    }

    @GetMapping("message/{recipient}")
    public String messageR(@PathVariable String recipient, Model model){
        recipient1 = recipient;
        Iterable<User> messU = uMessageRepo.findAllByOrderByIdAsc();
        model.addAttribute("messageU", messU);

        return "message";
    }

    @GetMapping("/messageU/{sender}")
    public String messageU(@PathVariable String sender, Model model){
        sender1 = sender;
        Iterable<Messages> messS = sMessageRepo.findAll();
        model.addAttribute("messageS", messS);
        return "message";
    }
    @PostMapping("messageAdd")
    public String messageAdd(Messages messages){
        messages.setRecipient(recipient1);
        messages.setSender(sender1);
        sMessageRepo.save(messages);

        return "message";
    }

//    @PostMapping("/adminR")
//    public String addAdmin(User user,Model model){
//        User userFromDB = userRepository.findByUsername(user.getUsername());
//        if(userFromDB != null){
//            model.addAttribute("messages", "User exists");
//            return "adminR";
//        }
//
//        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.ADMIN));
//        userRepository.save(user);
//        return "home";
//    }
}

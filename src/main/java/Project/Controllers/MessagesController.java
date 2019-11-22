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
import org.springframework.web.bind.annotation.RequestParam;

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


//        if(){
//
//        }
        Iterable<Messages> messS = sMessageRepo.findByRecipientAndSender(recipient1, sender1);
        Iterable<Messages> messS1 = sMessageRepo.findByRecipientAndSender(sender1, recipient1);
        model.addAttribute("messageS", messS);
        model.addAttribute("messageS1", messS1);

        return "message";
    }
    @GetMapping("messageS")
    public String messageS(Model model){
//        Iterable<Messages> messS = sMessageRepo.findAll();
        Iterable<Messages> messS = sMessageRepo.findByRecipientAndSender(recipient1, sender1);
        model.addAttribute("messageS", messS);

        return "messageS";
    }
    @GetMapping("messageR/{recipient}")
    public String messageR(@PathVariable String recipient, Model model){
        recipient1 = recipient;
        Iterable<User> messU = uMessageRepo.findAllByOrderByIdAsc();
        model.addAttribute("messageU", messU);

        return "message";
    }

    @GetMapping("/messageU/{sender}")
    public String messageU(@PathVariable String sender, Model model){
        sender1 = sender;
//        Iterable<Messages> messS = sMessageRepo.findAll();
        Iterable<Messages> messS = sMessageRepo.findByRecipientAndSender(recipient1, sender1);
        Iterable<Messages> messS1 = sMessageRepo.findByRecipientAndSender(sender1, recipient1);
        model.addAttribute("messageS", messS);
        model.addAttribute("messageS1", messS1);
        return "messageS";
    }
    @GetMapping("messageU/messageAdd")
    public String messageAdd(@RequestParam String message, Model model){
//        messages1.setRecipient(recipient1);
//        messages1.setSender(sender1);
//        sMessageRepo.save(messages1);

        Messages messages = new Messages(message, recipient1, sender1);
        sMessageRepo.save(messages);




//        Iterable<Messages> messS = sMessageRepo.findAll();
        Iterable<Messages> messS = sMessageRepo.findByRecipientAndSender(recipient1, sender1);
        Iterable<Messages> messS1 = sMessageRepo.findByRecipientAndSender(sender1, recipient1);
        model.addAttribute("messageS", messS);
        model.addAttribute("messageS1", messS1);

        return "messageS";
    }

}

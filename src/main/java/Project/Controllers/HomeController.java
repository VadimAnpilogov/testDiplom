package Project.Controllers;



import Project.Entity.Chat;
import Project.Entity.Messages;
import Project.Entity.User;
import Project.Repository.ChatRepo;
import Project.Repository.ReviewsRepo;
import Project.Repository.UMessageRepo;
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

    @Autowired
    private ChatRepo chatRepo;

    @GetMapping(value = {"/", "/NoAuthHome"})
    public String NoAuthHome() {
        return "NoAuthHome";
    }


    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/help/{sender}")
    public String helpSender(@PathVariable String sender, Model model) {
        model.addAttribute("namePage", namePageHelp);
        sender1 = sender;
        Iterable<Chat> helpS = chatRepo.findAllByOrderByIdAsc();
        model.addAttribute("helpMessage", helpS);
        return "redirect:/help";
    }
    @GetMapping("/help")
    public String help( Model model) {
        model.addAttribute("namePage", namePageHelp);
        Iterable<Chat> helpS = chatRepo.findAllByOrderByIdAsc();
        model.addAttribute("helpMessage", helpS);
        return "help";
    }
    @GetMapping("helpAddMessage")
    public String AddHelpSender(@RequestParam String message, Model model){
        model.addAttribute("namePage", namePageHelp);
        Iterable<Chat> helpS = chatRepo.findAllByOrderByIdAsc();
        model.addAttribute("helpMessage", helpS);

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm");
        date=formatForDateNow.format(dateNow);

        Chat chat = new Chat(message, sender1, date);
        chatRepo.save(chat);

        return "redirect:/help";
    }

    @GetMapping("/contacts")
    public String contacts(Model model) {
        model.addAttribute("namePage", namePage);
        return "contacts";
    }


}
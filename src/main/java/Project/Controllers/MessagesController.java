package Project.Controllers;


import Project.Entity.Messages;
import Project.Entity.User;
import Project.Repository.SMessageRepo;
import Project.Repository.UMessageRepo;

import Project.Service.MessageService;
import Project.Service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MessagesController {

    public String sender1 = "test";
    public String recipient1 = "test";
    public String NameMess= "test";
    public String sen;
    public String date;
    public String namePage = "Чат";

    @Autowired
    public UMessageRepo uMessageRepo;
    @Autowired
    public SMessageRepo sMessageRepo;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserSevice userSevice;

//Страница сообщений
    @GetMapping("message")
    public String message(Model model){
        model.addAttribute("namePage", namePage);
        Iterable<User> messU = uMessageRepo.findAllByOrderByIdAsc();
        model.addAttribute("messageU", messU);



//        Iterable<Messages> messages2 = sMessageRepo.findByNameMessOrderByDateAsc(NameMess);
//        model.addAttribute("messageS", messages2);


        return "message";
    }

    //Получение имени Получателя
    @GetMapping("/messageU/{recipient}")
    public String messageU(
            @AuthenticationPrincipal User user,
            @PathVariable String recipient, Model model){
        model.addAttribute("namePage", namePage);
        model.addAttribute("test", "test");
        recipient1 = recipient;

        Iterable<User> messU = uMessageRepo.findAllByOrderByIdAsc();
        model.addAttribute("messageU", messU);

        NameMess = messageService.nameDialog(recipient1,user.getUsername() );
        Iterable<Messages> messages1 = sMessageRepo.findByNameMess(NameMess);



        Iterable<Messages> messages2 = sMessageRepo.findByNameMessOrderByDateAsc(NameMess);
        model.addAttribute("messageS", messages2);
        return "message";
    }
//Отправка сообщения
    @GetMapping("messageU/messageAdd")
    public String messageAdd(
            @AuthenticationPrincipal User user,
            @RequestParam String message, Model model){
        model.addAttribute("namePage", namePage);
        model.addAttribute("test", "test");

        NameMess = messageService.nameDialog(recipient1,user.getUsername() );
        Iterable<Messages> messages1 = sMessageRepo.findByNameMess(NameMess);


        date = userSevice.date();

        Iterable<User> messU = uMessageRepo.findAllByOrderByIdAsc();
        model.addAttribute("messageU", messU);

        Messages messages = new Messages(message, recipient1, user.getUsername(), NameMess,date);
        sMessageRepo.save(messages);

        Iterable<Messages> messages2 = sMessageRepo.findByNameMessOrderByDateAsc(NameMess);
        model.addAttribute("messageS", messages2);


        return "message";
    }

}

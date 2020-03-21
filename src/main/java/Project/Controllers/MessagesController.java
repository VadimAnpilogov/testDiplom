package Project.Controllers;


import Project.Entity.Dialog;
import Project.Entity.Messages;
import Project.Entity.Users;
import Project.Repository.DialogRepo;
import Project.Repository.SMessageRepo;
import Project.Repository.UsersListRepo;
import Project.Service.MessageService;
import Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MessagesController {

    public String recipient1 = "test";
    public String NameMess= "test";
    public String date;
    public String namePage = "Чат";

    @Autowired
    public SMessageRepo sMessageRepo;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userSevice;
    @Autowired
    private DialogRepo dialogRepo;
    @Autowired
    private UsersListRepo usersListRepo;

//Страница сообщений
    @GetMapping("message")
    public String message(
            @AuthenticationPrincipal Users user,
            Model model){
        model.addAttribute("namePage", namePage);
        Iterable<Users> messU = usersListRepo.findAllByOrderByIdAsc();
        model.addAttribute("messageU", messU);

        Iterable<Dialog> dialogs = dialogRepo.findAllBySenderOrderByIdAsc(user.getUsername());
        model.addAttribute("dialogsUser", dialogs);

        return "message";
    }

    //Получение имени Получателя
    @GetMapping("/messageU/{recipient}")
    public String messageU(
            @AuthenticationPrincipal Users user,
            @PathVariable String recipient, Model model){
        model.addAttribute("namePage", namePage);
        model.addAttribute("test", "test");
        recipient1 = recipient;

        Iterable<Users> messU = usersListRepo.findAllByOrderByIdAsc();
        model.addAttribute("messageU", messU);

        NameMess = messageService.nameDialog(recipient1,user.getUsername() );
        Iterable<Messages> messages1 = sMessageRepo.findByNameMess(NameMess);



        Iterable<Messages> messages2 = sMessageRepo.findByNameMessOrderByDateAsc(NameMess);
        model.addAttribute("messageS", messages2);
        Iterable<Dialog> dialogs = dialogRepo.findAllBySenderOrderByIdAsc(user.getUsername());
        model.addAttribute("dialogsUser", dialogs);
        return "message";
    }
//Отправка сообщения
    @GetMapping("messageU/messageAdd")
    public String messageAdd(
            @AuthenticationPrincipal Users user,
            @RequestParam String message, Model model){
        model.addAttribute("namePage", namePage);
        model.addAttribute("test", "test");

        NameMess = messageService.nameDialog(recipient1,user.getUsername() );
//        Iterable<Messages> messages1 = sMessageRepo.findByNameMess(NameMess);


        date = userSevice.date();

        Iterable<Users> messU = usersListRepo.findAllByOrderByIdAsc();
        model.addAttribute("messageU", messU);

        Messages messages = new Messages(message, recipient1, user.getUsername(), NameMess,date);
        sMessageRepo.save(messages);

        Iterable<Messages> messages2 = sMessageRepo.findByNameMessOrderByDateAsc(NameMess);
        model.addAttribute("messageS", messages2);

        Iterable<Dialog> dialogs = dialogRepo.findAllBySenderOrderByIdAsc(user.getUsername());
        model.addAttribute("dialogsUser", dialogs);
        return "redirect:/message";
    }

    @GetMapping("/addDialog/{userName}")
    public String addDialogs(
            @AuthenticationPrincipal Users user,
            @PathVariable String userName){
        messageService.createDialog(user.getUsername(), userName);

        return "redirect:/message";
    }


    @GetMapping("/messageUsers/{recipient}")
    public String messageUsers(
            @AuthenticationPrincipal Users user,
            @PathVariable String recipient){

        messageService.createDialog(user.getUsername(), recipient);
        return "redirect:/messageU/{recipient}";
    }
}

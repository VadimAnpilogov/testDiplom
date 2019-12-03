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

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MessagesController {

    public String sender1 = "test";
    public String recipient1 = "test";
    public String NameMess= "test";
    public String sen;
    public String date;

    @Autowired
    public UMessageRepo uMessageRepo;
    @Autowired
    public SMessageRepo sMessageRepo;


//Страница сообщений
    @GetMapping("message")
    public String message(Model model){
        Iterable<User> messU = uMessageRepo.findAllByOrderByIdAsc();
        model.addAttribute("messageU", messU);



        Iterable<Messages> messages2 = sMessageRepo.findByNameMessOrderByDateAsc(NameMess);
        model.addAttribute("messageS", messages2);


        return "message";
    }
//Получение имени Отправителя
    @GetMapping("messageR/{sender}")
    public String messageR(@PathVariable String sender, Model model){
        sender1 = sender;
        Iterable<User> messU = uMessageRepo.findAllByOrderByIdAsc();
        model.addAttribute("messageU", messU);


        return "message";
    }
    //Получение имени Получателя
    @GetMapping("/messageU/{recipient}")
    public String messageU(@PathVariable String recipient, Model model){
        recipient1 = recipient;

        Iterable<User> messU = uMessageRepo.findAllByOrderByIdAsc();
        model.addAttribute("messageU", messU);


        NameMess=recipient1+sender1;
        Iterable<Messages> messages1 = sMessageRepo.findByNameMess(NameMess);
        sen=messages1.toString();
        if (sen == "[]") {
            NameMess = sender1+recipient1;
        }
        else {
            NameMess =recipient1+sender1;
        }


        Iterable<Messages> messages2 = sMessageRepo.findByNameMessOrderByDateAsc(NameMess);
        model.addAttribute("messageS", messages2);
        return "message";
    }
//Отправка сообщения
    @GetMapping("messageU/messageAdd")
    public String messageAdd(@RequestParam String message, Model model){

        NameMess=recipient1+sender1;
        Iterable<Messages> messages1 = sMessageRepo.findByNameMess(NameMess);
        sen=messages1.toString();
        if (sen == "[]") {
            NameMess = sender1+recipient1;
        }
        else {
            NameMess =recipient1+sender1;
        }


        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm");
        date=formatForDateNow.format(dateNow);

        Iterable<User> messU = uMessageRepo.findAllByOrderByIdAsc();
        model.addAttribute("messageU", messU);

        Messages messages = new Messages(message, recipient1, sender1, NameMess,date);
        sMessageRepo.save(messages);

        Iterable<Messages> messages2 = sMessageRepo.findByNameMessOrderByDateAsc(NameMess);
        model.addAttribute("messageS", messages2);


//        Iterable<Messages> messS = sMessageRepo.findAll();
//        Iterable<Messages> messS = sMessageRepo.findByRecipientAndSender(recipient1, sender1);
//        Iterable<Messages> messS1 = sMessageRepo.findByRecipientAndSender(sender1, recipient1);
//        model.addAttribute("messageS", messS);
//        model.addAttribute("messageS1", messS1);

        return "message";
    }

}

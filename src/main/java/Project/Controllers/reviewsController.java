package Project.Controllers;


import Project.Entity.Reviews;
import Project.Repository.ReviewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class reviewsController {
//    @Autowired
//    private ReviewsRepo reviewsRepoR;

//    public String authorReviews = "anonymous";
//    public String date = "test";
//    public String namePage = "Отзывы";

//    @GetMapping("/rewiews")
//    public String Rewiews()
//    {
//        return "rewiews";
//    }


//    @GetMapping("/rewiews/{sender}")
//    public String rewiewsSender(@PathVariable String sender, Model model) {
//        model.addAttribute("namePage", namePage);
//        authorReviews = sender;
//        Iterable<Reviews> reviewsMess = reviewsRepoR.findAllByOrderByIdAsc();
//        model.addAttribute("reviewsMessage", reviewsMess);
//        return "redirect:/rewiews";
//    }
//    @GetMapping("/rewiews")
//    public String Rewiews( Model model) {
//        model.addAttribute("namePage", namePage);
//        Iterable<Reviews> reviewsMess = reviewsRepoR.findAllByOrderByIdAsc();
//        model.addAttribute("reviewsMessage", reviewsMess);
//        return "rewiews";
//    }
//    @GetMapping("rewiewsAddMessage")
//    public String AddRewiewsSender(@RequestParam String message, Model model){
//        model.addAttribute("namePage", namePage);
//        Iterable<Reviews> reviewsMess = reviewsRepoR.findAllByOrderByIdAsc();
//        model.addAttribute("reviewsMessage", reviewsMess);
//
//        Date dateNow = new Date();
//        SimpleDateFormat formatForDateNow = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm");
//        date=formatForDateNow.format(dateNow);
//
//        Reviews reviews = new Reviews(message, authorReviews, date);
//        reviewsRepoR.save(reviews);
//
//        return "redirect:/rewiews";
//    }

}

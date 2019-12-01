package Project.Controllers;


import Project.Entity.Reviews;
import Project.Repository.ReviewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import Project.Controllers.UserController;
@Controller
public class reviewsController {
//@Autowired
//private UserController userController;
//    public String usr;
//    public String PrepName;
//    public String StudName;
//
//    @Autowired
//    private ReviewsRepo reviewsRepo;
//
//    @GetMapping("studName/{studName}")
//    public String studName(@PathVariable String studName, Model model){
//        StudName=studName;
//        model.addAttribute("message", studName);
//        return "StudPers";
//    }
//
//    @GetMapping("prepName/{prepName}")
//    public String prepName(@PathVariable String prepName, Model model){
//        PrepName=prepName;
//        model.addAttribute("message", prepName);
//        return "PrepPers";
//    }
//
//
//    @GetMapping("studName/messageAdd")
//    public String addReviewsStud(@RequestParam String reviews,
//                                 @RequestParam String autor, Model model){
//
//        Reviews reviews1= new Reviews(reviews, StudName, autor);
//        reviewsRepo.save(reviews1);
////        Iterable<Reviews> reviews2 = reviewsRepo.findByUserReviewsAndAutorReviews(StudName, autor);
////        model.addAttribute("reviews", reviews2);
//        return "StudPers";
//    }
//
//    @GetMapping("prepName/messageAdd")
//    public String addReviewsPrep(@RequestParam String reviews,
//                                 @RequestParam String autor, Model model){
//        Reviews reviews1= new Reviews(reviews, PrepName, autor);
//        reviewsRepo.save(reviews1);
////        Iterable<Reviews> reviews2 = reviewsRepo.findByUserReviewsAndAutorReviews(PrepName, autor);
////        model.addAttribute("reviews", reviews2);
//
//
//        return "PrepPers";
//    }


}

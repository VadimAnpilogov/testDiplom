package Project.Controllers;


import Project.Entity.*;
import Project.Repository.*;
import Project.Service.MessageService;
import Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class CourseController {

    @Autowired
    public CourseRepo courseRepo;
    @Autowired
    public ThemeRepo themeRepo;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private MessageService messageService;
    @Autowired
    private UsersListRepo usersListRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private NewsRepo newsRepo;


    public String usernameCorse;
    public String course1;
    public Long idCourse;
    public String namePageCreate = "Создание курса";
    public String namePageTheme = "Темы курса";
    public String nameCourses;
    public String date;
    public String roles = "[ADMIN]";
    public String roles1;
    public String test = "test";
    public Long id1;


    //Страница курсов
    @GetMapping("/course")
    public String course(Model model){

        Iterable<Course> course = courseRepo.findAllByOrderByIdAsc();
        model.addAttribute("QCourse1", course);
        return "course";
    }

//Страница создания курсов, добавление курса
    @GetMapping("addCourse")
    public String addCourse(
            @AuthenticationPrincipal Users user,
            @RequestParam String courseName,
            @RequestParam String description,
            @RequestParam String region ,
            @RequestParam String price,
            @RequestParam String priceType,
            @RequestParam String format, Model model){
        model.addAttribute("namePage", namePageCreate);
        nameCourses = courseName;
        Course course = new Course(courseName, user.getUsername(), description, region, price, priceType, format);

        Users users1 = usersRepo.findByUsername(user.getUsername());
        courseRepo.save(course);
        users1.getAuthCourse().add(course);
        usersRepo.save(users1);
        idCourse=course.getId();

        return  "redirect:/theme";
    }


//Получение названия курса
    @GetMapping("SCourse={id}")
    public String SCourseC( @AuthenticationPrincipal Users users,
                            @PathVariable Long id, Model model){

        idCourse = id;
        Optional<Course> course = courseRepo.findById(idCourse);
        model.addAttribute("courses", course.get());
        course1=course.get().getCourseName();

        model.addAttribute("theme", course.get().getThemesCourse());

        Optional<Course> usersCourse = courseRepo.findById(idCourse);
        model.addAttribute("UsersCourse", usersCourse.get().getUsersFol());
        Users users1 = usersRepo.findByUsername(users.getUsername());

        ArrayList<Users> usersCourse1 = new ArrayList<>();
        ArrayList<Users> usersCourse2 = new ArrayList<>(usersCourse.get().getUsersFol());
        usersCourse1.add(users1);

        for(int i = 0; i< usersCourse2.size(); i++) {
            System.out.println("hz = " + usersCourse2.get(i));
            if (usersCourse2.get(i) == usersCourse1.get(0)) {
                model.addAttribute("signUpCourse", users.getUsername());
            }
        }
        nameCourses=course1;

        Iterable<News> news1 = newsRepo.findByAuthorNewsOrderByDateDesc(course.get().getPrepName());
        model.addAttribute("News", news1);
        nameCourses=course1;
        return "SCourse";
    }
//Запись на курс
    @GetMapping("signCourse={user}")
    public String signCourse(
            @AuthenticationPrincipal Users users,
            @PathVariable String user,
            Model model){
        usernameCorse=users.getUsername();
        Optional<Course> course = courseRepo.findById(idCourse);

        Users users1 = usersRepo.findByUsername(users.getUsername());
        users1.getCourseFol().add(course.get());
        usersRepo.save(users1);
        messageService.createDialog(users.getUsername(), user);

        Optional<Course> courses = courseRepo.findById(idCourse);
        model.addAttribute("courses", courses.get());
        model.addAttribute("theme", course.get().getThemesCourse());
        return "SCourse";
    }

    @GetMapping("unsubscribeCourse")
    public String unsubscribeCourse(
            @AuthenticationPrincipal Users users
    ){
        Optional<Course> course = courseRepo.findById(idCourse);
        Users users1 = usersRepo.findByUsername(users.getUsername());
        users1.getCourseFol().remove(course.get());
        usersRepo.save(users1);
        return "redirect:/SCourse";
    }

    @GetMapping("addTheme")
    public String addTheme(
            @AuthenticationPrincipal Users user,
            Model model, @RequestParam String nameTheme){
        model.addAttribute("namePage", namePageTheme);
        date = userService.date();

        Theme themes = new Theme(nameTheme, nameCourses, user.getUsername(), date);

        Optional<Course> course = courseRepo.findById(idCourse);
        themeRepo.save(themes);
        course.get().getThemesCourse().add(themes);
        courseRepo.save(course.get());

        model.addAttribute("Theme", course.get().getThemesCourse());
        return "theme";
    }


    @GetMapping("theme")
    public String theme(Model model){
        Optional<Course> course = courseRepo.findById(idCourse);
//        ArrayList<Theme> themes = new ArrayList<>(course.get().getThemesCourse());
////        ArrayList<Theme> themes1 = new ArrayList<>();
////        for(int i=0; i< course.get().getThemesCourse().size(); i++){
////            themes1.add(themes.get(i));
////            System.out.println("model = " + themes.get(i));
////        }
        model.addAttribute("Theme", course.get().getThemesCourse());
        model.addAttribute("NameCourse", nameCourses);
        model.addAttribute("idCourse", idCourse);
        return "theme";
    }


    @GetMapping("deleteCourse={id}")
    public String deleteCourse(
            @PathVariable Long id){
        courseRepo.deleteById(id);
        return "redirect:/course";
    }

    @GetMapping("deleteTheme={id}")
    public String deleteTheme(
            @PathVariable Long id){
        themeRepo.deleteById(id);
        return "redirect:/theme";
    }


    @PostMapping("EditTheme")
    public String EditThemes(
            @RequestParam String nameTheme,
            Model model){
        date = userService.date();
        Optional<Theme> themes = themeRepo.findById(id1);
        themes.get().setNameTheme(nameTheme);
        themeRepo.save(themes.get());

//        Iterable<Theme> theme = themeRepo.findByNameCourseOrderByDateAsc(nameCourses);
        Optional<Course> course = courseRepo.findById(idCourse);
        model.addAttribute("Theme", course.get().getThemesCourse());
        model.addAttribute("NameCourse", nameCourses);
        model.addAttribute("idCourse", idCourse);
        return "theme";
    }


    @GetMapping("EditThemes")
    public String EditThemes(Model model){
        Optional<Theme> themes = themeRepo.findById(id1);
        model.addAttribute("Theme", themes.get().getNameTheme());
        Optional<Course> course = courseRepo.findById(idCourse);
        model.addAttribute("Themes", course.get().getThemesCourse());
        model.addAttribute("NameCourse", nameCourses);
        model.addAttribute("idCourse", idCourse);
        return "themeEdit";
    }

    @GetMapping("EditTheme={id}")
    public String EditTheme(
            @PathVariable Long id,
            Model model){
        id1=id;

        Optional<Theme> themes = themeRepo.findById(id1);
        model.addAttribute("Theme", themes.get().getNameTheme());
        Optional<Course> course = courseRepo.findById(idCourse);
        model.addAttribute("Themes", course.get().getThemesCourse());
        model.addAttribute("NameCourse", nameCourses);
        model.addAttribute("idCourse", idCourse);
        return "themeEdit";
    }


    @GetMapping("myCourse")
    public String myCourse(
            @AuthenticationPrincipal Users users,
            Model model){
        List<Users> users1 = usersListRepo.findByUsername(users.getUsername());
        roles1 = users1.get(0).getRoles().toString();
        if(roles1.equals(roles)){
            if(users1.get(0).getAuthCourse().isEmpty()){
                model.addAttribute("messCourse", "У вас пока нет ни одного курса");
                return "myCourse";
            }
            model.addAttribute("myCoursePrep", users1.get(0).getAuthCourse());
            model.addAttribute("testUser", users.getUsername());
        }else {
            if(users1.get(0).getCourseFol().isEmpty()){
                model.addAttribute("messCourse", "У вас пока нет ни одного курса");
                return "myCourse";
            }
            model.addAttribute("myCourseUser", users1.get(0).getCourseFol());
        }
        return "myCourse";
    }


    @GetMapping("/CourseEdit")
    public String CourseEdit( Model model){

        Optional<Course> course = courseRepo.findById(idCourse);
        model.addAttribute("CourseDataEdit", course.get());
        return "CourseEdit";
    }


    @PostMapping("/CourseEdit")
    public String updateCourse(
            @RequestParam String courseName,
            @RequestParam String description,
            @RequestParam String region,
            @RequestParam String price,
            @RequestParam String priceType,
            @RequestParam String format,
            Model model){
        Optional<Course> course = courseRepo.findById(idCourse);
        model.addAttribute("CourseDataEdit", course.get());
        Optional<Course> courseFromDB = courseRepo.findById(idCourse);
        if(courseFromDB.get() == null){

            return "CourseEdit";
        }
        else
        {
            courseFromDB.get().setCourseName(courseName);
            courseFromDB.get().setDescription(description);
            courseFromDB.get().setRegion(region);
            courseFromDB.get().setPrice(price);
            courseFromDB.get().setPriceType(priceType);
            courseFromDB.get().setFormat(format);
            courseRepo.save(courseFromDB.get());
        }
        course1 = courseName;
        model.addAttribute("messageC", "Изменения сохранены");
        return "CourseEdit";
    }


    @GetMapping("news")
    public String news(
            @AuthenticationPrincipal Users users,
            Model model){
        List<News> news = newsRepo.findByAuthorNewsOrderByDateDesc(users.getUsername());
        model.addAttribute("News", news);
        model.addAttribute("idCourse", idCourse);
        return "news";
    }

    @PostMapping("NewsAdd")
    public String NewsAdd(
            @AuthenticationPrincipal Users users,
            @RequestParam String messageNews){
        News news = new News(messageNews, users.getUsername(), userService.date());
        newsRepo.save(news);
        return "redirect:/news";
    }


    @GetMapping("deleteNews={id}")
    public String deleteNews(
            @PathVariable Long id){
        newsRepo.deleteById(id);
        return "redirect:/news";
    }


    @PostMapping("EditNews")
    public String EditNews(
            @AuthenticationPrincipal Users users,
            @RequestParam String messageNews,
            Model model){
        Optional<News> news = newsRepo.findById(id1);
        news.get().setMessageNews(messageNews);
        newsRepo.save(news.get());

        Iterable<News> news1 = newsRepo.findByAuthorNewsOrderByDateDesc(users.getUsername());
        model.addAttribute("News", news1);
        model.addAttribute("idCourse", idCourse);
        return "news";
    }


    @GetMapping("EditNewsGet")
    public String EditNews(
            @AuthenticationPrincipal Users users,
            Model model){
        Optional<News> news = newsRepo.findById(id1);
        model.addAttribute("News", news.get().getMessageNews());

        Iterable<News> news1 = newsRepo.findByAuthorNewsOrderByDateDesc(users.getUsername());
        model.addAttribute("NewsEd", news1);
        model.addAttribute("idCourse", idCourse);
        return "newsEdit";
    }

    @GetMapping("EditNews={id}")
    public String EditNewsId(
            @PathVariable Long id){
        id1=id;
        return "redirect:/EditNewsGet";
    }


}
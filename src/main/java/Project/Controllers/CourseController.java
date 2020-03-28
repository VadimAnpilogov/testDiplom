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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


//    public  String PrepName;
    public String usernameCorse;
    public String course1;
    public String namePage = "Курсы";
    public String namePageCreate = "Создание курса";
    public String namePageTheme = "Темы курса";
    public String nameCourses;
    public String date;
    public String roles = "[ADMIN]";
    public String roles1;
    public String test = "test";
    public int boole = 0;
    public Long id1;
    //Страница курсов
    @GetMapping("/course")
    public String course(Model model){
        model.addAttribute("namePage", namePage);
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
        Course addCourse = courseRepo.findByCourseName(courseName);
        if (addCourse != null) {
            model.addAttribute("messages", " Имя курса занято");
            return "CreateCourse";
        }
        Course course = new Course(courseName, user.getUsername(), description, region, price, priceType, format);
//        course.getUsers().add(user);
        Users users1 = usersRepo.findByUsername(user.getUsername());
        courseRepo.save(course);
        users1.getAuthCourse().add(course);
        usersRepo.save(users1);

        return  "redirect:/theme";
    }
//Страница создания курса
    @GetMapping("createCourse")
    public String CreateCourse(Model model){
        model.addAttribute("namePage", namePageCreate);
        Iterable<Course> course = courseRepo.findAllByOrderByIdAsc();
        model.addAttribute("QCourse1", course);
        return "CreateCourse";
    }

//Страница конкретного курса
    @GetMapping("SCourse")
    public String SCourse(
            @AuthenticationPrincipal Users users,
            Model model){
//        Iterable<Course> courses = courseRepo.findByCourseName(course1);
        model.addAttribute("namePage", namePage);
        Course courses = courseRepo.findByCourseName(course1);
        model.addAttribute("courses", courses);

        Iterable<Theme> theme = themeRepo.findByNameCourseOrderByDateAsc(course1);
        model.addAttribute("theme", theme);


        List<Course> usersCourse = courseRepo.findByCourseNameOrderByIdAsc(course1);
        model.addAttribute("UsersCourse", usersCourse.get(0).getUsersFol());
        Users users1 = usersRepo.findByUsername(users.getUsername());

        ArrayList<Users> usersCourse1 = new ArrayList<>();
        ArrayList<Users> usersCourse2 = new ArrayList<>(usersCourse.get(0).getUsersFol());
        usersCourse1.add(users1);

        for(int i = 0; i< usersCourse2.size(); i++) {
            System.out.println("hz = " + usersCourse2.get(i));
            if (usersCourse2.get(i) == usersCourse1.get(0)) {
                model.addAttribute("signUpCourse", users.getUsername());
            }
        }
        nameCourses=course1;
        Iterable<News> news1 = newsRepo.findByAuthorNewsOrderByDateAsc(users.getUsername());
        model.addAttribute("News", news1);
        return "SCourse";
    }

//Получение названия курса
    @GetMapping("SCourse/{course}")
    public String SCourseC(@PathVariable String course, Model model){
        course1=course;
        model.addAttribute("namePage", namePage);
        Iterable<Theme> theme = themeRepo.findByNameCourseOrderByDateAsc(course1);
        model.addAttribute("theme", theme);
        Course courses = courseRepo.findByCourseName(course1);
        model.addAttribute("courses", courses);
        nameCourses=course1;
        return "redirect:/SCourse";
    }
//Запись на курс
    @GetMapping("signCourse/{user}")
    public String signCourse(
            @AuthenticationPrincipal Users users,
            @PathVariable String user,
            Model model){
        model.addAttribute("namePage", namePage);
        model.addAttribute("signUp", "Вы записались на курс");
        usernameCorse=users.getUsername();

        Course course = courseRepo.findByCourseName(course1);

        Users users1 = usersRepo.findByUsername(users.getUsername());
        users1.getCourseFol().add(course);
        usersRepo.save(users1);

        messageService.createDialog(users.getUsername(), user);



        Iterable<Theme> theme = themeRepo.findByNameCourseOrderByDateAsc(course1);
        model.addAttribute("theme", theme);
        Course courses = courseRepo.findByCourseName(course1);
        model.addAttribute("courses", courses);

        return "redirect:/SCourse";
    }

    @GetMapping("unsubscribeCourse")
    public String unsubscribeCourse(
            @AuthenticationPrincipal Users users
    ){

        Course course = courseRepo.findByCourseName(course1);

        Users users1 = usersRepo.findByUsername(users.getUsername());
        users1.getCourseFol().remove(course);
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
        themeRepo.save(themes);

        Iterable<Theme> theme = themeRepo.findByNameCourseOrderByDateAsc(nameCourses);
        model.addAttribute("Theme", theme);
        return "redirect:/theme";
    }



    @GetMapping("theme")
    public String theme(Model model){

        Iterable<Theme> theme = themeRepo.findByNameCourseOrderByDateAsc(nameCourses);
        model.addAttribute("Theme", theme);
        model.addAttribute("NameCourse", nameCourses);
        return "theme";
    }


    @GetMapping("deleteCourse/{id}")
    public String deleteCourse(
            @PathVariable Long id
    ){

        courseRepo.deleteById(id);
        return "redirect:/course";
    }

    @GetMapping("deleteTheme/{id}")
    public String deleteTheme(
            @PathVariable Long id
    ){
        themeRepo.deleteById(id);

        return "redirect:/theme";
    }

    @PostMapping("EditTheme")
    public String EditThemes(
            @RequestParam String nameTheme,
            Model model
    ){
        date = userService.date();

        Optional<Theme> themes = themeRepo.findById(id1);
        themes.get().setNameTheme(nameTheme);
        themeRepo.save(themes.get());

        Iterable<Theme> theme = themeRepo.findByNameCourseOrderByDateAsc(nameCourses);
        model.addAttribute("Theme", theme);
        model.addAttribute("NameCourse", nameCourses);
        return "theme";
    }
    @GetMapping("EditThemes")
    public String EditThemes(Model model){
        Optional<Theme> themes = themeRepo.findById(id1);
        model.addAttribute("Theme", themes.get().getNameTheme());
        Iterable<Theme> theme = themeRepo.findByNameCourseOrderByDateAsc(nameCourses);
        model.addAttribute("Themes", theme);
        model.addAttribute("NameCourse", nameCourses);
        return "themeEdit";
    }

    @GetMapping("EditTheme/{id}")
    public String EditTheme(
            @PathVariable Long id
    ){

        id1=id;
        return "redirect:/EditThemes";
    }



    @GetMapping("myCourse")
    public String myCourse(
            @AuthenticationPrincipal Users users,
            Model model
    ){
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
    public String CourseEdit(
            @AuthenticationPrincipal Users user,
            Model model){

        model.addAttribute("courses", courseRepo.findByCourseName(course1));
        if(test=="tester")
        {
            model.addAttribute("CourseMessage", "Имя курса занято");
        }

        Course course = courseRepo.findByCourseName(course1);
        model.addAttribute("CourseDataEdit", course);

        return "CourseEdit";
    }
    @PostMapping("/CourseEdit")
    public String updateCourse(
            @AuthenticationPrincipal Users users,
            @RequestParam String courseName,
            @RequestParam String description,
            @RequestParam String region,
            @RequestParam String price,
            @RequestParam String priceType,
            @RequestParam String format,
            User user,
            Model model

    ){
        Course coursefromDB = courseRepo.findByCourseName(course1);
        Course testCourse = courseRepo.findByCourseName(courseName);

        if(courseName.equals(course1))
        {
            boole = 1;
        }
        if((testCourse != null) && (boole == 0)){

            test = "tester";
            return "redirect:/CourseEdit";
        }
        else
        {
            test = "test";
            coursefromDB.setCourseName(courseName);
            coursefromDB.setDescription(description);
            coursefromDB.setRegion(region);
            coursefromDB.setPrice(price);
            coursefromDB.setPriceType(priceType);
            coursefromDB.setFormat(format);
            courseRepo.save(coursefromDB);
        }
        course1 = courseName;


        return "redirect:/SCourse";
    }


    @GetMapping("news")
    public String news(
            @AuthenticationPrincipal Users users,
            Model model){
        List<News> news = newsRepo.findByAuthorNewsOrderByDateAsc(users.getUsername());
        model.addAttribute("News", news);
        return "news";
    }

    @PostMapping("NewsAdd")
    public String NewsAdd(
            @AuthenticationPrincipal Users users,
            @RequestParam String messageNews,
            Model model){
        News news = new News(messageNews, users.getUsername(), userService.date());
        newsRepo.save(news);

        return "redirect:/news";
    }


    @GetMapping("deleteNews/{id}")
    public String deleteNews(
            @PathVariable Long id
    ){
        newsRepo.deleteById(id);

        return "redirect:/news";
    }



    @PostMapping("EditNews")
    public String EditNews(
            @AuthenticationPrincipal Users users,
            @RequestParam String messageNews,
            Model model
    ){

        Optional<News> news = newsRepo.findById(id1);
        news.get().setMessageNews(messageNews);
        newsRepo.save(news.get());

        Iterable<News> news1 = newsRepo.findByAuthorNewsOrderByDateAsc(users.getUsername());
        model.addAttribute("News", news1);
        return "news";
    }

    @GetMapping("EditNewsGet")
    public String EditNews(
            @AuthenticationPrincipal Users users,
            Model model){
        Optional<News> news = newsRepo.findById(id1);
        model.addAttribute("News", news.get().getMessageNews());

        Iterable<News> news1 = newsRepo.findByAuthorNewsOrderByDateAsc(users.getUsername());
        model.addAttribute("NewsEd", news1);
        return "newsEdit";
    }

    @GetMapping("EditNews/{id}")
    public String EditNewsId(
            @PathVariable Long id
    ){

        id1=id;
        return "redirect:/EditNewsGet";
    }










//    public String coursesName;
//    @GetMapping("search")
//    public String searchCourse(
//            @RequestParam String courseNameS,
//            Model model
//    ){
//        List<Course> filterCourse = courseRepo.findAllByOrderById();
//        ArrayList<Course> filterCourses = new ArrayList<>();
//        for(int i=0; i< filterCourse.size(); i++){
//            coursesName = filterCourse.get(i).getCourseName();
//           if(coursesName.toUpperCase().contains(courseNameS.toUpperCase())){
//               filterCourses.add(filterCourse.get(i));
//           }
//
//
//
//        }
//        model.addAttribute("QCourse1", filterCourses);
//
//        return "course";
//    }
}

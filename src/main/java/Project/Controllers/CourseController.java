package Project.Controllers;


import Project.Entity.Course;
import Project.Entity.Theme;
import Project.Entity.Users;
import Project.Repository.CourseRepo;
import Project.Repository.ThemeRepo;
import Project.Repository.UsersListRepo;
import Project.Repository.UsersRepo;
import Project.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm");
        date=formatForDateNow.format(dateNow);

        Theme themes = new Theme(nameTheme, nameCourses, user.getUsername(), date);
        themeRepo.save(themes);

        Iterable<Theme> theme = themeRepo.findByNameCourseOrderByDateAsc(nameCourses);
        model.addAttribute("Theme", theme);
        return "redirect:/theme";
    }

    @GetMapping("theme")
    public String theme(Model model){
        model.addAttribute("namePage", namePageTheme);

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
}

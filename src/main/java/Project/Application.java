package Project;


import Project.Controllers.HomeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"Project", "templates"})
public class Application {

    public static final void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

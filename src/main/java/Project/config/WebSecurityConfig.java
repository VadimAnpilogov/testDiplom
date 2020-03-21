package Project.config;





import Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;



@Configuration
@EnableWebSecurity
//@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//
//    @Autowired
//    private DataSource dataSource;

    @Autowired
    private UserService userService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/NoAuthHome", "/registration", "/userR", "/adminR", "/user", "/admin", "/activate/*", "/contacts", "/ForPrep").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//                .usersByUsernameQuery("select username, password, active from usr where username=?")
//                .authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role ur on u.id = ur.user_id where u.username=?");


    }
//    @Bean
//    public PrincipalExtractor principalExtractor(UserRepo userDetailsRepo) {
//        return map -> {
////            String id = (String) map.get("sub");
////            idNew = id.toString();
//            String idNew = (String) map.get("sub");
//            User user = userDetailsRepo.findByIdNew(idNew).orElseGet(() -> {
//                User newUser = new User();
//
//                newUser.setIdNew(idNew);
//                newUser.setUsername((String) map.get("name"));
//                newUser.setEmail((String) map.get("email"));
//                newUser.setPassword((String) map.get("password"));
//                newUser.setFio((String) map.get("name"));
////                newUser.setGender((String) map.get("gender"));
////                newUser.setLocale((String) map.get("locale"));
////                newUser.setUserpic((String) map.get("picture"));
//
//                return newUser;
//            });
//
////            user.setLastVisit(LocalDateTime.now());
//
//            return userDetailsRepo.save(user);
//        };
//    }

}
package Project.Service;


import Project.Entity.User;
import Project.Entity.Users;
import Project.Repository.UserRepo;
import Project.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class UserSevice implements UserDetailsService {

    @Autowired
    private UsersRepo userRepo;

    @Autowired
    private UserRepo userRepos;
    private String Date1;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }


    public String date() {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
        Date1=formatForDateNow.format(dateNow);

        return Date1;
    }

    public void updateProfile(
            User user,
            Users users,
            String password,
            String email,
            String username,
            String fio,
            String  phone
    ) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        if (isEmailChanged) {
            user.setEmail(email);

            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }


        users.setUsername(username);
        user.setFio(fio);
        user.setPhone(phone);

        if (!StringUtils.isEmpty(password)) {
            users.setPassword(password);
        }

        userRepo.save(users);
        userRepos.save(user);


//        if (isEmailChanged) {
//            sendMessage(user);
//        }
    }
}

package Capstone.Project.VacationFinder;

import Capstone.Project.VacationFinder.models.User;
import Capstone.Project.VacationFinder.models.UserRole;
import Capstone.Project.VacationFinder.repositories.UserRepository;
import Capstone.Project.VacationFinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class VacationFinderApplication implements CommandLineRunner {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(VacationFinderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (!userRepository.existsByUsername("guest")) {
            User guest = User.builder()
                    .firstName("Robin")
                    .lastName("Smith")
                    .email("smithrobin@gmail.com")
                    .phone("1245684578")
                    .username("guest")
                    .password(passwordEncoder.encode("password"))
                    .userRole(UserRole.GUEST)
                    .enabled(true)
                    .locked(false)
                    .build();
            userService.createNewUser(guest);
        }

        if (!userRepository.existsByUsername("user")) {
            User user = User.builder()
                    .firstName("Alex")
                    .lastName("Walker")
                    .email("alexwalker@yahoo.com")
                    .phone("1245874568")
                    .username("user")
                    .password(passwordEncoder.encode("password"))
                    .userRole(UserRole.USER)
                    .enabled(true)
                    .locked(false)
                    .build();
            userService.createNewUser(user);
        }

        if (!userRepository.existsByUsername("admin")) {
            User admin = User.builder()
                    .firstName("Kyle")
                    .lastName("Jackson")
                    .email("kylejackson@yahoo.com")
                    .phone("8567487596")
                    .username("admin")
                    .password(passwordEncoder.encode("password"))
                    .userRole(UserRole.ADMIN)
                    .enabled(true)
                    .locked(false)
                    .build();
            userService.createNewUser(admin);
        }

    }
}

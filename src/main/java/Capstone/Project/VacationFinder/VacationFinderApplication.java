package Capstone.Project.VacationFinder;

import Capstone.Project.VacationFinder.models.Role;
import Capstone.Project.VacationFinder.models.User;
import Capstone.Project.VacationFinder.models.UserRole;
import Capstone.Project.VacationFinder.repositories.RoleRepository;
import Capstone.Project.VacationFinder.repositories.UserRepository;
import Capstone.Project.VacationFinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class VacationFinderApplication implements CommandLineRunner {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(VacationFinderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Role userRole = roleRepository.findByName("ROLE_USER");
        if (!userRepository.existsByUsername("guest")) {
            User guest = User.builder()
                    .firstName("Robin")
                    .lastName("Smith")
                    .email("smithrobin@gmail.com")
                    .phone("1245684578")
                    .username("guest")
                    .password("password")
                    .userRole(UserRole.GUEST)
                    .enabled(true)
                    .locked(false)
                    .build();

            guest.setRoles(Arrays.asList(userRole));

            userService.createNewUser(guest);
        }

        if (!userRepository.existsByUsername("user")) {
            User user = User.builder()
                    .firstName("Alex")
                    .lastName("Walker")
                    .email("alexwalker@yahoo.com")
                    .phone("1245874568")
                    .username("user")
                    .password("password")
                    .enabled(true)
                    .locked(false)
                    .build();

            user.setRoles(Arrays.asList(userRole));

            userService.createNewUser(user);
        }

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");

        if (!userRepository.existsByUsername("admin")) {
            User admin = User.builder()
                    .firstName("Kyle")
                    .lastName("Jackson")
                    .email("kylejackson@yahoo.com")
                    .phone("8567487596")
                    .username("admin")
                    .password("password")
                    .enabled(true)
                    .locked(false)
                    .build();

            admin.setRoles(Arrays.asList(adminRole));

            userService.createNewUser(admin);
        }

    }
}

package Capstone.Project.VacationFinder.services;

import Capstone.Project.VacationFinder.models.User;
import Capstone.Project.VacationFinder.models.UserRole;
import Capstone.Project.VacationFinder.repositories.UserRepository;
import Capstone.Project.VacationFinder.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);

    }

    public User createNewUser(User user) {
        passwordEncoder.encodedPassword(user.getPassword());

        user.setUserRole(UserRole.USER);

        userRepository.save(user);

        return user;
    }

}

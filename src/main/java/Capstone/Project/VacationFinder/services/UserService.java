package Capstone.Project.VacationFinder.services;

import Capstone.Project.VacationFinder.models.User;
import Capstone.Project.VacationFinder.repositories.UserRepository;
import Capstone.Project.VacationFinder.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User getUserById(long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else throw new Exception("User not found");
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User createNewUser(User user) {
        passwordEncoder.encodeUserPassword(user);

        userRepository.saveAndFlush(user);

        return user;
    }

    public User saveUser(User user) {
        userRepository.save(user);

        return user;
    }

}

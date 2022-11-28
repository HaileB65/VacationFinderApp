package Capstone.Project.NewMVCProject.services;

import Capstone.Project.NewMVCProject.models.User;
import Capstone.Project.NewMVCProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService  {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){return userRepository.findAll();}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);

    }

    public User createNewUser(User user){
        userRepository.save(user);
        return user;
    }

}

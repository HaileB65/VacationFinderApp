package Capstone.Project.NewMVCProject.services;

import Capstone.Project.NewMVCProject.models.User;
import Capstone.Project.NewMVCProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){return userRepository.findAll();}
}

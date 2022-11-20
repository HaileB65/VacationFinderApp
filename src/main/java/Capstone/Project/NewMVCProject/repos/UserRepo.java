package Capstone.Project.NewMVCProject.repos;

import Capstone.Project.NewMVCProject.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}


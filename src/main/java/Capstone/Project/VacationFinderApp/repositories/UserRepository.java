package Capstone.Project.VacationFinderApp.repositories;

import Capstone.Project.VacationFinderApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User getById(Long id);

    UserDetails findByUsername(String username) throws UsernameNotFoundException;

    boolean existsByUsername(String username) throws UsernameNotFoundException;

}


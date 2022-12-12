package Capstone.Project.VacationFinder.repositories;

import Capstone.Project.VacationFinder.models.Privilege;
import Capstone.Project.VacationFinder.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    List<Privilege> findAll();

    Privilege findByName(String name);

}


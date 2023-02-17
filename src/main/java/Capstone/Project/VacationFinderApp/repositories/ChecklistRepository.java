package Capstone.Project.VacationFinderApp.repositories;

import Capstone.Project.VacationFinderApp.models.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist, Long> {

    List<Checklist> findAll();

    Optional<Checklist> findByName(String name);

}


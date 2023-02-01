package Capstone.Project.VacationFinderApp.repositories;

import Capstone.Project.VacationFinderApp.models.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist, Long> {

    List<Checklist> findAll();

}


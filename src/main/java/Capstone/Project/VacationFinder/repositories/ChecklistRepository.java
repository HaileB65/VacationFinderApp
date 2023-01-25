package Capstone.Project.VacationFinder.repositories;

import Capstone.Project.VacationFinder.models.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist, Long> {

    List<Checklist> findAll();

}


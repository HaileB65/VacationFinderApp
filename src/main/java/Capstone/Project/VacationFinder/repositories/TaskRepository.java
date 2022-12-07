package Capstone.Project.VacationFinder.repositories;

import Capstone.Project.VacationFinder.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAll();

    List<Task> findById(int id);

    boolean existsById(int id);

}

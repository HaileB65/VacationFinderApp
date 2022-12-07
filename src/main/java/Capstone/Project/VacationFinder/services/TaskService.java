package Capstone.Project.VacationFinder.services;

import Capstone.Project.VacationFinder.models.Task;
import Capstone.Project.VacationFinder.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }


    public Task createNewTask(Task task) {
        taskRepository.save(task);
        return task;
    }

}

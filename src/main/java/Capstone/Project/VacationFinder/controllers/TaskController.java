package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.Task;
import Capstone.Project.VacationFinder.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/tasks")
    public String showTasksPage(Model model) {
        List<Task> tasksTable = taskService.getAllTasks();
        model.addAttribute("tasksTable", tasksTable);

        return "tasks-page";
    }

    @GetMapping("/newTask")
    public String createNewTask(Model model) {
        model.addAttribute("newTask", new Task());
        return "new-Task";
    }

    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute("newTask") Task newTask) {
        taskService.createNewTask(newTask);
        return "redirect:/tasks";
    }

}

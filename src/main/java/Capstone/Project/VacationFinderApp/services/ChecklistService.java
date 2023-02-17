package Capstone.Project.VacationFinderApp.services;

import Capstone.Project.VacationFinderApp.models.Checklist;
import Capstone.Project.VacationFinderApp.repositories.ChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChecklistService {
    @Autowired
    ChecklistRepository checklistRepository;

    /**
     * Retrieves checklist from database.
     *
     * @param id checklist ID
     * @return returns checklist from database.
     * @throws Exception
     */
    public Checklist getChecklistById(long id) throws Exception {
        Optional<Checklist> checklist = checklistRepository.findById(id);
        if (checklist.isPresent()) {
            return checklist.get();
        } else throw new Exception("Checklist not found");
    }

    public Checklist getChecklistByName(String name) throws Exception {
        Optional<Checklist> checklist = checklistRepository.findByName(name);
        if (checklist.isPresent()) {
            return checklist.get();
        } else throw new Exception("Checklist name not found");
    }

    /**
     * Saves a checklist.
     *
     * @param checklist Checklist to be saved to database.
     * @return returns saved checklist.
     */
    public Checklist saveChecklist(Checklist checklist) {

        checklistRepository.save(checklist);

        return checklist;
    }

    /**
     * Deletes checklist from database.
     *
     * @param checklist Checklist to be deleted.
     */
    public void deleteChecklist(Checklist checklist) {
        checklistRepository.delete(checklist);
    }


}

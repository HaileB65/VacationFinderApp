package Capstone.Project.VacationFinder.services;

import Capstone.Project.VacationFinder.models.Checklist;
import Capstone.Project.VacationFinder.models.Itinerary;
import Capstone.Project.VacationFinder.repositories.ChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChecklistService {
    @Autowired
    ChecklistRepository checklistRepository;


    public Checklist getChecklistById(long id) throws Exception {
        Optional<Checklist> checklist = checklistRepository.findById(id);
        if (checklist.isPresent()) {
            return checklist.get();
        } else throw new Exception("Checklist not found");
    }

    public Checklist createNewChecklist(Checklist newChecklist) {

        checklistRepository.save(newChecklist);

        return newChecklist;
    }

    public Checklist editChecklist(Checklist checklist) {

        checklistRepository.save(checklist);

        return checklist;
    }

    public Checklist saveChecklist(Checklist checklist) {

        checklistRepository.save(checklist);

        return checklist;
    }


}

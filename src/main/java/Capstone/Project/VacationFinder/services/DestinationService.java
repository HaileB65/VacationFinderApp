package Capstone.Project.VacationFinder.services;

import Capstone.Project.VacationFinder.models.Destination;
import Capstone.Project.VacationFinder.repositories.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {
    @Autowired
    DestinationRepository destinationRepository;

    public List<Destination> getAllDestinations(){return destinationRepository.findAll();}

    public Destination createNewDestination(Destination destination){
        destinationRepository.save(destination);
        return destination;
    }

    public List<Destination> getDestinationWhereMinimumBudgetGreaterThan(int minimumBudget) {
        return destinationRepository.findByMinimumBudgetGreaterThan(minimumBudget);
    }

}

package Capstone.Project.VacationFinder.services;

import Capstone.Project.VacationFinder.models.Destination;
import Capstone.Project.VacationFinder.repositories.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DestinationService {
    @Autowired
    DestinationRepository destinationRepository;

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    public Destination createNewDestination(Destination destination) {
        destinationRepository.save(destination);
        return destination;
    }

    public List<Destination> getDestinationByScenery(String scenery) {
        return destinationRepository.findByScenery(scenery);
    }

    public List<Destination> getDestinationByDailyCostLessThan(BigDecimal dailyCost) {
        return destinationRepository.findByDailyCostLessThan(dailyCost);
    }

    public List<Destination> getDestinationBySceneryAndDailyCostLessThan(String favoriteScenery, BigDecimal dailyCost) {
        return destinationRepository.findBySceneryAndDailyCostLessThan(favoriteScenery, dailyCost);
    }

}

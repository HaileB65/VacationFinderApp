package Capstone.Project.VacationFinderApp.services;

import Capstone.Project.VacationFinderApp.models.Destination;
import Capstone.Project.VacationFinderApp.repositories.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Destination> getByWeather(String weather) {
        return destinationRepository.findByWeather(weather);
    }

    public List<Destination> getByWeatherAndScenery(String weather, String scenery) {
        return destinationRepository.findByWeatherAndScenery(weather, scenery);
    }

    public Destination getByName(String name) {
        return destinationRepository.findByName(name);
    }

    public Destination getDestinationById(long id) throws Exception {
        Optional<Destination> destination = destinationRepository.findById(id);
        if (destination.isPresent()) {
            return destination.get();
        } else throw new Exception("Destination not found");
    }
}

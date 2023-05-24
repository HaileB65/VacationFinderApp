package Capstone.Project.VacationFinderApp.services;

import Capstone.Project.VacationFinderApp.models.Destination;
import Capstone.Project.VacationFinderApp.repositories.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {
    @Autowired
    DestinationRepository destinationRepository;

    @Autowired
    CacheManager cacheManager;

    /**
     * Gets all destinations from database.
     *
     * @return all destinations from database.
     */
    @Cacheable(value = "destinations")
    public List<Destination> getAllDestinations() {
        cacheManager.getCache("destinations");
        return destinationRepository.findAll();
    }

    /**
     * Creates a new destination.
     *
     * @param destination new destination to be saved.
     * @return newly saved destination.
     */
    @CacheEvict(value = "destinations", allEntries = true)
    public Destination createNewDestination(Destination destination) {
        destinationRepository.save(destination);
        return destination;
    }

    @CacheEvict(value = "destinations", allEntries = true)
    public Destination saveDestination(Destination destination) {
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

    public List<String> getAvailableSceneries() {
        return destinationRepository.findAvailableSceneries();
    }

    public List<String> getAvailableWeather() {
        return destinationRepository.findAvailableWeather();
    }
}

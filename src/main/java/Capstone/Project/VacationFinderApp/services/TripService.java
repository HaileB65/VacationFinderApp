package Capstone.Project.VacationFinderApp.services;

import Capstone.Project.VacationFinderApp.models.Trip;
import Capstone.Project.VacationFinderApp.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TripService {
    @Autowired
    TripRepository tripRepository;

    @Autowired
    CacheManager cacheManager;

    public Trip getById(long id) throws Exception {
        Optional<Trip> trip = tripRepository.findById(id);
        if (trip.isPresent()) {
            return trip.get();
        } else throw new Exception("Trip not found");
    }

    @CachePut(value = "trips", key = "#trip.id")
    public Trip saveTrip(Trip trip) {
        tripRepository.save(trip);
        cacheManager.getCache("trips");
        return trip;
    }

    @CacheEvict(value = "trips", allEntries = true)
    public Trip deleteTrip(Trip trip) {
        tripRepository.delete(trip);
        return trip;
    }


}

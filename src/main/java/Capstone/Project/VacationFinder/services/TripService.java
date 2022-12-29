package Capstone.Project.VacationFinder.services;

import Capstone.Project.VacationFinder.models.Trip;
import Capstone.Project.VacationFinder.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TripService {
    @Autowired
    TripRepository tripRepository;

    public Trip getTripById(long id) throws Exception {
        Optional<Trip> trip = tripRepository.findById(id);
        if (trip.isPresent()) {
            return trip.get();
        } else throw new Exception("Trip not found");
    }

    public Trip createNewTrip(Trip newTrip) {
        tripRepository.save(newTrip);
        return newTrip;
    }

    public Trip saveTrip(Trip newTrip) {
        tripRepository.save(newTrip);
        return newTrip;
    }


}

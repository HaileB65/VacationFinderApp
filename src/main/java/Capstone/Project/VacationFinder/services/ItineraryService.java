package Capstone.Project.VacationFinder.services;

import Capstone.Project.VacationFinder.models.Itinerary;
import Capstone.Project.VacationFinder.repositories.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItineraryService {
    @Autowired
    ItineraryRepository itineraryRepository;


    public Itinerary getItineraryById(long id) throws Exception {
        Optional<Itinerary> itinerary = itineraryRepository.findById(id);
        if (itinerary.isPresent()) {
            return itinerary.get();
        } else throw new Exception("Itinerary not found");
    }

    public Itinerary createNewItinerary(Itinerary newItinerary) {

        itineraryRepository.save(newItinerary);

        return newItinerary;
    }

    public Itinerary saveItinerary(Itinerary itinerary) {

        itineraryRepository.save(itinerary);

        return itinerary;
    }

    public void deleteItinerary(Itinerary itinerary) {
        itineraryRepository.delete(itinerary);
    }


}

package Capstone.Project.VacationFinderApp.repositories;

import Capstone.Project.VacationFinderApp.models.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {

    List<Itinerary> findAll();


}


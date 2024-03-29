package Capstone.Project.VacationFinderApp.repositories;

import Capstone.Project.VacationFinderApp.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    List<Destination> findAll();

    List<Destination> findByWeather(String weather);

    List<Destination> findByWeatherAndScenery(String weather, String scenery);

    Destination findByName(String name);

    @Query(value = "select distinct scenery from destination", nativeQuery = true)
    List<String> findAvailableSceneries();

    @Query(value = "select distinct weather from destination", nativeQuery = true)
    List<String> findAvailableWeather();

}


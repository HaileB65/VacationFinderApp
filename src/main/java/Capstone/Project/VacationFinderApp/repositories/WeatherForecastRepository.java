package Capstone.Project.VacationFinderApp.repositories;

import Capstone.Project.VacationFinderApp.models.weatherAPI.WeatherForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherForecastRepository extends JpaRepository<WeatherForecast, Long> {

    List<WeatherForecast> findAll();

    Optional<WeatherForecast> findByCity(String name);

}


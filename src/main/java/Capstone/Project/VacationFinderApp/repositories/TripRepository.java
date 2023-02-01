package Capstone.Project.VacationFinderApp.repositories;

import Capstone.Project.VacationFinderApp.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findAll();

    boolean existsByName(String name);

}


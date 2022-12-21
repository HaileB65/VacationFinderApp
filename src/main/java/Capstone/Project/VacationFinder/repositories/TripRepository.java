package Capstone.Project.VacationFinder.repositories;

import Capstone.Project.VacationFinder.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findAll();

    Trip getById(Long id);

}


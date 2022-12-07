package Capstone.Project.VacationFinder.repositories;

import Capstone.Project.VacationFinder.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    List<Destination> findAll();

    List<Destination> findByDailyCostLessThan(BigDecimal dailyCost);

    List<Destination> findByScenery(String scenery);

    List<Destination> findBySceneryAndDailyCostLessThan(String scenery, BigDecimal dailyCost);


}


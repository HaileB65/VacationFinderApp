package Capstone.Project.VacationFinder.repositories;

import Capstone.Project.VacationFinder.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    List<Destination> findAll();

    List<Destination> findByScenery(String scenery);

    Destination findByName(String name);

    boolean existsByName(String username);


}


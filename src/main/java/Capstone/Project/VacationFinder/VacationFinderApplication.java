package Capstone.Project.VacationFinder;

import Capstone.Project.VacationFinder.models.*;
import Capstone.Project.VacationFinder.repositories.ChecklistRepository;
import Capstone.Project.VacationFinder.repositories.ItineraryRepository;
import Capstone.Project.VacationFinder.repositories.TripRepository;
import Capstone.Project.VacationFinder.repositories.UserRepository;
import Capstone.Project.VacationFinder.services.ChecklistService;
import Capstone.Project.VacationFinder.services.ItineraryService;
import Capstone.Project.VacationFinder.services.TripService;
import Capstone.Project.VacationFinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class VacationFinderApplication implements CommandLineRunner {

    @Autowired
    ItineraryRepository itineraryRepository;

    @Autowired
    ChecklistRepository checklistRepository;

    @Autowired
    TripRepository tripRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ItineraryService itineraryService;

    @Autowired
    ChecklistService checklistService;

    @Autowired
    TripService tripService;
    @Autowired
    UserService userService;


    public static void main(String[] args) {
        SpringApplication.run(VacationFinderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if(!itineraryRepository.existsById(1L)) {
            Itinerary itinerary = Itinerary.builder()
                    .item1("dfas")
                    .item2("fdas")
                    .item3("dfsa")
                    .build();

            itineraryService.createNewItinerary(itinerary);
        }

        if(!checklistRepository.existsById(1L)) {
           Checklist checklist = Checklist.builder()
                    .item1("fdas")
                    .item2("fdas")
                    .item3("asdf")
                    .build();

            checklistService.createNewChecklist(checklist);
        }


        if(!tripRepository.existsById(1L)) {
            Itinerary itinerary = Itinerary.builder()
                    .item1("213")
                    .item2("213")
                    .item3("342")
                    .build();

            Checklist checklist = Checklist.builder()
                    .item1("324")
                    .item2("342")
                    .item3("asd342f")
                    .build();

            Trip trip = Trip.builder()
                    .itinerary(itinerary)
                    .checklist(checklist)
                    .selectedDestination("Rome")
                    .build();

            tripService.createNewTrip(trip);
        }

        if (!userRepository.existsById(1L)) {
            User guest = User.builder()
                    .firstName("Robin")
                    .lastName("Smith")
                    .email("smithrobin@gmail.com")
                    .phone("1245684578")
                    .username("guest")
                    .password("password")
                    .userRole(UserRole.GUEST)
//                    .trip(new Trip())
                    .enabled(true)
                    .locked(false)
                    .build();

            userService.createNewUser(guest);

        }

        if (!userRepository.existsByUsername("Alex45")) {
            User user = User.builder()
                    .firstName("Alex")
                    .lastName("Walker")
                    .email("alexwalker@yahoo.com")
                    .phone("1245874568")
                    .username("Alex45")
                    .password("Walker76")
                    .userRole(UserRole.USER)
                    .enabled(true)
                    .locked(false)
                    .build();

            userService.createNewUser(user);
        }

        if (!userRepository.existsByUsername("admin")) {
            User admin = User.builder()
                    .firstName("Kyle")
                    .lastName("Jackson")
                    .email("kylejackson@yahoo.com")
                    .phone("8567487596")
                    .username("admin")
                    .password("password")
                    .userRole(UserRole.ADMIN)
//                    .trip(new Trip())
                    .enabled(true)
                    .locked(false)
                    .build();

            userService.createNewUser(admin);
        }
    }

}

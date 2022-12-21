package Capstone.Project.VacationFinder;

import Capstone.Project.VacationFinder.models.*;
import Capstone.Project.VacationFinder.repositories.*;
import Capstone.Project.VacationFinder.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class VacationFinderApplication implements CommandLineRunner {

    @Autowired
    ItineraryRepository itineraryRepository;

    @Autowired
    ChecklistRepository checklistRepository;

    @Autowired
    DestinationRepository destinationRepository;

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

    @Autowired
    DestinationService destinationService;

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

        if (!userRepository.existsByUsername("Robin41")) {
            Itinerary itinerary = Itinerary.builder()
                    .item1("Red")
                    .item2("Orange")
                    .item3("Blue")
                    .build();

            Checklist checklist = Checklist.builder()
                    .item1("Yellow")
                    .item2("Grey")
                    .item3("Turquoise")
                    .build();

            Trip trip = Trip.builder()
                    .itinerary(itinerary)
                    .checklist(checklist)
                    .selectedDestination("Japan")
                    .build();

            ArrayList<Trip> list = new ArrayList<>(Arrays.asList(trip));

            Set<Trip> set = new HashSet<>(list);

            User guest = User.builder()
                    .firstName("Robin")
                    .lastName("Smith")
                    .email("smithrobin@gmail.com")
                    .phone("1245684578")
                    .username("Robin41")
                    .password("password")
                    .trips(set)
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

        if(!destinationRepository.existsByName("Bahamas")){
            Destination bahamas = Destination.builder()
                    .name("Bahamas")
                    .weather("warm")
                    .scenery("Sunny")
                    .activity1("Swimming")
                    .activity2("Scuba Diving")
                    .activity3("Sport Fishing")
                    .image1("https://cdn.britannica.com/14/94514-050-461B9A6D/Palm-trees-ocean-Bahamas-New-Providence-Island.jpg")
                    .image2("https://cdn.britannica.com/14/94514-050-461B9A6D/Palm-trees-ocean-Bahamas-New-Providence-Island.jpg")
                    .build();

            destinationService.createNewDestination(bahamas);
        }

        if(!destinationRepository.existsByName("Switzerland")){
            Destination switzerland = Destination.builder()
                    .name("Switzerland")
                    .weather("Cold")
                    .scenery("Snow")
                    .activity1("Skiing")
                    .activity2("Christmas Markets")
                    .activity3("Mountain Tours")
                    .image1("https://deih43ym53wif.cloudfront.net/zermatt-matterhorn-switzerland-shutterstock_1298208013_44fea015e5.jpeg")
                    .image2("https://deih43ym53wif.cloudfront.net/zermatt-matterhorn-switzerland-shutterstock_1298208013_44fea015e5.jpeg")
                    .build();

            destinationService.createNewDestination(switzerland);

        }


    }

}

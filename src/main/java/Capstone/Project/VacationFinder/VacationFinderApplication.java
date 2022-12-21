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

        if (!itineraryRepository.existsById(1L)) {
            Itinerary itinerary = Itinerary.builder()
                    .item1("dfas")
                    .item2("fdas")
                    .item3("dfsa")
                    .build();

            itineraryService.createNewItinerary(itinerary);
        }

        if (!checklistRepository.existsById(1L)) {
            Checklist checklist = Checklist.builder()
                    .item1("fdas")
                    .item2("fdas")
                    .item3("asdf")
                    .build();

            checklistService.createNewChecklist(checklist);
        }


        if (!tripRepository.existsById(1L)) {
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

            Destination destination = Destination.builder()
                    .name("Peru")
                    .weather("Cold")
                    .scenery("Mountains")
                    .activity1("Mountain Tours")
                    .activity2("Bike riding")
                    .activity3("Riding Llamas")
                    .image1("https://www.planetware.com/wpimages/2019/12/peru-in-pictures-best-places-to-photograph-machu-picchu-llama.jpg")
                    .image2("https://ontheworldmap.com/bahamas/the-islands-of-the-bahamas-map.jpg")
                    .build();

            ArrayList<Destination> list1 = new ArrayList<>(Arrays.asList(destination));

            Set<Destination> set = new HashSet<>(list1);

            Trip trip = Trip.builder()
                    .itinerary(itinerary)
                    .checklist(checklist)
                    .destinations(set)
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

            Destination destination = Destination.builder()
                    .name("Brazil")
                    .weather("Warm")
                    .scenery("Beach")
                    .activity1("dsaf")
                    .activity2("fd")
                    .activity3("fdsa")
                    .image1("https://www.planetware.com/wpimages/2020/02/brazil-in-pictures-beautiful-places-to-photograph-botafogo-bay.jpg")
                    .image2("https://static.wikia.nocookie.net/rio/images/1/12/1_brazil.gif/revision/latest?cb=20110411190105")
                    .build();

            ArrayList<Destination> list1 = new ArrayList<>(Arrays.asList(destination));
            Set<Destination> set = new HashSet<>(list1);

            Trip trip = Trip.builder()
                    .itinerary(itinerary)
                    .checklist(checklist)
                    .destinations(set)
                    .build();

            ArrayList<Trip> list2 = new ArrayList<>(Arrays.asList(trip));
            Set<Trip> set2 = new HashSet<>(list2);

            User guest = User.builder()
                    .firstName("Robin")
                    .lastName("Smith")
                    .email("smithrobin@gmail.com")
                    .phone("1245684578")
                    .username("Robin41")
                    .password("password")
                    .trips(set2)
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
                    .enabled(true)
                    .locked(false)
                    .build();

            userService.createNewUser(admin);

        }

        if (!destinationRepository.existsByName("Bahamas")) {
            Destination bahamas = Destination.builder()
                    .name("Bahamas")
                    .weather("warm")
                    .scenery("Sunny")
                    .activity1("Swimming")
                    .activity2("Scuba Diving")
                    .activity3("Sport Fishing")
                    .image1("https://cdn.britannica.com/14/94514-050-461B9A6D/Palm-trees-ocean-Bahamas-New-Providence-Island.jpg")
                    .image2("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRrnLKJGVO40ijQvXp4XXaShoJRvob75N8ghSAFwsox3aM7Z4TJUeYXr8pVeTuZ7QJrDco&usqp=CAU")
                    .build();

            destinationService.createNewDestination(bahamas);
        }

        if (!destinationRepository.existsByName("Switzerland")) {
            Destination switzerland = Destination.builder()
                    .name("Switzerland")
                    .weather("Cold")
                    .scenery("Snow")
                    .activity1("Skiing")
                    .activity2("Christmas Markets")
                    .activity3("Mountain Tours")
                    .image1("https://deih43ym53wif.cloudfront.net/zermatt-matterhorn-switzerland-shutterstock_1298208013_44fea015e5.jpeg")
                    .image2("https://c8.alamy.com/comp/JF64PT/alps-switzerland-zurich-swiss-map-atlas-map-of-the-world-bern-rhine-JF64PT.jpg")
                    .build();

            destinationService.createNewDestination(switzerland);

        }


    }

}

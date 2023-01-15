package Capstone.Project.VacationFinder;

import Capstone.Project.VacationFinder.models.*;
import Capstone.Project.VacationFinder.repositories.*;
import Capstone.Project.VacationFinder.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.List;
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
                    .item1("Day 1")
                    .item2("Day 2")
                    .item3("Day 3")
                    .build();

            itineraryService.createNewItinerary(itinerary);
        }

        if (!checklistRepository.existsById(1L)) {
            Checklist checklist = Checklist.builder()
                    .checklistItems(List.of("123","4568", "486132"))
                    .build();

            checklistService.createNewChecklist(checklist);
        }


        if (!tripRepository.findByName("Mongolia")) {
            Itinerary itinerary = Itinerary.builder()
                    .item1("AEUEKD")
                    .item2("HJKLD")
                    .item3("SHKJ")
                    .build();

            Checklist checklist = Checklist.builder()
                    .checklistItems(List.of("adf","hgdg", "glil", "jfdak;sji"))
                    .build();

            Destination mongolia = Destination.builder()
                    .name("Mongolia")
                    .weather("Cold")
                    .scenery("Mountains")
                    .activity1("Hiking")
                    .activity2("Mountain Tours")
                    .activity3("Horse Riding")
                    .city1("Ulaanbaatar")
                    .city2("Uliastai")
                    .city3("Olgii")
                    .city4("Erdenet")
                    .city5("Dalanzadgad")
                    .image1("https://www.worldtravelguide.net/wp-content/uploads/2012/05/shu-Mongolia-600892928-1440x823.jpg")
                    .image2("https://www.mappr.co/wp-content/uploads/2021/11/mongolia-map-1024x586.jpg")
                    .build();
            destinationService.createNewDestination(mongolia);

            Set<Destination> destinations = new HashSet();
            destinations.add(mongolia);

            Trip mongoliaTrip = Trip.builder()
                    .name("Mongolia Trip")
                    .itinerary(itinerary)
                    .checklist(checklist)
                    .destinations(destinations)
                    .build();

            tripService.createNewTrip(mongoliaTrip);
        }

        if (!tripRepository.findByName("Bahamas")) {
            Itinerary itinerary = Itinerary.builder()
                    .item1("Red")
                    .item2("Orange")
                    .item3("Blue")
                    .build();
            itineraryService.createNewItinerary(itinerary);

            Checklist checklist = Checklist.builder()
                    .checklistItems(List.of("fdsafda","4568"))
                    .build();
            checklistService.createNewChecklist(checklist);

            Destination bahamas = Destination.builder()
                    .name("Bahamas")
                    .weather("Warm")
                    .scenery("Beach")
                    .activity1("Swimming")
                    .activity2("Scuba Diving")
                    .activity3("Sport Fishing")
                    .city1("Nassau")
                    .city2("Paradise Island")
                    .city3("Arawak Cay")
                    .city4("Freeport")
                    .city5("Alice Town")
                    .image1("https://cdn.britannica.com/14/94514-050-461B9A6D/Palm-trees-ocean-Bahamas-New-Providence-Island.jpg")
                    .image2("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRrnLKJGVO40ijQvXp4XXaShoJRvob75N8ghSAFwsox3aM7Z4TJUeYXr8pVeTuZ7QJrDco&usqp=CAU")
                    .build();
            destinationService.createNewDestination(bahamas);

            Set<Destination> destinations = new HashSet();
            destinations.add(bahamas);

            Trip bahamasTrip = Trip.builder()
                    .name("Bahamas Trip")
                    .itinerary(itinerary)
                    .checklist(checklist)
                    .destinations(destinations)
                    .build();

            tripService.createNewTrip(bahamasTrip);
        }

        if (!tripRepository.findByName("Switzerland")) {
            Itinerary itinerary = Itinerary.builder()
                    .item1("431")
                    .item2("413")
                    .item3("4134")
                    .build();
            itineraryService.createNewItinerary(itinerary);

            Checklist checklist = Checklist.builder()
                    .checklistItems(List.of("876","45JKHI68"))
                    .build();
            checklistService.createNewChecklist(checklist);

            Destination switzerland = Destination.builder()
                    .name("Switzerland")
                    .weather("Cold")
                    .scenery("Snow")
                    .activity1("Skiing")
                    .activity2("Christmas Markets")
                    .activity3("Mountain Tours")
                    .city1("Zürich")
                    .city2("Lucerne")
                    .city3("Bern")
                    .city4("Interlaken")
                    .city5("Zermatt")
                    .image1("https://deih43ym53wif.cloudfront.net/zermatt-matterhorn-switzerland-shutterstock_1298208013_44fea015e5.jpeg")
                    .image2("https://c8.alamy.com/comp/JF64PT/alps-switzerland-zurich-swiss-map-atlas-map-of-the-world-bern-rhine-JF64PT.jpg")
                    .build();
            destinationService.createNewDestination(switzerland);

            Set<Destination> destinations = new HashSet();
            destinations.add(switzerland);

            Trip switzerlandTrip = Trip.builder()
                    .name("Switzerland Trip")
                    .itinerary(itinerary)
                    .checklist(checklist)
                    .destinations(destinations)
                    .build();

            tripService.createNewTrip(switzerlandTrip);
        }

        if (!tripRepository.findByName("Brazil")) {
            Itinerary itinerary = Itinerary.builder()
                    .item1("JKLDF")
                    .item2("KLDFS")
                    .item3("JKDFLS")
                    .build();
            itineraryService.createNewItinerary(itinerary);

            Checklist checklist = Checklist.builder()
                    .checklistItems(List.of("699","45JKHI68"))
                    .build();
            checklistService.createNewChecklist(checklist);

            Destination brazil = Destination.builder()
                    .name("Brazil")
                    .weather("Warm")
                    .scenery("Beach")
                    .activity1("Swimming")
                    .activity2("Snorkeling")
                    .activity3("Parasailing")
                    .city1("Rio de Janeiro")
                    .city2("Florianopolis")
                    .city3("Salvador")
                    .city4("São Paulo")
                    .city5("Foz do Iguaçu")
                    .image1("https://www.planetware.com/wpimages/2020/02/brazil-in-pictures-beautiful-places-to-photograph-botafogo-bay.jpg")
                    .image2("https://static.wikia.nocookie.net/rio/images/1/12/1_brazil.gif/revision/latest?cb=20110411190105")
                    .build();
            destinationService.createNewDestination(brazil);

            Set<Destination> destinations = new HashSet();
            destinations.add(brazil);

            Trip brazilTrip = Trip.builder()
                    .name("Brazil Trip")
                    .itinerary(itinerary)
                    .checklist(checklist)
                    .destinations(destinations)
                    .build();

            tripService.createNewTrip(brazilTrip);
        }

        if (!tripRepository.findByName("Peru")) {
            Itinerary itinerary = Itinerary.builder()
                    .item1("JKLDF")
                    .item2("KLDFS")
                    .item3("JKDFLS")
                    .build();
            itineraryService.createNewItinerary(itinerary);

            Checklist checklist = Checklist.builder()
                    .checklistItems(List.of("699","45JKHI68"))
                    .build();
            checklistService.createNewChecklist(checklist);

            Destination peru = Destination.builder()
                    .name("Peru")
                    .weather("Cold")
                    .scenery("Mountains")
                    .activity1("Mountain Tours")
                    .activity2("Bike riding")
                    .activity3("Riding Llamas")
                    .city1("Cuzco")
                    .city2("Lima")
                    .city3("Arequipa")
                    .city4("Ayacucho")
                    .city5("Iquitos")
                    .image1("https://www.planetware.com/wpimages/2019/12/peru-in-pictures-best-places-to-photograph-machu-picchu-llama.jpg")
                    .image2("https://www.tripsavvy.com/thmb/uEReEir7LcDkySAMkkl4gqMMUPk=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/GettyImages-498613571-eb1b37fc20ad4152892951bb8ea3f3e1.jpg")
                    .build();
            destinationService.createNewDestination(peru);

            Set<Destination> destinations = new HashSet();
            destinations.add(peru);

            Trip peruTrip = Trip.builder()
                    .name("Peru Trip")
                    .itinerary(itinerary)
                    .checklist(checklist)
                    .destinations(destinations)
                    .build();

            tripService.createNewTrip(peruTrip);
        }

        if (!userRepository.existsByUsername("Robin41")) {
        User guest = User.builder()
                .id(1L)
                .firstName("Robin")
                .lastName("Smith")
                .email("smithrobin@gmail.com")
                .phone("1245684578")
                .username("Robin41")
                .password("password")
                .role(Role.GUEST)
                .trips(new HashSet<>())
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
                    .password("password")
                    .role(Role.USER)
                    .enabled(true)
                    .locked(false)
                    .build();

            userService.createNewUser(user);
        }

        if (!userRepository.existsByUsername("kyle")) {
            User admin = User.builder()
                    .firstName("Kyle")
                    .lastName("Jackson")
                    .email("kylejackson@yahoo.com")
                    .phone("8567487596")
                    .username("kyle")
                    .password("password")
                    .role(Role.ADMIN)
                    .enabled(true)
                    .locked(false)
                    .build();

            userService.createNewUser(admin);
        }
    }

}

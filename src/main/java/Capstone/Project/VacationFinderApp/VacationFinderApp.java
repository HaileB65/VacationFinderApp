package Capstone.Project.VacationFinderApp;

import Capstone.Project.VacationFinderApp.models.*;
import Capstone.Project.VacationFinderApp.repositories.TripRepository;
import Capstone.Project.VacationFinderApp.repositories.UserRepository;
import Capstone.Project.VacationFinderApp.services.TripService;
import Capstone.Project.VacationFinderApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SpringBootApplication
@EnableCaching
public class VacationFinderApp implements CommandLineRunner {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TripService tripService;

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(VacationFinderApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /**
         * Creates Mongolia Trip and destination. Includes prefilled itinerary and checklist.
         *
         */
        if (!tripRepository.existsByName("Mongolia")) {
            Itinerary itinerary = Itinerary.builder()
                    .location1("Weston Hotel")
                    .location2("Atrium Event Center")
                    .location3("The Axewell")
                    .location4("The Arboretum")
                    .location5("Flight Museum")
                    .meal1("Ephesus Mediterranean Grill")
                    .meal2("True Food Kitchen")
                    .meal3("Hawkers Asian Cuisine")
                    .meal4("TJ's Seafood Market & Grill")
                    .meal5("Elm & Good")
                    .leisure1("Jazz Festival")
                    .leisure2("Comedy Club")
                    .leisure3("Soccer Game")
                    .leisure4("Jack's Bar")
                    .leisure5("Klyde Warren Park")
                    .transport1("Hotel Cab")
                    .transport2("Hotel Cab")
                    .transport3("Hotel Cab")
                    .transport4("Hotel Cab")
                    .transport5("Hotel Cab")
                    .build();

            Destination mongolia = Destination.builder()
                    .name("Mongolia")
                    .weather("Cold")
                    .scenery("Mountains")
                    .activity1("Mountain Tours")
                    .activity2("Hiking")
                    .activity3("Horse Riding")
                    .city1("Ulaanbaatar")
                    .city2("Uliastai")
                    .city3("Olgii")
                    .city4("Erdenet")
                    .city5("Dalanzadgad")
                    .image1("https://www.worldtravelguide.net/wp-content/uploads/2012/05/shu-Mongolia-600892928-1440x823.jpg")
                    .image2("https://www.mappr.co/wp-content/uploads/2021/11/mongolia-map-1024x586.jpg")
                    .trips(new HashSet<>())
                    .build();

            Set<Destination> destinations = new HashSet();
            destinations.add(mongolia);

            Trip mongoliaTrip = Trip.builder()
                    .name("Mongolia")
                    .itinerary(itinerary)
                    .destinations(destinations)
                    .build();

            tripService.saveTrip(mongoliaTrip);
        }

        /**
         * Creates Bahamas Trip and destination. Includes prefilled itinerary and checklist.
         *
         */
        if (!tripRepository.existsByName("Bahamas")) {
            Itinerary itinerary = Itinerary.builder()
                    .location1("Weston Hotel")
                    .location2("Atrium Event Center")
                    .location3("The Axewell")
                    .location4("The Arboretum")
                    .location5("Flight Museum")
                    .meal1("Ephesus Mediterranean Grill")
                    .meal2("True Food Kitchen")
                    .meal3("Hawkers Asian Cuisine")
                    .meal4("TJ's Seafood Market & Grill")
                    .meal5("Elm & Good")
                    .leisure1("Jazz Festival")
                    .leisure2("Comedy Club")
                    .leisure3("Soccer Game")
                    .leisure4("Jack's Bar")
                    .leisure5("Klyde Warren Park")
                    .transport1("Hotel Cab")
                    .transport2("Hotel Cab")
                    .transport3("Hotel Cab")
                    .transport4("Hotel Cab")
                    .transport5("Hotel Cab")
                    .build();

            Destination bahamas = Destination.builder()
                    .name("Bahamas")
                    .weather("Warm")
                    .scenery("Beach")
                    .activity1("Scuba Diving")
                    .activity2("Swimming")
                    .activity3("Sport Fishing")
                    .city1("Nassau")
                    .city2("Paradise Island")
                    .city3("Arawak Cay")
                    .city4("Freeport")
                    .city5("Alice Town")
                    .image1("https://cdn.britannica.com/14/94514-050-461B9A6D/Palm-trees-ocean-Bahamas-New-Providence-Island.jpg")
                    .image2("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/Taino_Beach%2C_Grand_Bahama_Island%2C_Bahamas.jpg/1280px-Taino_Beach%2C_Grand_Bahama_Island%2C_Bahamas.jpg")
                    .build();

            Set<Destination> destinations = new HashSet();
            destinations.add(bahamas);

            Trip bahamasTrip = Trip.builder()
                    .name("Bahamas")
                    .itinerary(itinerary)
                    .destinations(destinations)
                    .build();

            tripService.saveTrip(bahamasTrip);
        }

        /**
         * Creates Switzerland Trip and destination. Includes prefilled itinerary and checklist.
         *
         */
        if (!tripRepository.existsByName("Switzerland")) {
            Itinerary itinerary = Itinerary.builder()
                    .location1("Weston Hotel")
                    .location2("Atrium Event Center")
                    .location3("The Axewell")
                    .location4("The Arboretum")
                    .location5("Flight Museum")
                    .meal1("Ephesus Mediterranean Grill")
                    .meal2("True Food Kitchen")
                    .meal3("Hawkers Asian Cuisine")
                    .meal4("TJ's Seafood Market & Grill")
                    .meal5("Elm & Good")
                    .leisure1("Jazz Festival")
                    .leisure2("Comedy Club")
                    .leisure3("Soccer Game")
                    .leisure4("Jack's Bar")
                    .leisure5("Klyde Warren Park")
                    .transport1("Hotel Cab")
                    .transport2("Hotel Cab")
                    .transport3("Hotel Cab")
                    .transport4("Hotel Cab")
                    .transport5("Hotel Cab")
                    .build();

            Destination switzerland = Destination.builder()
                    .name("Switzerland")
                    .weather("Cold")
                    .scenery("Snow")
                    .activity1("Christmas Markets")
                    .activity2("Skiing")
                    .activity3("Mountain Tours")
                    .city1("Zürich")
                    .city2("Lucerne")
                    .city3("Bern")
                    .city4("Interlaken")
                    .city5("Zermatt")
                    .image1("https://deih43ym53wif.cloudfront.net/zermatt-matterhorn-switzerland-shutterstock_1298208013_44fea015e5.jpeg")
                    .image2("https://c8.alamy.com/comp/JF64PT/alps-switzerland-zurich-swiss-map-atlas-map-of-the-world-bern-rhine-JF64PT.jpg")
                    .build();

            Set<Destination> destinations = new HashSet();
            destinations.add(switzerland);

            Trip switzerlandTrip = Trip.builder()
                    .name("Switzerland")
                    .itinerary(itinerary)
                    .destinations(destinations)
                    .build();

            tripService.saveTrip(switzerlandTrip);
        }

        /**
         * Creates Brazil Trip and destination. Includes prefilled itinerary and checklist.
         *
         */
        if (!tripRepository.existsByName("Brazil")) {
            Itinerary itinerary = Itinerary.builder()
                    .location1("Weston Hotel")
                    .location2("Atrium Event Center")
                    .location3("The Axewell")
                    .location4("The Arboretum")
                    .location5("Flight Museum")
                    .meal1("Ephesus Mediterranean Grill")
                    .meal2("True Food Kitchen")
                    .meal3("Hawkers Asian Cuisine")
                    .meal4("TJ's Seafood Market & Grill")
                    .meal5("Elm & Good")
                    .leisure1("Jazz Festival")
                    .leisure2("Comedy Club")
                    .leisure3("Soccer Game")
                    .leisure4("Jack's Bar")
                    .leisure5("Klyde Warren Park")
                    .transport1("Hotel Cab")
                    .transport2("Hotel Cab")
                    .transport3("Hotel Cab")
                    .transport4("Hotel Cab")
                    .transport5("Hotel Cab")
                    .build();

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
                    .image2("https://www.planetware.com/wpimages/2020/02/brazil-in-pictures-beautiful-places-to-photograph-sugarloaf-mountain.jpg")
                    .build();

            Set<Destination> destinations = new HashSet();
            destinations.add(brazil);

            Trip brazilTrip = Trip.builder()
                    .name("Brazil")
                    .itinerary(itinerary)
                    .destinations(destinations)
                    .build();

            tripService.saveTrip(brazilTrip);
        }

        /**
         * Creates Peru Trip and destination. Includes prefilled itinerary and checklist.
         *
         */
        if (!tripRepository.existsByName("Peru")) {
            Itinerary itinerary = Itinerary.builder()
                    .location1("Weston Hotel")
                    .location2("Atrium Event Center")
                    .location3("The Axewell")
                    .location4("The Arboretum")
                    .location5("Flight Museum")
                    .meal1("Ephesus Mediterranean Grill")
                    .meal2("True Food Kitchen")
                    .meal3("Hawkers Asian Cuisine")
                    .meal4("TJ's Seafood Market & Grill")
                    .meal5("Elm & Good")
                    .leisure1("Jazz Festival")
                    .leisure2("Comedy Club")
                    .leisure3("Soccer Game")
                    .leisure4("Jack's Bar")
                    .leisure5("Klyde Warren Park")
                    .transport1("Hotel Cab")
                    .transport2("Hotel Cab")
                    .transport3("Hotel Cab")
                    .transport4("Hotel Cab")
                    .transport5("Hotel Cab")
                    .build();

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

            Set<Destination> destinations = new HashSet();
            destinations.add(peru);

            Trip peruTrip = Trip.builder()
                    .name("Peru")
                    .itinerary(itinerary)
                    .destinations(destinations)
                    .build();

            tripService.saveTrip(peruTrip);
        }

        /**
         * Creates default guest user.
         *
         */
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

        /**
         * Creates default user.
         *
         */
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

        /**
         * Creates default admin user.
         *
         */
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

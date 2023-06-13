package Capstone.Project.VacationFinderApp;

import Capstone.Project.VacationFinderApp.models.*;
import Capstone.Project.VacationFinderApp.models.weatherAPI.WeatherForecast;
import Capstone.Project.VacationFinderApp.repositories.TripRepository;
import Capstone.Project.VacationFinderApp.repositories.UserRepository;
import Capstone.Project.VacationFinderApp.services.CountryFactsAPIService;
import Capstone.Project.VacationFinderApp.services.TripService;
import Capstone.Project.VacationFinderApp.services.UserService;
import Capstone.Project.VacationFinderApp.services.WeatherAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
@EnableCaching
public class VacationFinderApp extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TripService tripService;

    @Autowired
    UserService userService;

    @Autowired
    WeatherAPIService weatherAPIService;

    @Autowired
    CountryFactsAPIService countryFactsAPIService;

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
                    .description("Mongolia's Gobi desert is a must see for its iconic natural formations, dinosaur fossils, wildlife, birds and camel herding nomads. Mongolia is a unique place were you can experience vast, untouched landscapes and learn about nomadic culture.")
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
                    .image2("https://www.toursmongolia.com/uploads/Why_Mongolia_Is_Simply_Awesome.jpg")
                    .image3("https://worldexpeditions.com/croppedImages/Asia/Mongolia/camel-crossing-Mongolia-988307-500px.jpg")
                    .image4("https://www.toursmongolia.com/uploads/ulaanbaatar_mongolia.JPG")
                    .image5("https://encrypted-tbn3.gstatic.com/licensed-image?q=tbn:ANd9GcS5o6XkKgvqzZBJ_ANjo7umg45RIbB-UUL9PpVLjguAvxXh1GO9g__9q7K2OK5nGuZlwNe8Mjo1DJMV3HBXS795C4LMVw")
                    .image6("https://www.toursmongolia.com/uploads/Mongolia_Ulaanbaatar_city_tours.jpg")
                    .trips(new HashSet<>())
                    .build();

            Set<Destination> destinations = new HashSet();
            destinations.add(mongolia);

            WeatherForecast forecast = WeatherForecast.builder()
                    .forecastImageUrl("yuturuy")
                    .build();

            Trip mongoliaTrip = Trip.builder()
                    .name("Mongolia")
                    .city(mongolia.getCity1())
                    .country("Mongolia")
                    .itinerary(itinerary)
                    .destinations(destinations)
                    .weatherForecast(forecast)
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
                    .description("The Bahamas is home to beautiful hotels, a vibrant culture, pristine beaches, and watersport activities. Amazing diving and delicious food are just two of all the things to do at this natural paradise.")
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
                    .image3("https://www.sandals.com/blog/content/images/2020/01/Blue-Lagoon-Bahamas.jpg")
                    .image4("https://images.unsplash.com/photo-1548574505-5e239809ee19?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8dGhlJTIwYmFoYW1hc3xlbnwwfHwwfHw%3D&w=1000&q=80")
                    .image5("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTH0WG1PvfbpmQtzno8K_dbou8hfrXyBa4tbA&usqp=CAU")
                    .image6("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSgCVx3X-wSuHtOTll9zr3U4kD_kkZfjeiu0Q&usqp=CAU")
                    .build();

            Set<Destination> destinations = new HashSet();
            destinations.add(bahamas);

            WeatherForecast forecast = WeatherForecast.builder()
                    .forecastImageUrl("yuturuy")
                    .build();

            Trip bahamasTrip = Trip.builder()
                    .name("Bahamas")
                    .city(bahamas.getCity1())
                    .country("Bahamas")
                    .itinerary(itinerary)
                    .destinations(destinations)
                    .weatherForecast(forecast)
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
                    .description("Switzerland has stunning nature, rich culture, exceptional architecture from medieval buildings, top-notch museums, and delicious cuisine. Winter is a great time to witness Switzerland's beautiful landscape and to learn to ski.")
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
                    .image3("https://images.unsplash.com/photo-1530122037265-a5f1f91d3b99?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8c3dpdHplcmxhbmR8ZW58MHx8MHx8&w=1000&q=80")
                    .build();

            Set<Destination> destinations = new HashSet();
            destinations.add(switzerland);

            WeatherForecast forecast = WeatherForecast.builder()
                    .forecastImageUrl("yuturuy")
                    .build();

            Trip switzerlandTrip = Trip.builder()
                    .name("Switzerland")
                    .city(switzerland.getCity1())
                    .country("Switzerland")
                    .itinerary(itinerary)
                    .destinations(destinations)
                    .weatherForecast(forecast)
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
                    .description("Brazil is filled with adventure from dazzling beaches, tropical islands and picturesque colonial towns. Its verdant rainforests boast an astounding array of wildlife, while its wildly energetic cities are home to great cuisine.")
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

            WeatherForecast forecast = WeatherForecast.builder()
                    .forecastImageUrl("yuturuy")
                    .build();

            Trip brazilTrip = Trip.builder()
                    .name("Brazil")
                    .city(brazil.getCity1())
                    .country("Brazil")
                    .itinerary(itinerary)
                    .destinations(destinations)
                    .weatherForecast(forecast)
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
                    .description("Peru is a veritable treasure trove of unforgettable places to visit and things to do from Vertiginous Andean mountain peaks, breath-taking Incan archaeological sites like Machu Picchu, lush jungle national parks, and colorful indigenous communities.")
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

            WeatherForecast forecast = WeatherForecast.builder()
                    .forecastImageUrl("yuturuy")
                    .build();

            Trip peruTrip = Trip.builder()
                    .name("Peru")
                    .city(peru.getCity1())
                    .country("Peru")
                    .itinerary(itinerary)
                    .destinations(destinations)
                    .weatherForecast(forecast)
                    .build();

            tripService.saveTrip(peruTrip);

        }

        if (!tripRepository.existsByName("Italy")) {
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

            Destination italy = Destination.builder()
                    .name("Italy")
                    .description("Italy has a vast wealth of culture, architecture and ancient sites. It has modern cities that embody contemporary Italy, and don't forget Italy's gifts to the world's taste buds, its wonderful cuisine and wines.")
                    .weather("Warm")
                    .scenery("Sunny")
                    .activity1("Cultural Tours")
                    .activity2("Churches & Cathedrals")
                    .activity3("Wine Tastings")
                    .city1("Rome")
                    .city2("")
                    .city3("")
                    .city4("")
                    .city5("")
                    .image1("https://www.thecolosseum.org/wp-content/uploads/2017/09/Colosseum-optimized.jpg")
                    .image2("https://fullsuitcase.com/wp-content/uploads/2022/01/Best-views-and-viewpoints-in-Rome-Italy.jpg.webp")
                    .image3("https://lp-cms-production.imgix.net/2021-03/500pxRF_77415821.jpg")
                    .image4("https://www.thediaryofanomad.com/wp-content/uploads/2020/11/rome-for-3-days-in-rome-itinerary-vatican-dome-view.jpg")
                    .image5("https://www.travelandleisure.com/thmb/QDUywna6SQbiQte-ZmrJmXcywp0=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/rome-italy-lead-ROMETG0521-7bd455d3c2b545219498215df7143e0d.jpg")
                    .image6("https://media.cntraveler.com/photos/5a903dd660543c4ae96c2e74/16:9/w_2560%2Cc_limit/GettyImages-497268617.jpg")
                    .build();

            Set<Destination> destinations = new HashSet();
            destinations.add(italy);

            WeatherForecast forecast = WeatherForecast.builder()
                    .forecastImageUrl("yuturuy")
                    .build();

            Trip italyTrip = Trip.builder()
                    .name("Italy")
                    .city(italy.getCity1())
                    .country("Italy")
                    .itinerary(itinerary)
                    .destinations(destinations)
                    .weatherForecast(forecast)
                    .build();

            tripService.saveTrip(italyTrip);

        }

        /**
         * Creates default user.
         *
         */
        if (!userRepository.existsByUsername("Robin411")) {
            User user = User.builder()
                    .id(1L)
                    .firstName("Robin")
                    .lastName("Smith")
                    .email("smithrobin@gmail.com")
                    .phone("124568457")
                    .username("Robin411")
                    .password("password")
                    .role(Role.USER)
                    .enabled(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .accountNonLocked(true)
                    .trips(new HashSet<>())
                    .build();

            userService.createNewUser(user);
        }

        /**
         * Creates default user.
         *
         */
        if (!userRepository.existsByUsername("Alex4555")) {
            User user = User.builder()
                    .firstName("Alex")
                    .lastName("Walker")
                    .email("alexwalker@yahoo.com")
                    .phone("1245874568")
                    .username("Alex4555")
                    .password("password")
                    .role(Role.USER)
                    .enabled(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .accountNonLocked(true)
                    .build();

            userService.createNewUser(user);
        }

        /**
         * Creates default admin user.
         *
         */
        if (!userRepository.existsByUsername("kyle5555")) {
            User admin = User.builder()
                    .firstName("Kyle")
                    .lastName("Jackson")
                    .email("kylejackson@yahoo.com")
                    .phone("8567487596")
                    .username("kyle5555")
                    .password("password")
                    .role(Role.ADMIN)
                    .enabled(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .accountNonLocked(true)
                    .build();

            userService.createNewUser(admin);
        }

        weatherAPIService.getAllWeatherForecasts();

    }
}
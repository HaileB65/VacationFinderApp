package Capstone.Project.VacationFinderApp.services;

import Capstone.Project.VacationFinderApp.models.Itinerary;
import Capstone.Project.VacationFinderApp.models.Trip;
import Capstone.Project.VacationFinderApp.models.weatherAPI.WeatherForecast;
import Capstone.Project.VacationFinderApp.models.weatherAPI.WeatherLocation;
import Capstone.Project.VacationFinderApp.repositories.WeatherForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class WeatherAPIService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TripService tripService;

    @Autowired
    WeatherForecastRepository weatherForecastRepository;

    public String postNewForecast(String city, String country) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-rapidapi-forward-key", "b61a5a7435msh866977d946919afp1c7620jsn64158120fd4f");
        headers.add("X-RapidAPI-Key", "b61a5a7435msh866977d946919afp1c7620jsn64158120fd4f");
        headers.add("X-RapidAPI-Host", "weather-embed.p.rapidapi.com");

        WeatherLocation weatherLocation = new WeatherLocation(city, country);

        HttpEntity<WeatherLocation> request = new HttpEntity<>(weatherLocation, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity("https://weather-embed.p.rapidapi.com/forecast/create", request, Map.class);

        return (String) response.getBody().get("url");
    }

    //TODO
    // Create getAllWeatherForecasts method
    public void getAllWeatherForecasts() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        System.out.println(dtf.format(date));

        //if date is not today's date run method
        //
        List<Trip> trips = tripService.getAllTrips();

        for (Trip trip : trips) {
//                WeatherForecast forecast = trip.getWeatherForecasts().;
//                trip.setWeatherForecastUrl(weatherAPI.postNewForecast(trip.getCity(), trip.getCountry()));
        }
    }

    public WeatherForecast saveWeatherForecast(WeatherForecast weatherForecast) {

        weatherForecastRepository.save(weatherForecast);

        return weatherForecast;
    }
}
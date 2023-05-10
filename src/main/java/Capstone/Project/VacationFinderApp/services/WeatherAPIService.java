package Capstone.Project.VacationFinderApp.services;

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

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public String getForecastImageUrl(String city, String country) {

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
    public void getAllWeatherForecasts() throws Exception {

        LocalDate date = LocalDate.now();
//
        Trip peruTrip = tripService.getByName("Peru");
        LocalDateTime localDateTime = peruTrip.weatherForecast.timestamp.toLocalDateTime();
        LocalDate forecastTimestamp = localDateTime.toLocalDate();

//        if(forecastTimestamp.compareTo(findPrevDay(date)) < 0) {
//            peruTrip.getWeatherForecast().setForecastImageUrl("1235465");
//        weatherAPIService.getForecastImageUrl(peruTrip.getCity(), peruTrip.getCountry())
//            tripService.saveTrip(peruTrip);
//        }

//        List<Trip> trips = tripService.getAllTrips();
//        for (Trip trip : trips) {
//            LocalDateTime localDateTime1 = trip.weatherForecast.timestamp.toLocalDateTime();
//            LocalDate forecastDate = localDateTime1.toLocalDate();
//
//            if(forecastDate.compareTo(findPrevDay(date)) < 0) {
//                trip.getWeatherForecast().setForecastImageUrl(weatherAPIService.getForecastImageUrl(trip.getCity(), trip.getCountry()));
//                tripService.saveTrip(trip);
//            }
//        }
    }

    public WeatherForecast saveWeatherForecast(WeatherForecast weatherForecast) {

        weatherForecastRepository.save(weatherForecast);

        return weatherForecast;
    }

    private static LocalDate findPrevDay(LocalDate localdate)
    {
        return localdate.minusDays(1);
    }
}

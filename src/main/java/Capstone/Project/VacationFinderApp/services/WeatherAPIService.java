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

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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

    /**
     * Checks to see if all forecasts are up-to-date. If forecast is more than one day old
     * an updated forecast is requested from the Weather Embedded API. The Weather Embedded API
     * can be found at https://rapidapi.com/random-shapes-random-shapes-default/api/weather-embed.
     *
     * @throws Exception
     */
    public void getAllWeatherForecasts() throws Exception {

        LocalDate date = LocalDate.now();

        List<Trip> trips = tripService.getAllTrips();
        for (Trip trip : trips) {
            if (trip.weatherForecast != null) {
                LocalDateTime localDateTime = trip.weatherForecast.timestamp.toLocalDateTime();
                LocalDate forecastTimestamp = localDateTime.toLocalDate();

                if (forecastTimestamp.compareTo(findPrevDay(date)) < 0) {
                    trip.getWeatherForecast().setForecastImageUrl(getForecastImageUrl(trip.getCity(), trip.getCountry()));

                    Date date1 = new Date();
                    Timestamp todaysDate = new Timestamp(date1.getTime());
                    trip.getWeatherForecast().setTimestamp(todaysDate);

                    tripService.saveTrip(trip);
                }
            }
        }
    }

    public WeatherForecast saveWeatherForecast(WeatherForecast weatherForecast) {

        weatherForecastRepository.save(weatherForecast);

        return weatherForecast;
    }

    private static LocalDate findPrevDay(LocalDate localdate) {
        return localdate.minusDays(1);
    }
}

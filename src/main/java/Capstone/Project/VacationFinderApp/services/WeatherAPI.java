package Capstone.Project.VacationFinderApp.services;

import Capstone.Project.VacationFinderApp.models.WeatherLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class WeatherAPI {

    @Autowired
    RestTemplate restTemplate;

    //    @PostConstruct
    public String postNewForecast(String city, String country) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-rapidapi-forward-key", "${x-rapidapi-forward-key}");
        headers.add("X-RapidAPI-Key", "${X-RapidAPI-Key}");
        headers.add("X-RapidAPI-Host", "${X-RapidAPI-Host}");

        WeatherLocation weatherLocation = new WeatherLocation(city, country);

        HttpEntity<WeatherLocation> request = new HttpEntity<>(weatherLocation, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity("https://weather-embed.p.rapidapi.com/forecast/create", request, Map.class);

        if (response.getStatusCodeValue() == 200) {
            System.out.println("test");
            return (String) response.getBody().get("url");
        } else return null;


    }

}

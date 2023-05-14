package Capstone.Project.VacationFinderApp.services;

import Capstone.Project.VacationFinderApp.models.countryAPI.Country;
import Capstone.Project.VacationFinderApp.models.weatherAPI.WeatherLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class CountryFactsAPIService {

    @Autowired
    RestTemplate restTemplate;

    @PostConstruct
    public Country[] getCountryFacts() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Api-Key", "z0OKSRFSKCVkJe38WWPxGA==WF3dSUDfg23VTqva");

        HttpEntity<Void> request = new HttpEntity<>(headers);

        String url = "https://api.api-ninjas.com/v1/country?name=";
        String param = "France";

        ResponseEntity<Country[]> response = restTemplate.exchange(url, HttpMethod.GET, request, Country[].class, param);
        System.out.println("test");

        return response.getBody();
    }

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
}

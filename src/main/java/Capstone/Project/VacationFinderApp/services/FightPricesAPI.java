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
public class FightPricesAPI {

    @Autowired
    RestTemplate restTemplate;

    //TODO turn postman request into createNewSearch method below
    // turn JSON object into java object.

//    public String createNewSearch() {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("X-RapidAPI-Key", "b61a5a7435msh866977d946919afp1c7620jsn64158120fd4f");
//        headers.add("X-RapidAPI-Host", "skyscanner-api.p.rapidapi.com");
//
//        Query newsearch = new Query(object);
//
//        HttpEntity<WeatherLocation> request = new HttpEntity<>(weatherLocation, headers);
//
//        ResponseEntity<Map> response = restTemplate.postForEntity("https://skyscanner-api.p.rapidapi.com/v3/flights/live/search/create", request, Map.class);
//
//        if (response.getStatusCodeValue() == 200) {
//            System.out.println((String) response.getBody().get("url"));
//            System.out.println("test");
//            return (String) response.getBody().get("url");
//        } else return null;
//    }

}

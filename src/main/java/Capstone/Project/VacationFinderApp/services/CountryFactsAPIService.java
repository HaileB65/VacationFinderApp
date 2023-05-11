package Capstone.Project.VacationFinderApp.services;

import Capstone.Project.VacationFinderApp.models.countryAPI.CountryAPIResponse;
import Capstone.Project.VacationFinderApp.models.weatherAPI.WeatherLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryFactsAPIService {

    @Autowired
    RestTemplate restTemplate;

//    public CountryAPIResponse getCountryFacts() {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("X-Api-Key", "z0OKSRFSKCVkJe38WWPxGA==WF3dSUDfg23VTqva");
//        HttpEntity<String> request = new HttpEntity<>( headers);
//
//        ResponseEntity<CountryAPIResponse> response = restTemplate.postForEntity("https://api.api-ninjas.com/v1/country?name=France", headers, CountryAPIResponse.class);
//
//        return response.getBody();
//    }
}

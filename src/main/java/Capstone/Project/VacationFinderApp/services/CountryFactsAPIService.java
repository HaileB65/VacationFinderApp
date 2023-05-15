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

    /**
     * Gets facts about a country by searching the Country API from API Ninjas. The Country API can be found at
     * https://api-ninjas.com/api/country. Surface Area is in Km^2,
     *
     * @return returns an array of countries. Each country object contains facts about itself.
     */
    public Country[] getCountryFacts(String countryName) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Api-Key", "z0OKSRFSKCVkJe38WWPxGA==WF3dSUDfg23VTqva");

        HttpEntity<Void> request = new HttpEntity<>(headers);

        String url = "https://api.api-ninjas.com/v1/country?name=" + countryName;

        ResponseEntity<Country[]> response = restTemplate.exchange(url, HttpMethod.GET, request, Country[].class);
        System.out.println("test");

        return response.getBody();
    }

    public String getPopulationInMillions(Float countryPopulation) {
        String popInMillions = countryPopulation.toString();
        if(popInMillions.length() != 0) {
            for(int i = 0; i <= 4; i++) {
                popInMillions = popInMillions.substring(0, popInMillions.length() - 1);
            }
        }

        return popInMillions;
    }
}

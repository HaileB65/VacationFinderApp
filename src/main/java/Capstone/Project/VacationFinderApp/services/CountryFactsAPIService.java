package Capstone.Project.VacationFinderApp.services;

import Capstone.Project.VacationFinderApp.models.countryAPI.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

        return response.getBody();
    }

    /**
     * Turns country population from given units to millions.
     *
     * @param countryPopulation population of country in given unites.
     * @return return country population in millions.
     */
    public String getPopulationInMillions(Float countryPopulation) {
        String popInMillions = countryPopulation.toString();
        for (int i = 0; i <= 4; i++) {
            popInMillions = popInMillions.substring(0, popInMillions.length() - 1);
        }
        popInMillions = popInMillions + "M";

        return popInMillions;
    }

    public String getPopulationInThousands(Float countryPopulation) {
        String popInHundredThousands = countryPopulation.toString();
        for (int i = 0; i <= 1; i++) {
            popInHundredThousands = popInHundredThousands.substring(0, popInHundredThousands.length() - 1);
        }

        popInHundredThousands = popInHundredThousands + ",000";

        return popInHundredThousands;
    }

}

package Capstone.Project.VacationFinderApp.services;

import Capstone.Project.VacationFinderApp.models.skyscanner.flightsearch.*;
import Capstone.Project.VacationFinderApp.models.skyscanner.skyscannerresponse.SkyscannerResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;


@Service
public class SkyscannerAPIService {

    @Autowired
    RestTemplate restTemplate;

    public SkyscannerResponse createNewSearch() throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", "b61a5a7435msh866977d946919afp1c7620jsn64158120fd4f");
        headers.add("X-RapidAPI-Host", "skyscanner-api.p.rapidapi.com");
        headers.setContentType(MediaType.APPLICATION_JSON);

        OriginPlaceID originPlaceId = new OriginPlaceID();
        originPlaceId.setIata("DFW");

        DestinationPlaceId destinationPlaceId = new DestinationPlaceId();
        destinationPlaceId.setIata("DUB");

        Date date = new Date();
        date.setYear(2023);
        date.setMonth(4);
        date.setDay(20);

        QueryLeg queryLeg = new QueryLeg();
        queryLeg.setOriginPlaceId(originPlaceId);
        queryLeg.setDestinationPlaceId(destinationPlaceId);
        queryLeg.setDate(date);

        Query query = new Query();
        query.setMarket("UK");
        query.setLocale("en-GB");
        query.setCurrency("EUR");
        query.setQueryLegs(new QueryLeg[]{queryLeg});
        query.setCabinClass("CABIN_CLASS_ECONOMY");
        query.setAdults(2);
        query.setChildrenAges(new int[]{3, 9});
        
        FlightSearch flightSearch = new FlightSearch();
        flightSearch.setQuery(query);
                
        ObjectMapper objectMapper = new ObjectMapper();
        String flightSearchJsonStrObject = objectMapper.writeValueAsString(flightSearch);

        String url = "https://skyscanner-api.p.rapidapi.com/v3/flights/live/search/create";

        HttpEntity<String> request = new HttpEntity<>(flightSearchJsonStrObject, headers);
        ResponseEntity<SkyscannerResponse> response = restTemplate.postForEntity(url, request, SkyscannerResponse.class);

        return response.getBody();
    }

    public SkyscannerResponse pollSearch(String sessionToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", "b61a5a7435msh866977d946919afp1c7620jsn64158120fd4f");
        headers.add("X-RapidAPI-Host", "skyscanner-api.p.rapidapi.com");

        HttpEntity<String> request = new HttpEntity<>("parameters", headers);

        String token = sessionToken;
        String url = "https://skyscanner-api.p.rapidapi.com/v3/flights/live/search/poll/" + token;

        ResponseEntity<SkyscannerResponse> response = restTemplate.postForEntity(url, request, SkyscannerResponse.class);

        return response.getBody();
    }
}

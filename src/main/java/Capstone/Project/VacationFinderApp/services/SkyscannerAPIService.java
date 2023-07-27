package Capstone.Project.VacationFinderApp.services;

import Capstone.Project.VacationFinderApp.models.skyscannerAPI.carrierresponse.CarrierResponse;
import Capstone.Project.VacationFinderApp.models.skyscannerAPI.flightsearch.*;
import Capstone.Project.VacationFinderApp.models.skyscannerAPI.skyscannerresponse.SkyscannerResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;


@Service
public class SkyscannerAPIService {
    @Value("${rapidapi.skyscanner.api-key}")
    String skyscannerApiKey;

    @Autowired
    RestTemplate restTemplate;

    public SkyscannerResponse createNewSearch(String originPlaceId, String destinationPlaceId) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", skyscannerApiKey);
        headers.add("X-RapidAPI-Host", "skyscanner-api.p.rapidapi.com");
        headers.setContentType(MediaType.APPLICATION_JSON);

        OriginPlaceID origin = new OriginPlaceID();
        origin.setIata(originPlaceId);

        DestinationPlaceId destination = new DestinationPlaceId();
        destination.setIata(destinationPlaceId);

        Date date = new Date();
        date.setYear(2023);
        date.setMonth(4);
        date.setDay(20);

        QueryLeg queryLeg = new QueryLeg();
        queryLeg.setOriginPlaceId(origin);
        queryLeg.setDestinationPlaceId(destination);
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

        HttpEntity<String> request = new HttpEntity<>(flightSearchJsonStrObject, headers);

        String url = "https://skyscanner-api.p.rapidapi.com/v3/flights/live/search/create";

        ResponseEntity<SkyscannerResponse> response = restTemplate.postForEntity(url, request, SkyscannerResponse.class);

        return response.getBody();
    }

    public SkyscannerResponse pollSearch(String sessionToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", skyscannerApiKey);
        headers.add("X-RapidAPI-Host", "skyscanner-api.p.rapidapi.com");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>("parameters", headers);

        String token = sessionToken;
        String url = "https://skyscanner-api.p.rapidapi.com/v3/flights/live/search/poll/" + token;

        ResponseEntity<SkyscannerResponse> response = restTemplate.postForEntity(url, request, SkyscannerResponse.class);

        return response.getBody();
    }

    @PostConstruct
    public CarrierResponse getIataCodes() {

        String url = "https://skyscanner-api.p.rapidapi.com/v3/flights/carriers?rapidapi-key=" + skyscannerApiKey;

        CarrierResponse response = restTemplate.getForObject(url, CarrierResponse.class);

        return response;
    }
}

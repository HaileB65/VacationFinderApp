package Capstone.Project.VacationFinderApp.controllers;

import Capstone.Project.VacationFinderApp.models.skyscanner.flightsearch.FlightSearch;
import Capstone.Project.VacationFinderApp.models.skyscanner.skyscannerresponse.SkyscannerItinerary;
import Capstone.Project.VacationFinderApp.models.skyscanner.skyscannerresponse.SkyscannerResponse;
import Capstone.Project.VacationFinderApp.services.SkyscannerAPIService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class SkyscannerController {

    @Autowired
    SkyscannerAPIService skyscannerAPIService;

    @PostMapping("/searchFlightPrices/{originPlaceId}/{destinationPlaceId}")
    public String searchFlightPrices(@ModelAttribute("originPlaceId") String originPlaceId, @ModelAttribute("destinationPlaceId") String destinationPlaceId,Model model) throws JsonProcessingException {


        return "redirect:/myTrips";
    }
}

package Capstone.Project.VacationFinderApp.controllers;

import Capstone.Project.VacationFinderApp.models.skyscanner.flightsearch.Query;
import Capstone.Project.VacationFinderApp.models.skyscanner.skyscannerresponse.SkyscannerItinerary;
import Capstone.Project.VacationFinderApp.models.skyscanner.skyscannerresponse.SkyscannerResponse;
import Capstone.Project.VacationFinderApp.services.SkyscannerAPIService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class SkyscannerController {

    @Autowired
    SkyscannerAPIService skyscannerAPIService;

//    @PostMapping("/searchFlightPrices/{originPlaceId}/{destinationPlaceId}")
//    public SkyscannerResponse searchFlightPrices(@PathVariable("originPlaceId") String originPlaceId, @PathVariable("destinationPlaceId") String destinationPlaceId) throws JsonProcessingException {
//
//        SkyscannerResponse createSearchResponse = skyscannerAPIService.createNewSearch(originPlaceId, destinationPlaceId);
//
//
//        return createSearchResponse;
//    }
}

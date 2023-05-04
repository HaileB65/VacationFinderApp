package Capstone.Project.VacationFinderApp.controllers;

import Capstone.Project.VacationFinderApp.services.SkyscannerAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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

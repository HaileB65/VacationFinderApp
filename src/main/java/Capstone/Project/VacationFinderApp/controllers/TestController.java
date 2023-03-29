package Capstone.Project.VacationFinderApp.controllers;

import Capstone.Project.VacationFinderApp.models.skyscanner.flightsearch.FlightSearch;
import Capstone.Project.VacationFinderApp.models.skyscanner.skyscannerresponse.SkyscannerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/skyscanner/pollSearch")
    public void pollASearch() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", "b61a5a7435msh866977d946919afp1c7620jsn64158120fd4f");
        headers.add("X-RapidAPI-Host", "skyscanner-api.p.rapidapi.com");

        HttpEntity<String> request = new HttpEntity<>("parameters", headers);

        String token = "Jfi0e5a-YyX7H7I5hfj0RT8csIK690grTe5q5Ep1j90k2_krZdvTsUrlBa1iqnwAGtBu_aLnbQeTbOtD_Bva_9DpspRlEzbHYX0e0tIdcNOw60rRpmAgbHd1Wgtop5m9EARr2_zVSKstVU83Zsi3w0XumipBY1SycW0sBNba1pj8vZBhknjPo0R6ZoLRBohFUaqKGn3aZxP9uc8egcUOi-prKYeExrooaVmBwuFvUvD2t2wLjY4EoGRnt4FIg3-pmn93ZXWzWERduVdoL-nWg4B_xREHK1o5RmXQ6FhD4VKU3OhA6R-lFLYeEBskJHDUMMvNkp8UyBfVD6lr89rbZu0mrnOFS0fmDV1VhRheutjfuro8-3DNdtWjBfqrL0CKHAz3YOumRBdpvMvXfT0es0qUmeFOERzBRhp7CRfHepsp5GssRyhSGD1Pt5Lz0f7K20T88WwegFXpIj5zv42aRITL0SRa5mxzZt4U04vfyCotjA5JMimb6ffM8xvNAf2QxMYcpIUEqZ7XmBk12KvDSQ";
        String url = "https://skyscanner-api.p.rapidapi.com/v3/flights/live/search/poll/" + token;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SkyscannerResponse> responseObj = restTemplate.exchange(url, HttpMethod.POST, request, SkyscannerResponse.class);

        SkyscannerResponse resObj = responseObj.getBody();

        System.out.println("test");

    }

    @PostMapping("/skyscanner/createSearch")
    public void createSearch() {
        //TODO finish creating 'createSearch' endpoint to get session token from Skyscanner API.

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Rapidapi-Host", "skyscanner-api.p.rapidapi.com");
        headers.add("X-Rapidapi-Key", "b61a5a7435msh866977d946919afp1c7620jsn64158120fd4f");

        HttpEntity<String> request = new HttpEntity<>("parameters", headers);

        String url = "https://skyscanner-api.p.rapidapi.com/v3/flights/live/search/create";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FlightSearch> responseObj = restTemplate.exchange(url, HttpMethod.POST, request, FlightSearch.class);

        FlightSearch resObj = responseObj.getBody();

        System.out.println("test");

    }

}

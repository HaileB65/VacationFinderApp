package Capstone.Project.VacationFinderApp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class CountryFactsAPIService {

    public void getCountryFacts() throws IOException {
        URL url = new URL("https://api.api-ninjas.com/v1/country?name=United States");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("X-Api-Key", "z0OKSRFSKCVkJe38WWPxGA==WF3dSUDfg23VTqva");
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseStream);
        System.out.println(root.path("fact").asText());
    }
}

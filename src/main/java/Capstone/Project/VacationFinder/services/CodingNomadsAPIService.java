package Capstone.Project.VacationFinder.services;

import Capstone.Project.VacationFinder.models.CodingNomadsAPIResponse;
import Capstone.Project.VacationFinder.models.apiUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodingNomadsAPIService {

    RestTemplate restTemplate; // getAllUsers method isn't working b/c resttemplate is null
    @Value("${apiUrl}")
    private String apiURl;

    public List<apiUser> getAllUsers() {
        CodingNomadsAPIResponse response = restTemplate.getForObject("http://demo.codingnomads.co:8080/tasks_api/users?email=rickmartin88%40gmail.com", CodingNomadsAPIResponse.class);
        List<apiUser> results = new ArrayList<>();
        if (response != null & response.getStatus().equals("200 OK")) {
            return response.getData();
        } else {
            return results;
        }
    }

}

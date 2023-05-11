package Capstone.Project.VacationFinderApp.models.skyscannerAPI.skyscannerresponse;

import lombok.Data;

import java.util.HashMap;

@Data
public class Results {
    HashMap<String, SkyscannerItinerary> itineraries;
}

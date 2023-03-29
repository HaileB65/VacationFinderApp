package Capstone.Project.VacationFinderApp.models.skyscanner.skyscannerresponse;

import lombok.Data;

import java.util.HashMap;

@Data
public class Results {
    HashMap<String, SkyscannerItinerary> itineraries;
}

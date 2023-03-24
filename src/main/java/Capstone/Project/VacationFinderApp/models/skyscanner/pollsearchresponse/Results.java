package Capstone.Project.VacationFinderApp.models.skyscanner.pollsearchresponse;

import lombok.Data;

import java.util.HashMap;

@Data
public class Results {
    HashMap<String, SkyscannerItinerary> itineraries;
}

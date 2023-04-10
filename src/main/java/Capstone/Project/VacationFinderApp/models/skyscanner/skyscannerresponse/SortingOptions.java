package Capstone.Project.VacationFinderApp.models.skyscanner.skyscannerresponse;

import lombok.Data;

import java.util.List;

@Data
public class SortingOptions {
    List<FlightId> cheapest;
}

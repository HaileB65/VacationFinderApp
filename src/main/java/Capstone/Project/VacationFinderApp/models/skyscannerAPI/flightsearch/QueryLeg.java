package Capstone.Project.VacationFinderApp.models.skyscannerAPI.flightsearch;

import lombok.Data;

@Data
public class QueryLeg {
    OriginPlaceID originPlaceId;
    DestinationPlaceId destinationPlaceId;
    Date date;
}

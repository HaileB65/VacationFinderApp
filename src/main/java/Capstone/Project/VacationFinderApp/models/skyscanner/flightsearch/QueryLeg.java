package Capstone.Project.VacationFinderApp.models.skyscanner.flightsearch;

import lombok.Data;

@Data
public class QueryLeg {
    OriginPlaceID originPlaceId;
    DestinationPlaceId destinationPlaceId;
    Date date;
}

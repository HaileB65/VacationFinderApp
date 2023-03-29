package Capstone.Project.VacationFinderApp.models.skyscanner.flightsearch;

import lombok.Data;

@Data
public class Query {
    String market;
    String locale;
    String currency;
    QueryLeg[] queryLegs;
    String cabinClass;
    int adults;
    int[] childrenAges;
}

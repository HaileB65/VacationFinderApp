package Capstone.Project.VacationFinderApp.models.skyscannerAPI.skyscannerresponse;

import lombok.Data;

import java.util.List;

@Data
public class SkyscannerItinerary {
    List<PricingOption> pricingOptions;
}

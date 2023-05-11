package Capstone.Project.VacationFinderApp.models.skyscannerAPI.skyscannerresponse;

import lombok.Data;

import java.util.List;

@Data
public class PricingOption {
    String id;
    Price price;
    List<Item> items;
}

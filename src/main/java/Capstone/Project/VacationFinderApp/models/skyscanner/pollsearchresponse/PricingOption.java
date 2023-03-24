package Capstone.Project.VacationFinderApp.models.skyscanner.pollsearchresponse;

import lombok.Data;

import java.util.List;

@Data
public class PricingOption {
    String id;
    Price price;
    List<Item> items;
}

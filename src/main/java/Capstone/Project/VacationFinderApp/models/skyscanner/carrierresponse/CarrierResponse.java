package Capstone.Project.VacationFinderApp.models.skyscanner.carrierresponse;

import lombok.Data;

import java.util.HashMap;

@Data
public class CarrierResponse {
    HashMap<String, Carrier> carriers;
}

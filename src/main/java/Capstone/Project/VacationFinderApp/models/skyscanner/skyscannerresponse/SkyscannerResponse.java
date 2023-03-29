package Capstone.Project.VacationFinderApp.models.skyscanner.skyscannerresponse;

import lombok.Data;

@Data
public class SkyscannerResponse {
    String sessionToken;
    String status;
    String action;
    Content content;
}

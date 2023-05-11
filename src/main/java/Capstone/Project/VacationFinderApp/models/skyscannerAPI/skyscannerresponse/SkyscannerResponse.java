package Capstone.Project.VacationFinderApp.models.skyscannerAPI.skyscannerresponse;

import lombok.Data;

@Data
public class SkyscannerResponse {
    String sessionToken;
    String status;
    String action;
    Content content;
}

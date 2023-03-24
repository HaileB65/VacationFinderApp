package Capstone.Project.VacationFinderApp.models.skyscanner.pollsearchresponse;

import lombok.Data;

@Data
public class PollASearchResponse {
    String sessionToken;
    String status;
    String action;
    Content content;
}

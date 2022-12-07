package Capstone.Project.VacationFinder.models.CodingNomadsAPI;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Response {
    private List<apiUser> data;
    private Error error;
    private String status;
}

package Capstone.Project.NewMVCProject.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CodingNomadsAPIResponse {
        private List<apiUser> data;
        private Error error;
        private String status;
}

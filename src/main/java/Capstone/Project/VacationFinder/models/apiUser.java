package Capstone.Project.VacationFinder.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class apiUser {
    int id;
    String email;
    String firstName;
    String lastName;
    Timestamp createdAt;
    Timestamp updatedAt;
}

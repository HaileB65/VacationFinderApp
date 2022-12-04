package Capstone.Project.VacationFinder.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Questionnaire {
    String favoriteScenery;
    String weather;
    String preferredActivity;
    BigDecimal budget;
}

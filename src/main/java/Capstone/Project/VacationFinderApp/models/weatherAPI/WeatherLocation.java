package Capstone.Project.VacationFinderApp.models.weatherAPI;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherLocation {
    private String city;
    private String country;
}

package Capstone.Project.VacationFinderApp.models.weatherAPI;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherForecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String trip;
    String forecastImageUrl;

    @Column(name = "timestamp")
    @CreationTimestamp
    public Timestamp timestamp;
}

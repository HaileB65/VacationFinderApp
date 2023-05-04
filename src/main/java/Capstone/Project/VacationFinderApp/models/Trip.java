package Capstone.Project.VacationFinderApp.models;

import Capstone.Project.VacationFinderApp.models.weatherAPI.WeatherForecast;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trip implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String country;
    String city;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itinerary_id")
    public Itinerary itinerary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weatherForecast_id")
    public WeatherForecast weatherForecast;

    @Column(name = "timestamp")
    @CreationTimestamp
    public Timestamp timestamp;

    @ManyToMany(mappedBy = "trips")
    public Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Destination> destinations;

}

package Capstone.Project.VacationFinderApp.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "itineraries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Itinerary implements Serializable {
    String location1;
    String location2;
    String location3;
    String location4;
    String location5;
    String meal1;
    String meal2;
    String meal3;
    String meal4;
    String meal5;
    String leisure1;
    String leisure2;
    String leisure3;
    String leisure4;
    String leisure5;
    String transport1;
    String transport2;
    String transport3;
    String transport4;
    String transport5;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "timestamp")
    @CreationTimestamp
    private Timestamp timestamp;

}

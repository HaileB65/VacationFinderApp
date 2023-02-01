package Capstone.Project.VacationFinderApp.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "itineraries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Itinerary {
    String item1;
    String item2;
    String item3;
    String item4;
    String item5;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "timestamp")
    @CreationTimestamp
    private Timestamp timestamp;

}

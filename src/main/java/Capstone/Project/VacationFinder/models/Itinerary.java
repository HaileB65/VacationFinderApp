package Capstone.Project.VacationFinder.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "itineraries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String item1;
    String item2;
    String item3;
    String item4;
    String item5;
    String item6;
    String item7;
    String item8;
    String item9;
    String item10;

    @Column(name = "timestamp")
    @CreationTimestamp
    private Timestamp timestamp;
}

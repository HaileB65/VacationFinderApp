package Capstone.Project.VacationFinder.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "itineraries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Itinerary {
    String item1;
    String item2;
    String item3;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "timestamp")
    @CreationTimestamp
    private Timestamp timestamp;

    public <T> Itinerary(List<T> asList) {

    }
}

package Capstone.Project.VacationFinder.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Data
@NoArgsConstructor
@Table(name= "Destinations")
@AllArgsConstructor
@Builder
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String scenery;
    String weather;
    String activity1;
    String activity2;
    String activity3;
    int minimumBudget;

    @Column(name = "timestamp")
    @CreationTimestamp
    private Timestamp timestamp;
}

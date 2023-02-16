package Capstone.Project.VacationFinderApp.models;

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
public class Destination implements Serializable {
    @ManyToMany(mappedBy = "destinations")
    public Set<Trip> trips;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String scenery;
    String weather;
    String activity1;
    String activity2;
    String activity3;
    String image1;
    String image2;
    String city1;
    String city2;
    String city3;
    String city4;
    String city5;

    @Column(name = "timestamp")
    @CreationTimestamp
    private Timestamp timestamp;

    public Destination(String name) {
        this.name = name;
    }
}

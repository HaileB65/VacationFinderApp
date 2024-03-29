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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String country;
    String region;
    String description;
    String scenery;
    String weather;
    String activity1;
    String activity2;
    String activity3;
    String image1;
    String image2;
    String image3;
    String image4;
    String image5;
    String image6;
    String city1;
    String city2;
    String city3;
    String city4;
    String city5;

    @Column(name = "timestamp")
    @CreationTimestamp
    private Timestamp timestamp;

    @ManyToMany(mappedBy = "destinations")
    public Set<Trip> trips;

    public Destination(String name) {
        this.name = name;
    }
}

package Capstone.Project.VacationFinder.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


@Entity
@Table(name = "Destinations")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Destination {
    @Id
    @EqualsAndHashCode.Include
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

    @Column(name = "timestamp")
    @CreationTimestamp
    private Timestamp timestamp;

//    @ManyToMany(mappedBy = "destinations", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//    public Set<Trip> trips;
}

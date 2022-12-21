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

    @ManyToMany(mappedBy = "destinations", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    public Set<Trip> trips;

    @Override
    public String toString() {
        return "Destination{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", scenery='" + scenery + '\'' +
                ", weather='" + weather + '\'' +
                ", activity1='" + activity1 + '\'' +
                ", activity2='" + activity2 + '\'' +
                ", activity3='" + activity3 + '\'' +
                ", image1='" + image1 + '\'' +
                ", image2='" + image2 + '\'' +
                ", timestamp=" + timestamp +
                ", trips=" + trips.hashCode() +
                '}';
    }
}

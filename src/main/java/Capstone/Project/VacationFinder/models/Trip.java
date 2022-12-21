package Capstone.Project.VacationFinder.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "trips")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trip {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "timestamp")
    @CreationTimestamp
    private Timestamp timestamp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itinerary_id")
    public Itinerary itinerary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "checklist_id")
    public Checklist checklist;

    @ManyToMany(mappedBy = "trips", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    public Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "trips_destinations",
            joinColumns = @JoinColumn(name = "trip_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "destination_id", referencedColumnName = "id"))
    public Set<Destination> destinations;


    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", itinerary=" + itinerary +
                ", checklist=" + checklist +
                ", users=" + users +
                ", destinations=" + destinations.hashCode() +
                '}';
    }
}

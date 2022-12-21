package Capstone.Project.VacationFinder.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="trips")
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

    String selectedDestination;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itinerary_id")
    public Itinerary itinerary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "checklist_id")
    public Checklist checklist;

    @ManyToMany(mappedBy = "trips",cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    public Set<User> users;

    @Column(name = "timestamp")
    @CreationTimestamp
    private Timestamp timestamp;

}

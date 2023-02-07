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
public class Trip implements Serializable {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itinerary_id")
    public Itinerary itinerary;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "checklist_id")
    public Checklist checklist;
    @Column(name = "timestamp")
    @CreationTimestamp
    public Timestamp timestamp;
    @ManyToMany(mappedBy = "trips")
    public Set<User> users;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Destination> destinations;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

}

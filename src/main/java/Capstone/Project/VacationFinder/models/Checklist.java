package Capstone.Project.VacationFinder.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "checklists")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Checklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<String> checklistItems;

    @Column(name = "timestamp")
    @CreationTimestamp
    private Timestamp timestamp;

    public Checklist() {
        checklistItems = new ArrayList<>();
    }
}

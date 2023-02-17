package Capstone.Project.VacationFinderApp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "checklists")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Checklist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;

    @ElementCollection
    private List<String> checklistItems;

    @Column(name = "timestamp")
    @CreationTimestamp
    private Timestamp timestamp;

    public Checklist() {
        checklistItems = new ArrayList<>();
    }
}

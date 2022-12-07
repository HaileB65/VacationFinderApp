package Capstone.Project.VacationFinder.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Tasks")
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String task;

    @Column(name = "timestamp")
    @CreationTimestamp
    private Timestamp timestamp;
}

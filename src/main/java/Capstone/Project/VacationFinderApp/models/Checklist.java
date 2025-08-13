package Capstone.Project.VacationFinderApp.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "checklists")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Checklist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;
    String item1;
    String item2;
    String item3;
    String item4;
    String item5;
    String item6;
    String item7;
    String item8;
    String item9;
    String item10;
    final String checkbox1 = "false";

    @Column(name = "timestamp")
    @CreationTimestamp
    private Timestamp timestamp;
}

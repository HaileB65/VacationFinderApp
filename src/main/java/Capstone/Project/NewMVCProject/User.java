package Capstone.Project.NewMVCProject;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name= "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstName;
    String lastName;
    String email;
    String phone;

    @Column(name = "timestamp")
    @CreationTimestamp
    private Timestamp timestamp;

}

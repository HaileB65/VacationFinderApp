//package Capstone.Project.VacationFinder.models;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.Collection;
//
//@Entity
//@NoArgsConstructor
//@Data
//public class Privilege {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String name;
//
//    @ManyToMany(mappedBy = "privileges")
//    private Collection<Role> roles;
//
//    public Privilege(String name) {
//        this.name = name;
//    }
//}

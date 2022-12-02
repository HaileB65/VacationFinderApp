package Capstone.Project.VacationFinder.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name= "Budget")
@AllArgsConstructor
@Builder
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    BigDecimal transportationCost;
    BigDecimal lodgingCost;
    BigDecimal foodAlcoholCost;
    BigDecimal  entertainmentCost;
    BigDecimal total;

    public void setTotal(BigDecimal total) {
        total = total.add(transportationCost);
        total = total.add(lodgingCost);
        total = total.add(foodAlcoholCost);
        total = total.add(entertainmentCost);
        this.total = total;
    }

    @Column(name = "timestamp")
    @CreationTimestamp
    private Timestamp timestamp;

}

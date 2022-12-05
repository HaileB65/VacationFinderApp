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
@Table(name = "Budget")
@AllArgsConstructor
@Builder
public class Budget {
    BigDecimal transportationCost;
    BigDecimal lodgingCost;
    BigDecimal foodAlcoholCost;
    BigDecimal entertainmentCost;
    BigDecimal total;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "timestamp")
    @CreationTimestamp
    private Timestamp timestamp;

    public void setTotal(BigDecimal total) {
        total = total.add(transportationCost);
        total = total.add(lodgingCost);
        total = total.add(foodAlcoholCost);
        total = total.add(entertainmentCost);
        this.total = total;
    }

}

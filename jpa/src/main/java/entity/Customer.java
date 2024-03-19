package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor //기본생성자
@Table(name="Customer_tb") //표기법에 의해 이름이 다를 경우
public class Customer {
    @Id
    @Column(name="customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    LocalDate regDate;

    public Customer(String name) {
        this.name = name;
        regDate = LocalDate.now();
    }
}

package edu.sperek.vendingmachine.vending.infrastructure.persistance.relational.model;

import edu.sperek.vendingmachine.vending.infrastructure.persistance.DrinkPM;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class JpaDrink extends DrinkPM {
    @Id
    @GeneratedValue
    Long id;
    String name;
    Integer amount;
    BigDecimal price;
}

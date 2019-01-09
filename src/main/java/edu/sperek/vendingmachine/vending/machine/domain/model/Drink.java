package edu.sperek.vendingmachine.vending.machine.domain.model;

import com.google.common.base.Objects;
import edu.sperek.vendingmachine.vending.machine.dto.DrinkDto;
import lombok.*;

import java.math.BigDecimal;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Drink {
    private Long id;
    private String name;
    private Integer amount;
    private BigDecimal price;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Drink other = (Drink) obj;
        return Objects.equal(this.name, other.name)
                && Objects.equal(this.amount, other.amount)
                && Objects.equal(this.price, other.price);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name, this.amount, this.price);
    }

    public void refill(Integer amount) {
        this.amount += amount;
    }

    public void decreaseAmountByOne() {
        this.amount--;
    }

    public DrinkDto dto() {
        return DrinkDto.builder()
                .id(this.id)
                .name(this.name)
                .price(this.price)
                .build();
    }
}

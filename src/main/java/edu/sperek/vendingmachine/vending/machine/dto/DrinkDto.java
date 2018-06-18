package edu.sperek.vendingmachine.vending.machine.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
@EqualsAndHashCode
public class DrinkDto {
    private Long id;
    private String name;
    private BigDecimal price;
}

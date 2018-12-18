package edu.sperek.vendingmachine.vending.machine.domain.model;

import edu.sperek.vendingmachine.vending.machine.dto.DrinkDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@AllArgsConstructor
@Getter
public class DrinkOrder {
    private final Drink orderedDrink;
    private final List<Money> change;
}

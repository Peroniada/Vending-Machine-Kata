package edu.sperek.vendingmachine.vending.machine.domain;

import edu.sperek.vendingmachine.vending.machine.domain.enitities.Money;
import edu.sperek.vendingmachine.vending.machine.dto.DrinkDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@AllArgsConstructor
@Getter
class DrinkOrder {
    private final DrinkDto orderedDrink;
    private final List<Money> change;
}

package edu.sperek.vendingmachine.vending.machine.ports;

import edu.sperek.vendingmachine.vending.machine.domain.model.Drink;

import java.util.List;

public interface DrinksRepository<Type, Id> {
    Type getDrink(final Id drinkId);

    void subtractDrink(final Id drinkId);

    void refill(final Id drinkId, final Integer amount);

    List<Type> getDrinks();
}

package edu.sperek.vendingmachine.vending.machine.ports;

import edu.sperek.vendingmachine.vending.machine.domain.model.Drink;

import java.util.List;

public interface DrinksRepository<Type, Id> {
    Type getDrink(final Id id);

    void subtractDrink(final Id id);

    void refill(final Id id, final Integer amount);

    List<Type> getDrinks();
}

package edu.sperek.vendingmachine.vending.machine.ports;

import edu.sperek.vendingmachine.vending.machine.domain.enitities.Drink;

import java.util.List;

public interface DrinksRepository {
    Drink getDrink(final Long drinkId);

    void subtractDrink(final Long drinkId);

    void refill(final Long drinkId, final Integer amount);

    List<Drink> getDrinks();
}

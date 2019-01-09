package edu.sperek.vendingmachine.vending.infrastructure.persistance;

import edu.sperek.vendingmachine.vending.infrastructure.DrinkFactory;
import edu.sperek.vendingmachine.vending.infrastructure.exceptions.NoSuchDrinkOfGivenIdException;
import edu.sperek.vendingmachine.vending.machine.domain.model.Drink;
import edu.sperek.vendingmachine.vending.machine.ports.DrinksRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryDrinksRepository implements DrinksRepository<Drink, Long> {

    private final List<Drink> drinks;

    public InMemoryDrinksRepository() {
        drinks = new ArrayList<>();
        drinks.add(DrinkFactory.cocoRise(1));
        drinks.add(DrinkFactory.pandaShake(3));
        drinks.add(DrinkFactory.fizzyBubble(10));
    }

    @Override
    public Drink getDrink(final Long id)
    {
        final Optional<Drink> getDrink = getDrinkOfId(id);
        if(!getDrink.isPresent()) {
            final String message = "There is no such drink of id" + id;
            throw new NoSuchDrinkOfGivenIdException(message);
        }
        return getDrink.get();
    }

    @Override
    public void subtractDrink(final Long id) {
        final Optional<Drink> getDrink = getDrinkOfId(id);
        getDrink.ifPresent(Drink::decreaseAmountByOne);
    }

    private Optional<Drink> getDrinkOfId(Long drinkId) {
        return drinks.stream().filter(drink -> drink.getId().equals(drinkId)).findFirst();
    }

    @Override
    public void refill(final Long id, final Integer amount) {
        final Optional<Drink> getDrink = getDrinkOfId(id);
        getDrink.ifPresent(drink -> drink.refill(amount));
    }

    @Override
    public List<Drink> getDrinks() {
        return this.drinks;
    }
}


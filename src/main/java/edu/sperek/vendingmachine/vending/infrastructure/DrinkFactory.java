package edu.sperek.vendingmachine.vending.infrastructure;

import edu.sperek.vendingmachine.vending.machine.domain.enitities.Drink;

import java.math.BigDecimal;

public class DrinkFactory {

    private DrinkFactory() {}

    public static Drink cocoRise(final Integer amount) {
        return new Drink(1L, "Coco Rise", amount, BigDecimal.valueOf(0.65));
    }

    public static Drink pandaShake(final Integer amount) {
        return new Drink(2L, "Panda Shake", amount, BigDecimal.valueOf(1.0));
    }

    public static Drink fizzyBubble(final Integer amount) {
        return new Drink(3L, "Fizzy Bubble", amount, BigDecimal.valueOf(1.5));
    }
}

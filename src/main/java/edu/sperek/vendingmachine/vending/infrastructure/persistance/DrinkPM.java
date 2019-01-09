package edu.sperek.vendingmachine.vending.infrastructure.persistance;

import java.math.BigDecimal;

public abstract class DrinkPM {
    public Long id;
    public String name;
    public Integer amount;
    public BigDecimal price;
}

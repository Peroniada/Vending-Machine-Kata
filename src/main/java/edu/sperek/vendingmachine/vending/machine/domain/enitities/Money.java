package edu.sperek.vendingmachine.vending.machine.domain.enitities;

import java.math.BigDecimal;

public enum Money {

    DOLLAR(BigDecimal.ONE),
    QUARTER(BigDecimal.valueOf(0.25)),
    DIME(BigDecimal.valueOf(0.1)),
    NICKEL(BigDecimal.valueOf(0.05));

    Money(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    private final BigDecimal value;
}

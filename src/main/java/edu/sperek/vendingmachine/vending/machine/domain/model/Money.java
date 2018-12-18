package edu.sperek.vendingmachine.vending.machine.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public enum Money {

    @JsonProperty("dollar") DOLLAR(BigDecimal.ONE),
    @JsonProperty("quarter") QUARTER(BigDecimal.valueOf(0.25)),
    @JsonProperty("dime") DIME(BigDecimal.valueOf(0.1)),
    @JsonProperty("nickel") NICKEL(BigDecimal.valueOf(0.05));

    Money(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    private final BigDecimal value;
}

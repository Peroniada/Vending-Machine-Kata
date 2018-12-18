package edu.sperek.vendingmachine.vending.machine.ports;

import edu.sperek.vendingmachine.vending.machine.domain.model.Money;

import java.math.BigDecimal;

public interface CreditRepository {
    BigDecimal getCredit();

    void addCredit(final Money money);

    BigDecimal returnMoney(BigDecimal creditToReturn);
}

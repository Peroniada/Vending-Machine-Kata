package edu.sperek.vendingmachine.vending.machine.ports;

import edu.sperek.vendingmachine.vending.machine.domain.enitities.Money;

import java.math.BigDecimal;
import java.util.List;

public interface CreditRepository {
    BigDecimal getCredit();

    void addCredit(final Money money);

    BigDecimal returnMoney(BigDecimal creditToReturn);
}

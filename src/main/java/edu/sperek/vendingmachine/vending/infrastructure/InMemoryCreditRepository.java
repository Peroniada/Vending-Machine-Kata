package edu.sperek.vendingmachine.vending.infrastructure;

import edu.sperek.vendingmachine.vending.machine.ports.CreditRepository;
import edu.sperek.vendingmachine.vending.machine.domain.enitities.Money;

import java.math.BigDecimal;

public class InMemoryCreditRepository implements CreditRepository {

    private BigDecimal credit;

    public InMemoryCreditRepository() {
        this.credit = BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getCredit() {
        return credit;
    }

    @Override
    public void addCredit(final Money money) {
        this.credit = this.credit.add(money.getValue());
    }

    @Override
    public BigDecimal returnMoney(final BigDecimal creditToReturn) {
        this.credit = this.credit.subtract(creditToReturn);
        return creditToReturn;
    }

}

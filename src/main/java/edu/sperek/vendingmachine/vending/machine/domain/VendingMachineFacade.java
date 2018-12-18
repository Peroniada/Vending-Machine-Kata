package edu.sperek.vendingmachine.vending.machine.domain;

import edu.sperek.vendingmachine.vending.machine.domain.model.Drink;
import edu.sperek.vendingmachine.vending.machine.domain.model.DrinkOrder;
import edu.sperek.vendingmachine.vending.machine.domain.model.Money;
import edu.sperek.vendingmachine.vending.machine.ports.CreditRepository;
import edu.sperek.vendingmachine.vending.machine.ports.DrinksRepository;

import java.math.BigDecimal;
import java.util.*;


public class VendingMachineFacade {

    private final CreditRepository creditRepository;
    private final DrinksRepository drinksRepository;

    public VendingMachineFacade(CreditRepository creditRepository, DrinksRepository drinksRepository) {
        this.creditRepository = creditRepository;
        this.drinksRepository = drinksRepository;
    }

    public void refillDrinks(final List<Drink> drinks, final Integer amount) {
        for (final Drink drink : drinks) {
            this.drinksRepository.refill(drink.getId(), amount);
        }
    }

    public List<Drink> getDrinks() {
        return drinksRepository.getDrinks();
    }

    public BigDecimal getCredit() {
        return this.creditRepository.getCredit();
    }

    public void insert(final Money money) {
        this.creditRepository.addCredit(money);
    }

    public List<Money> returnMoney() {
        final BigDecimal credit = this.creditRepository.getCredit();
        return prepareChange(this.creditRepository.returnMoney(credit));
    }

    public DrinkOrder buyDrink(final Long drinkId) {
        final BigDecimal availableCredit = creditRepository.getCredit();
        if (!isDrinkInStock(drinkId)) {
            final String message = "No more in stock";
            throw new NoMoreInStockException(message);
        }
        drinksRepository.subtractDrink(drinkId);
        final Drink drink = drinksRepository.getDrink(drinkId);
        if (haveEnoughCredit(availableCredit, drink)) {
            final String message = "Not enough credit. You lack "
                    + drink.getPrice().subtract(availableCredit)
                    + "to buy this drink";
            throw new NotEnoughCreditException(message);
        }
        return prepareOrder(drink, availableCredit);
    }

    private boolean haveEnoughCredit(BigDecimal availableCredit, Drink drink) {
        return availableCredit.compareTo(drink.getPrice()) < 0;
    }

    private boolean isDrinkInStock(Long drinkId) {
        return drinksRepository.getDrink(drinkId).getAmount() > 0;
    }

    private DrinkOrder prepareOrder(final Drink drink, final BigDecimal credit) {
        final BigDecimal change = credit.subtract(drink.getPrice());
        return new DrinkOrder(drink, prepareChange(change));
    }

    private List<Money> prepareChange(final BigDecimal change) {

        final List<Money> changeInCash = new ArrayList<>();
        BigDecimal remainingChange = change;
        while (remainingChange.compareTo(BigDecimal.ZERO) > 0) {
            final Optional<Money> optionalMoney = getMoneySmallerThanChange(remainingChange);

            if (optionalMoney.isPresent()) {
                final Money coin = optionalMoney.get();
                remainingChange = remainingChange.subtract(coin.getValue());
                changeInCash.add(coin);
            }
        }

        return changeInCash;
    }

    private Optional<Money> getMoneySmallerThanChange(BigDecimal remainingChange) {

        return Arrays.stream(Money.values()).filter(coin -> {
            final BigDecimal coinValue = coin.getValue();
            return coinValue.compareTo(remainingChange) <= 0;
        }).findFirst();
    }
}

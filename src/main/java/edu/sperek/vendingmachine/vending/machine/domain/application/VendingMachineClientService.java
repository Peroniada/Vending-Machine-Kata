package edu.sperek.vendingmachine.vending.machine.domain.application;

import edu.sperek.vendingmachine.vending.machine.domain.enitities.DrinkOrder;
import edu.sperek.vendingmachine.vending.machine.domain.VendingMachineFacade;
import edu.sperek.vendingmachine.vending.machine.domain.enitities.Drink;
import edu.sperek.vendingmachine.vending.machine.domain.enitities.Money;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VendingMachineClientService {
    private final VendingMachineFacade vendingFacade;

    public VendingMachineClientService(VendingMachineFacade vendingFacade) {
        this.vendingFacade = vendingFacade;
    }

    public BigDecimal getCredit() {
        return this.vendingFacade.getCredit();
    }

    public List<Drink> getDrinks() {
        return this.vendingFacade.getDrinks();
    }

    public void insertMoney(final Money money) {
        this.vendingFacade.insert(money);
    }

    public List<Money> returnMoney() {
        return this.vendingFacade.returnMoney();
    }

    public DrinkOrder buyDrink(final Long drinkId) {
        return this.vendingFacade.buyDrink(drinkId);
    }





}

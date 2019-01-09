package edu.sperek.vendingmachine.vending.machine.application;

import edu.sperek.vendingmachine.vending.infrastructure.persistance.DrinkPM;
import edu.sperek.vendingmachine.vending.machine.domain.model.DrinkOrder;
import edu.sperek.vendingmachine.vending.machine.domain.VendingMachineFacade;
import edu.sperek.vendingmachine.vending.machine.domain.model.Drink;
import edu.sperek.vendingmachine.vending.machine.domain.model.Money;
import edu.sperek.vendingmachine.vending.machine.ports.DrinksRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VendingMachineClientService {
    private final VendingMachineFacade vendingFacade;
    private DrinksRepository <DrinkPM, Long> drinksRepository;


    public VendingMachineClientService(VendingMachineFacade vendingFacade, DrinksRepository drinksRepository) {
        this.vendingFacade = vendingFacade;
        this.drinksRepository = drinksRepository;
    }

    public BigDecimal getCredit() {
        return this.vendingFacade.getCredit();
    }

    public List<Drink> getDrinks() {
        return PersistenceDomainDrinkMapper.toDrinks(this.drinksRepository.getDrinks());
    }

    public void insertMoney(final Money money) {
        this.vendingFacade.insert(money);
    }

    public List<Money> returnMoney() {
        return this.vendingFacade.returnMoney();
    }

    public DrinkOrder buyDrink(final Long drinkId) {
        final Drink drink = PersistenceDomainDrinkMapper.toDrink((this.drinksRepository.getDrink(drinkId)));
        return this.vendingFacade.buyDrink(drink);
    }





}

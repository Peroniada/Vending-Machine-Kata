package edu.sperek.vendingmachine.vending.machine.application;

import edu.sperek.vendingmachine.vending.machine.domain.VendingMachineFacade;
import edu.sperek.vendingmachine.vending.machine.domain.model.Drink;
import edu.sperek.vendingmachine.vending.machine.ports.DrinksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendingMachineCatererService {


    private final VendingMachineFacade vendingFacade;

    private DrinksRepository drinksRepository;

    public VendingMachineCatererService(DrinksRepository drinksRepository, VendingMachineFacade vendingFacade) {
        this.drinksRepository = drinksRepository;
        this.vendingFacade = vendingFacade;
    }

    public void refillDrinks(final List<Drink> drinks, final Integer amount) {
        this.vendingFacade.refillDrinks(drinks, amount);
    }

    public List<?> getDrinks() {
        return drinksRepository.getDrinks();
    }
}

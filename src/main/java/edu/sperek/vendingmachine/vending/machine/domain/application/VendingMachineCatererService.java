package edu.sperek.vendingmachine.vending.machine.domain.application;

import edu.sperek.vendingmachine.vending.machine.domain.VendingMachineFacade;
import edu.sperek.vendingmachine.vending.machine.domain.enitities.Drink;
import edu.sperek.vendingmachine.vending.machine.dto.DrinkDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendingMachineCatererService {
    private final VendingMachineFacade vendingFacade;

    public VendingMachineCatererService(VendingMachineFacade vendingFacade) {
        this.vendingFacade = vendingFacade;
    }

    public void refillDrinks(final List<DrinkDto> drinks, final Integer amount) {
        this.vendingFacade.refillDrinks(drinks, amount);
    }

    public List<Drink> getDrinks() {
        return this.vendingFacade.getDrinks();
    }
}

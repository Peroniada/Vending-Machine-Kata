package edu.sperek.vendingmachine.vending;

import edu.sperek.vendingmachine.vending.infrastructure.InMemoryCreditRepository;
import edu.sperek.vendingmachine.vending.infrastructure.InMemoryDrinksRepository;
import edu.sperek.vendingmachine.vending.machine.domain.VendingMachineFacade;
import edu.sperek.vendingmachine.vending.machine.ports.CreditRepository;
import edu.sperek.vendingmachine.vending.machine.ports.DrinksRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VendingConfiguration {

    @Bean
    public VendingMachineFacade vendingFacade() {
        return vendingFacade(new InMemoryCreditRepository(), new InMemoryDrinksRepository());
    }

    private VendingMachineFacade vendingFacade(final CreditRepository creditRepository, final DrinksRepository drinksRepository) {
        return new VendingMachineFacade(creditRepository);
    }
}

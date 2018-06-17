package edu.sperek.vendingmachine.vending.machine.domain;

import edu.sperek.vendingmachine.vending.infrastructure.InMemoryCreditRepository;
import edu.sperek.vendingmachine.vending.infrastructure.InMemoryDrinksRepository;
import edu.sperek.vendingmachine.vending.machine.ports.CreditRepository;
import edu.sperek.vendingmachine.vending.machine.ports.DrinksRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class VendingConfiguration {

    VendingMachineFacade vendingFacade() {
        return vendingFacade(new InMemoryCreditRepository(), new InMemoryDrinksRepository());
    }

    @Bean
    private VendingMachineFacade vendingFacade(final CreditRepository creditRepository, final DrinksRepository drinksRepository) {
        return new VendingMachineFacade(creditRepository, drinksRepository);
    }
}

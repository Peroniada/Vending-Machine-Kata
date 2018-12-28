package edu.sperek.vendingmachine.vending;

import edu.sperek.vendingmachine.vending.infrastructure.InMemoryCreditRepository;
import edu.sperek.vendingmachine.vending.infrastructure.InMemoryDrinksRepository;
import edu.sperek.vendingmachine.vending.infrastructure.persistance.relational.JpaDrinksRepository;
import edu.sperek.vendingmachine.vending.machine.application.VendingMachineClientService;
import edu.sperek.vendingmachine.vending.machine.domain.VendingMachineFacade;
import edu.sperek.vendingmachine.vending.machine.ports.CreditRepository;
import edu.sperek.vendingmachine.vending.machine.ports.DrinksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VendingConfiguration {

    private final JpaDrinksRepository drinksRepository;

    @Autowired
    public VendingConfiguration(JpaDrinksRepository drinksRepository) {
        this.drinksRepository = drinksRepository;
    }

    @Bean
    public VendingMachineClientService clientService () {
        final boolean useJpa = false;
        if(useJpa) {
            return new VendingMachineClientService(vendingFacade(), drinksRepository);
        } else {
            return new VendingMachineClientService(vendingFacade(), new InMemoryDrinksRepository());
        }
    }

    @Bean
    public VendingMachineFacade vendingFacade() {
        return vendingFacade(new InMemoryCreditRepository());
    }

    private VendingMachineFacade vendingFacade(final CreditRepository creditRepository) {
        return new VendingMachineFacade(creditRepository);
    }


}

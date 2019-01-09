package edu.sperek.vendingmachine.vending;

import edu.sperek.vendingmachine.vending.infrastructure.persistance.InMemoryCreditRepository;
import edu.sperek.vendingmachine.vending.infrastructure.persistance.InMemoryDrinksRepository;
import edu.sperek.vendingmachine.vending.machine.application.VendingMachineClientService;
import edu.sperek.vendingmachine.vending.machine.domain.VendingMachineFacade;
import edu.sperek.vendingmachine.vending.machine.ports.CreditRepository;
import edu.sperek.vendingmachine.vending.machine.ports.DrinksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:configfile.properties")
@ConfigurationProperties(prefix = "machine")
public class VendingConfiguration {

    private final DrinksRepository drinksRepository;
    @Value("${machine.useJpa}")
    private boolean useJpa;

    @Autowired
    public VendingConfiguration(DrinksRepository drinksRepository) {
        this.drinksRepository = drinksRepository;
    }

    @Bean
    public VendingMachineClientService clientService() {
        if(useJpa) {
            System.out.println("-------------================= USING JPA ================----------------");
            return new VendingMachineClientService(vendingFacade(), drinksRepository);
        } else {
            System.out.println("-------------================= USING In Memory Persistance ================----------------");
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

package edu.sperek.vendingmachine.vending.infrastructure.rest;

import edu.sperek.vendingmachine.vending.infrastructure.rest.requests.RefillMachineRequest;
import edu.sperek.vendingmachine.vending.machine.domain.application.VendingMachineCatererService;
import edu.sperek.vendingmachine.vending.machine.domain.enitities.Drink;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class VendingMachineCatererEndpoint {
    private final VendingMachineCatererService vendingMachineCatererService;

    public VendingMachineCatererEndpoint(VendingMachineCatererService vendingMachineCatererService) {
        this.vendingMachineCatererService = vendingMachineCatererService;
    }

    @PutMapping("/caterer/refill")
    public ResponseEntity<List<Drink>> refillDrinks(@RequestBody final RefillMachineRequest request) {
        this.vendingMachineCatererService.refillDrinks(request.getDrinks(), request.getAmount());
        final List<Drink> drinks = this.vendingMachineCatererService.getDrinks();
        return new ResponseEntity<>(drinks, HttpStatus.ACCEPTED);
    }

}

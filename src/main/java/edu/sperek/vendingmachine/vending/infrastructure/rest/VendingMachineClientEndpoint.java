package edu.sperek.vendingmachine.vending.infrastructure.rest;


import edu.sperek.vendingmachine.vending.infrastructure.rest.requests.InsertCoinRequest;
import edu.sperek.vendingmachine.vending.machine.domain.application.VendingMachineClientService;
import edu.sperek.vendingmachine.vending.machine.domain.enitities.Drink;
import edu.sperek.vendingmachine.vending.machine.domain.enitities.DrinkOrder;
import edu.sperek.vendingmachine.vending.machine.domain.enitities.Money;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
class VendingMachineClientEndpoint {
    private final VendingMachineClientService vendingMachineClientService;

    public VendingMachineClientEndpoint(VendingMachineClientService vendingMachineClientService) {
        this.vendingMachineClientService = vendingMachineClientService;
    }

    @GetMapping("/credit")
    public ResponseEntity<BigDecimal> getCredit() {
        final BigDecimal credit = this.vendingMachineClientService.getCredit();
        return new ResponseEntity<>(credit, HttpStatus.OK);
    }

    @GetMapping("/drinks")
    public ResponseEntity<List<Drink>> getDrinks() {
        return new ResponseEntity<>(this.vendingMachineClientService.getDrinks(), HttpStatus.OK);
    }

    @PutMapping("/insert")
    public ResponseEntity<BigDecimal> insertMoney(@RequestBody final InsertCoinRequest request) {
        this.vendingMachineClientService.insertMoney(request.getMoney());
        final BigDecimal credit = this.vendingMachineClientService.getCredit();
        return new ResponseEntity<>(credit, HttpStatus.ACCEPTED);
    }

    @GetMapping("/return")
    public ResponseEntity<List<Money>> returnMoney() {
        final List<Money> monies = this.vendingMachineClientService.returnMoney();
        return new ResponseEntity<>(monies, HttpStatus.OK);
    }

    @GetMapping("/buy/{drinkId}")
    public ResponseEntity<DrinkOrder> buyDrink(@PathVariable final int drinkId) {
        final DrinkOrder drinkOrder = this.vendingMachineClientService.buyDrink(Integer.toUnsignedLong(drinkId));
        return new ResponseEntity<>(drinkOrder, HttpStatus.OK);
    }
}

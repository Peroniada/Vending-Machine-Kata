package edu.sperek.vendingmachine.vending.primary.rest.requests;

import edu.sperek.vendingmachine.vending.machine.domain.model.Drink;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefillMachineRequest {
    private List<Drink> drinks;
    private Integer amount;

}
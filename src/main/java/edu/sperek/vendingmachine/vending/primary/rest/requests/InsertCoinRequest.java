package edu.sperek.vendingmachine.vending.primary.rest.requests;

import edu.sperek.vendingmachine.vending.machine.domain.model.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsertCoinRequest {
    private Money money;
}

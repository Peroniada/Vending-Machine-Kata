package edu.sperek.vendingmachine.vending.infrastructure.rest.requests;

import edu.sperek.vendingmachine.vending.machine.dto.DrinkDto;
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
    private List<DrinkDto> drinks;
    private Integer amount;

}

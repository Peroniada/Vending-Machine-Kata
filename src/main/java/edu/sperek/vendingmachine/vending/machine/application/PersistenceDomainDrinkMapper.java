package edu.sperek.vendingmachine.vending.machine.application;

import edu.sperek.vendingmachine.vending.infrastructure.persistance.DrinkPM;
import edu.sperek.vendingmachine.vending.machine.domain.model.Drink;

import java.util.List;
import java.util.stream.Collectors;

class PersistenceDomainDrinkMapper {
    //przemapować persistenceModel na domain model

    static <PersistenceModel extends DrinkPM> Drink toDrink (PersistenceModel pmDrink) {
        return Drink.builder().id(pmDrink.id).name(pmDrink.name).amount(pmDrink.amount).price(pmDrink.price).build();
    }

    static <PersistenceModel extends DrinkPM> List<Drink> toDrinks (List<PersistenceModel> pmDrinks) {
        return pmDrinks.stream().map(PersistenceDomainDrinkMapper::toDrink).collect(Collectors.toList());
    }

}

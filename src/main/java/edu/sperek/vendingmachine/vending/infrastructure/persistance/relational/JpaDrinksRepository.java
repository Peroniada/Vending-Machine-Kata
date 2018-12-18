package edu.sperek.vendingmachine.vending.infrastructure.persistance.relational;

import edu.sperek.vendingmachine.vending.infrastructure.persistance.relational.model.Drink;
import edu.sperek.vendingmachine.vending.machine.ports.DrinksRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaDrinksRepository extends DrinksRepository<Drink, Long>, JpaRepository<Drink, Long> {

    @Override
    @Query("select d from Drink where d.id =?1")
    Drink getDrink(final Long drinkId);

    @Override
    @Query("update Drink d set d.amount = d.amount - 1 where d.id = ?1")
    void subtractDrink(final Long drinkId);

    @Override
    @Query("update Drink d set d.amount = d.amount - ?2 where d.id = ?1")
    void refill(final Long drinkId, final Integer amount);

    @Override
    @Query("from Drink")
    List<Drink> getDrinks();
}

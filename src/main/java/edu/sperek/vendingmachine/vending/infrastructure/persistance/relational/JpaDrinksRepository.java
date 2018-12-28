package edu.sperek.vendingmachine.vending.infrastructure.persistance.relational;

import edu.sperek.vendingmachine.vending.infrastructure.persistance.relational.model.DrinkPM;
import edu.sperek.vendingmachine.vending.machine.ports.DrinksRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaDrinksRepository extends DrinksRepository<DrinkPM, Long>, JpaRepository<DrinkPM, Long> {

    @Override
    @Query("select d from DrinkPM where d.id =?1") DrinkPM getDrink(final Long drinkId);

    @Override
    @Query("update DrinkPM d set d.amount = d.amount - 1 where d.id = ?1")
    void subtractDrink(final Long drinkId);

    @Override
    @Query("update DrinkPM d set d.amount = d.amount - ?2 where d.id = ?1")
    void refill(final Long drinkId, final Integer amount);

    @Override
    @Query("from DrinkPM")
    List<DrinkPM> getDrinks();
}

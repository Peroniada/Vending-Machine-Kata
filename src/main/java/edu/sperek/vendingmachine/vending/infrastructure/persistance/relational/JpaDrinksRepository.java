package edu.sperek.vendingmachine.vending.infrastructure.persistance.relational;

import edu.sperek.vendingmachine.vending.infrastructure.persistance.relational.model.JpaDrink;
import edu.sperek.vendingmachine.vending.machine.ports.DrinksRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaDrinksRepository extends DrinksRepository<JpaDrink, Long>, JpaRepository<JpaDrink, Long> {

    @Override
    @Query("select d from JpaDrink d where d.id = :id")
    JpaDrink getDrink(final Long id);

    @Override
    @Query("update JpaDrink set amount = amount - 1 where id = :id")
    void subtractDrink(final Long id);

    @Override
    @Query("update JpaDrink set amount = amount + :amount where id = :id")
    void refill(final Long id, final Integer amount);

    @Override
    @Query("from JpaDrink")
    List<JpaDrink> getDrinks();
}

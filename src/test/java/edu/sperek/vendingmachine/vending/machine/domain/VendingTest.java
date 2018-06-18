package edu.sperek.vendingmachine.vending.machine.domain;

import edu.sperek.vendingmachine.vending.infrastructure.DrinkFactory;
import edu.sperek.vendingmachine.vending.machine.domain.enitities.Drink;
import edu.sperek.vendingmachine.vending.machine.domain.enitities.DrinkOrder;
import edu.sperek.vendingmachine.vending.machine.domain.enitities.Money;
import edu.sperek.vendingmachine.vending.machine.dto.DrinkDto;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class VendingTest {

    /*
     *
     * nickel - 0.05, dime - 0.1, quarter - 0.25, dollar - 1.00
     *
     * happy scenario
     *
     * as a thirsty person i want to:
     *
     * given the drinks in vending machine are
     *
     * [
     *   {id: 1, name: "Coco-rise", price: 0.65, amount: 1},
     *   {id: 2, name: "Panda-shake", price: 1, amount: 3},
     *   {id: 3, name: "Fizzy-bubble" , price: 1.5, amount: 10}
     * ]
     *
     * when I go to /drinks i see both drinks
     * when I go to /credit I have 0
     * when I post to /insert/dime I can see I have credit of 0.1
     * when I post to /insert/quarter I can see I have credit of 0.35
     * when I post to /insert/dollar I can see I have credit of 1.35
     *
     * when I go to /return I will get dime, quarter and dollar
     *  and I can see i have credit of 0
     *
     * when I go to /buy/1 and my credit is zero I can see "not enough credit to buy a drink"
     * when I post to /insert/dollar I can see I have credit of 1
     * when I go to /buy/1 I will receive Coco-rise and quarter and dime (0.35)
     *
     * when I post to /insert/dollar and go to /buy/1 I can see "no more in stock" and I have credit of 1
     * when I go to /buy/2 I will receive Panda-shake with no change
     * when I post to /service/add/ with new drink of id1, I will see drink has been added
     *
     * */

    private VendingMachineFacade vendingFacade = new VendingConfiguration().vendingFacade();
    private final Drink[] createDrinks = {
            DrinkFactory.cocoRise(1),
            DrinkFactory.pandaShake(3),
            DrinkFactory.fizzyBubble(10)
    };

    //    when I go to /drinks i see both drinks
    @Test
    public final void should_return_set_of_available_drinks() {
        //given
        final List<Drink> drinks = Arrays.asList(createDrinks);

        //when
        final List<Drink> getDrinksResult = vendingFacade.getDrinks();

        //then
        assertThat(getDrinksResult).isEqualTo(drinks);
    }


    //    when I go to /credit I have 0
    @Test
    public final void should_return_credit_of_zero() {
        //given
        final BigDecimal expectedCredit = BigDecimal.ZERO;

        //when
        final BigDecimal credit = vendingFacade.getCredit();

        //then
        assertThat(credit).isEqualTo(expectedCredit);

    }

    //    when I post to /insert/dime I can see I have credit of 0.1
    @Test
    public final void should_add_credit_of_tenth_when_dime_is_inserted() {
        //given
        final Money dime = Money.DIME;
        final BigDecimal expectedCredit = dime.getValue();

        //when
        vendingFacade.insert(dime);
        final BigDecimal actualCredit = vendingFacade.getCredit();

        //then
        assertThat(actualCredit).isEqualTo(expectedCredit);

    }

    //    when I post to /insert/quarter I can see I have credit of 0.35
    @Test
    public final void should_add_credit_of_0_point_35_when_quarter_and_dime_is_inserted() {
        //given
        final Money quarter = Money.QUARTER;
        final Money dime = Money.DIME;

        final BigDecimal expectedCredit = quarter.getValue().add(dime.getValue());

        //when
        vendingFacade.insert(quarter);
        vendingFacade.insert(dime);
        final BigDecimal actualCredit = vendingFacade.getCredit();

        //then
        assertThat(actualCredit).isEqualTo(expectedCredit);
    }

    //    when I post to /insert/dollar I can see I have credit of 1.35
    @Test
    public final void should_add_credit_of_1_point_35_when_quarter_and_dime_is_inserted() {
        //given
        final Money dollar = Money.DOLLAR;
        final Money quarter = Money.QUARTER;
        final Money dime = Money.DIME;

        final BigDecimal expectedCredit = dollar.getValue().add(quarter.getValue()).add(dime.getValue());

        //when
        vendingFacade.insert(dollar);
        vendingFacade.insert(quarter);
        vendingFacade.insert(dime);
        final BigDecimal actualCredit = vendingFacade.getCredit();

        //then
        assertThat(actualCredit).isEqualTo(expectedCredit);
    }

    //    when I go to /return I will get dime, quarter and dollar and I can see i have credit of 0
    @Test
    public final void credit_should_be_equal_zero_after_return_is_done() {
        //given
        final Money dollar = Money.DOLLAR;
        final Money quarter = Money.QUARTER;
        final Money dime = Money.DIME;
        vendingFacade.insert(dollar);
        vendingFacade.insert(quarter);
        vendingFacade.insert(dime);

        final BigDecimal expectedCredit = BigDecimal.ZERO;
        final Money[] insertedCoins = {dollar, quarter, dime};
        final List<Money> expectedCoinsReturned = Arrays.asList(insertedCoins);

        //when
        final List<Money> returnedMoney = vendingFacade.returnMoney();

        //then
        BigDecimal actualCredit = vendingFacade.getCredit();
        assertThat(actualCredit.compareTo(BigDecimal.ZERO)).isEqualTo(expectedCredit.compareTo(BigDecimal.ZERO));
        assertThat(returnedMoney).isEqualTo(expectedCoinsReturned);
    }

    //    when I go to /buy/1 and my credit is zero I can see "not enough credit to buy a drink"
    @Test
    public final void should_be_unable_to_buy_a_drink_with_credit_of_zero() throws NotEnoughCreditException {
        //given
        final BigDecimal expectedCredit = BigDecimal.ZERO;

        //when
        final Throwable throwable = catchThrowable(() -> vendingFacade.buyDrink(1L));

        //then
        final BigDecimal actualCredit = vendingFacade.getCredit();
        assertThat(actualCredit).isEqualTo(expectedCredit);
        assertThat(throwable.getMessage()).isNotEmpty();
    }

    //    when I post to /insert/dollar I can see I have credit of 1
//    when I go to /buy/1 I will receive Coco-rise and quarter and dime (0.35)
    @Test
    public final void should_buy_a_coco_rise_drink_and_get0_point_35_change_back() {
        //given
        final Money dollar = Money.DOLLAR;
        final Drink expectedDrink = DrinkFactory.cocoRise(1);

        //when
        vendingFacade.insert(dollar);
        final DrinkOrder actualOrder = vendingFacade.buyDrink(1L);

        //then
        final String actualOrderDrinkName = actualOrder.getOrderedDrink().getName();
        final List<Money> actualChange = actualOrder.getChange();
        assertThat(actualOrderDrinkName).isEqualTo(expectedDrink.getName());
        assertThat(actualChange.get(0)).isEqualTo(Money.QUARTER);
        assertThat(actualChange.get(1)).isEqualTo(Money.DIME);
    }

    //    when_I_post_to /insert/dollar and go to /buy/1 I can see "no more in stock" and I have credit of 1
    @Test
    public final void should_throw_no_more_in_stock_exception_when_no_more_in_stock() throws NoMoreInStockException {
        //given
        final Money dollar = Money.DOLLAR;

        //when
        vendingFacade.insert(dollar);
        vendingFacade.insert(dollar);
        vendingFacade.buyDrink(1L);
        final Throwable throwable = catchThrowable(() -> vendingFacade.buyDrink(1L));

        //then
        final List<Drink> drinks = vendingFacade.getDrinks();
        final Drink drink = drinks.get(0);
        assertThat(drink.getAmount()).isEqualTo(0);
        assertThat(throwable.getMessage()).isEqualTo("No more in stock");
    }

    //    when I go to /buy/2 I will receive Panda-shake with no change
    @Test
    public final void should_buy_panda_shake_ang_get_no_change() {
        //given
        final Money dollar = Money.DOLLAR;

        //when
        vendingFacade.insert(dollar);
        final long drinkId = 2L;
        final DrinkOrder order = vendingFacade.buyDrink(drinkId);

        //then

        final DrinkDto orderedDrink = order.getOrderedDrink();
        final String expectedDrinkName = "Panda Shake";
        assertThat(orderedDrink.getName()).isEqualTo(expectedDrinkName);
        assertThat(order.getChange()).isEmpty();
    }

    //    when I post to /service/add/ with new drink of id1, I will see drink has been added
    @Test
    public final void should_refill_drinks() {
        //given
        final List<Drink> drinksToRefill = Arrays.asList(createDrinks);
        final List<DrinkDto> drinkDtos  =
                Arrays.stream(createDrinks).map(Drink::dto).collect(Collectors.toList());
        final Integer cocoRiseAmount = drinksToRefill.get(0).getAmount();
        final Integer amountToAdd = 5;
        //when
        vendingFacade.refillDrinks(drinkDtos, amountToAdd);

        //then
        final Drink actualCocoRiseDrink = vendingFacade.getDrinks().get(0);
        assertThat(actualCocoRiseDrink.getAmount()).isEqualTo(cocoRiseAmount + amountToAdd);
    }
}

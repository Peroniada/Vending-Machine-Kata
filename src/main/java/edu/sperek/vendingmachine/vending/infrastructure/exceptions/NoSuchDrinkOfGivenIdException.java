package edu.sperek.vendingmachine.vending.infrastructure.exceptions;

public class NoSuchDrinkOfGivenIdException extends RuntimeException {
    public NoSuchDrinkOfGivenIdException(String message) {
        super(message);
    }
}

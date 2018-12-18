package edu.sperek.vendingmachine.vending.infrastructure.exceptions;

public class NoSuchDrinkOfGivenIdException extends RuntimeException {
    public NoSuchDrinkOfGivenIdException(final String message) {
        super(message);
    }
}

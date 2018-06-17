package edu.sperek.vendingmachine.vending.machine.domain;

class NotEnoughCreditException extends RuntimeException {
    NotEnoughCreditException(String message) {
        super(message);
    }
}

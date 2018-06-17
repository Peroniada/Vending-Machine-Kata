package edu.sperek.vendingmachine.vending.machine.domain;

class NoMoreInStockException extends RuntimeException {
    NoMoreInStockException(String message) {
        super(message);
    }
}

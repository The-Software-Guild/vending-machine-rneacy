package dev.rneacy.exception;

import dev.rneacy.dto.Item;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(Item item) {
        super(String.format("Not enough money for %s.\n", item.getName()));
    }
}

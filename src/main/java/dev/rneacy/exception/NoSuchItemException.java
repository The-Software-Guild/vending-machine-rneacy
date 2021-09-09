package dev.rneacy.exception;

import dev.rneacy.dto.Item;

public class NoSuchItemException extends VendingException {
    public NoSuchItemException(String name) {
        super(String.format("We don't have no %ss here.\n", name));
    }
}

package dev.rneacy.exception;

import dev.rneacy.dto.Item;

public class NoItemInventoryException extends VendingException {
    public NoItemInventoryException(Item item) {
        super(String.format("No %s left.\n", item.getName()));
    }
}

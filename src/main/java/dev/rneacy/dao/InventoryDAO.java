package dev.rneacy.dao;

import dev.rneacy.dto.Item;
import dev.rneacy.exception.NoSuchItemException;

import java.util.*;

public class InventoryDAO implements IInventoryDAO {
    private final Map<Item, Integer> items;

    public InventoryDAO() {
        items = new HashMap<>();
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    public int getItemStock(Item item) {
        return items.get(item);
    }

    public int getItemCount(Item item) {
        return items.size();
    }

    public int removeStock(Item item, int amount) {
        return items.put(item, items.get(item) - amount);
    }

    public void addItem(Item item, int stock) {
        items.put(item, stock);
    }
}

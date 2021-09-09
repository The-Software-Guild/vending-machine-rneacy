package dev.rneacy.service;

import dev.rneacy.dao.IInventoryDAO;
import dev.rneacy.dto.Item;

import java.util.HashMap;
import java.util.Map;

public class StubInventoryDAO implements IInventoryDAO {
    private Map<Item, Integer> items;

    Item item = new Item("Bueno", "1.20");
    Item item2 = new Item("Meme", "1.40");
    int stock = 100, stock2 = 0;

    public StubInventoryDAO() {
        items = new HashMap<>();
        items.put(item, stock);
        items.put(item2, stock2);
    }

    public StubInventoryDAO(Map<Item, Integer> items) {
        this.items = items;
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    public int getItemStock(Item item) {
        return this.item.getName().equals(item.getName()) ? stock : 0;
    }

    public int getItemCount() {
        return 1;
    }

    public int removeStock(Item item, int amount) {
        return stock-amount;
    }

    public void addItem(Item item, int stock) {

    }
}

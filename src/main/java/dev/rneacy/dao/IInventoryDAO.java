package dev.rneacy.dao;

import dev.rneacy.dto.Item;

import java.util.List;
import java.util.Map;

public interface IInventoryDAO {
    Map<Item, Integer> getItems(); // item to its count
    int getItemStock(Item item);
    int getItemCount();
    int removeStock(Item item, int amount);
    void addItem(Item item, int stock);
}

package dev.rneacy.service;

import dev.rneacy.dao.IAuditDAO;
import dev.rneacy.dao.IFundsDAO;
import dev.rneacy.dao.IInventoryDAO;
import dev.rneacy.dto.Change;
import dev.rneacy.dto.Item;
import dev.rneacy.exception.InsufficientFundsException;
import dev.rneacy.exception.NoItemInventoryException;
import dev.rneacy.exception.NoSuchItemException;
import dev.rneacy.exception.VendingException;
import dev.rneacy.main.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Component
public class VendingService {
    private final IFundsDAO funds;
    private final IInventoryDAO inventory;
    private final IAuditDAO audit;

    @Autowired
    public VendingService(IFundsDAO fundsDAO, IInventoryDAO inventoryDAO, IAuditDAO auditDAO) {
        funds = fundsDAO;
        inventory = inventoryDAO;
        audit = auditDAO;
    }

    public BigDecimal getFunds() {
        return funds.getFunds();
    }

    public BigDecimal addFunds(String amount) throws VendingException {
        BigDecimal amountD = Util.toFormat(amount);
        if(amountD.compareTo(BigDecimal.ZERO) < 1) throw new VendingException("You can't add negative funds.");
        audit.write(String.format("$%s was put in the machine.", amountD));
        return funds.addFunds(amountD);
    }

    public Change purchase(Item item) throws VendingException {
        if(inventory.getItemStock(item) <= 0) throw new NoItemInventoryException(item);
        if(funds.getFunds().compareTo(item.getPrice()) < 0) throw new InsufficientFundsException(item);

        inventory.removeStock(item, 1);
        audit.write(String.format("%s was purchased.", item.getName()));
        return funds.spendFunds(item.getPrice());
    }

    public Item getItemByName(String name) throws NoSuchItemException {
        Optional<Item> item = inventory.getItems().keySet().stream().filter(key -> key.getName().equalsIgnoreCase(name)).findFirst();

        if(item.isPresent()) {
            return item.get();
        }
        else {
            throw new NoSuchItemException(name);
        }
    }

    public Map<Item, Integer> getItems() {
        return inventory.getItems();
    }

    public void loadInventory() throws VendingException {
        try (BufferedReader reader = new BufferedReader(new FileReader("inv.txt"))) {
            String line = reader.readLine();
            String[] split = line.split("¬");

            for(String s : split) {
                if(s.isEmpty()) break;
                String[] split2 = s.split(",");
                Item item = new Item(split2[0].trim(), split2[1].trim());
                inventory.addItem(item, Integer.parseInt(split2[2]));
            }
        }
        catch(IOException ex) {
            throw new VendingException("Looks like we've got nothing to sell.");
        }
    }

    public void saveInventory() throws VendingException {
        try (BufferedWriter reader = new BufferedWriter(new FileWriter("inv.txt"))) {
            reader.write("");
            for(Map.Entry<Item, Integer> entry : inventory.getItems().entrySet()) {
                reader.append(String.format("%s,%s,%s¬", entry.getKey().getName(), entry.getKey().getPrice().toString(), entry.getValue()));
            }
        }
        catch(IOException ex) {
            throw new VendingException("Some of the products have quantum phased back into the machine.");
        }
    }
}

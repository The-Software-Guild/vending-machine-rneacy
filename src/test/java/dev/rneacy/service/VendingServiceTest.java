package dev.rneacy.service;

import dev.rneacy.dao.IAuditDAO;
import dev.rneacy.dao.IFundsDAO;
import dev.rneacy.dao.IInventoryDAO;
import dev.rneacy.dto.Change;
import dev.rneacy.dto.Item;
import dev.rneacy.exception.NoItemInventoryException;
import dev.rneacy.exception.NoSuchItemException;
import dev.rneacy.exception.VendingException;
import dev.rneacy.main.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendingServiceTest {
    private VendingService service;

    public VendingServiceTest() {
        IFundsDAO funds = new StubFundsDAO();
        IInventoryDAO inventory = new StubInventoryDAO();
        IAuditDAO audit = new StubAuditDAO();

        service = new VendingService(funds, inventory, audit);
    }

    @Test
    public void noNegativeFunds() {
        try{
            service.addFunds("-1.00");
        }
        catch(VendingException ex) {
            return;
        }

        fail("Should reject negative funds.");
    }

    @Test
    public void getCorrectItem() {
        try {
            Item item = new Item("Bueno", "1.20");
            Item check = service.getItemByName("Bueno");

            assertEquals(item.getName(), check.getName());
        }
        catch(NoSuchItemException ex) {
            fail("Should have found the item");
        }
    }

    @Test
    public void disallowNoStockPurchase() {
        try{
            Item item = service.getItemByName("Meme");
            service.purchase(item);
        }
        catch(NoSuchItemException ignored){}
        catch(VendingException ex) {
            if(ex instanceof NoItemInventoryException) {
                return;
            }
            else {
                fail("Wrong exception");
            }
        }

        fail("Should reject when no stock");
    }

    @Test
    public void correctChange() {
        Change expected = new Change(Util.toFormat("8.80"));
        Change change;
        try{
            Item item = service.getItemByName("Bueno");
            change = service.purchase(item);
            assertEquals(change, expected);
        }
        catch(VendingException ex) {
            fail("weird");
        }
    }
}
package dev.rneacy.control;

import dev.rneacy.dto.Change;
import dev.rneacy.dto.Item;
import dev.rneacy.exception.InsufficientFundsException;
import dev.rneacy.exception.NoItemInventoryException;
import dev.rneacy.exception.NoSuchItemException;
import dev.rneacy.exception.VendingException;
import dev.rneacy.service.VendingService;
import dev.rneacy.view.VendingView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class VendingControl {
    private final VendingService service;
    private final VendingView view;

    @Autowired
    public VendingControl(VendingService service, VendingView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean requestedExit = false;

        try {
            service.loadInventory();
        } catch (VendingException e) {
            view.showMsg(e.getMessage());
            System.exit(-1);
        }

        view.showInventoryBreakdown(service.getItems());

        while(!requestedExit) {
            view.showBanner("Balance $" + service.getFunds());
            VendingAction action = view.getMenuInput();

            switch(action) {
                case INSERT_FUNDS:
                    String amountStr = view.getStringWithMsg("How much: ");
                    try {
                        BigDecimal added = service.addFunds(amountStr);
                        view.showMsg(String.format("Added $%s.\n", added.toString()));
                    }
                    catch(VendingException ex) {
                        view.showMsg(ex.getMessage());
                    }
                    break;
                case PURCHASE:
                    String itemName = view.getStringWithMsg("Which item: ");
                    try {
                        Item toBuy = service.getItemByName(itemName);
                        Change change = service.purchase(toBuy);
                        view.showMsg(String.format("%s\n\nBye.\n", change));
                        requestedExit = true;
                    } catch (VendingException ex) {
                        view.showMsg(ex.getMessage());
                    }

                    break;
                case EXIT:
                    view.showMsg("Here's your money back; thanks for wasting my time.");
                    requestedExit = true;
            }

            try {
                service.saveInventory();
            } catch (VendingException e) {
                view.showMsg(e.getMessage());
            }
        }
    }
}

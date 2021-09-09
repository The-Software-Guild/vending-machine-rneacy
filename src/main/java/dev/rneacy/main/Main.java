package dev.rneacy.main;

import dev.rneacy.control.VendingControl;
import dev.rneacy.dao.FundsDAO;
import dev.rneacy.dao.InventoryDAO;
import dev.rneacy.service.VendingService;
import dev.rneacy.view.VendingView;

public class Main {
    public static void main(String[] args) {
        VendingService service = new VendingService(new FundsDAO(), new InventoryDAO());
        VendingView view = new VendingView();
        VendingControl control = new VendingControl(service, view);
        control.run();
    }
}

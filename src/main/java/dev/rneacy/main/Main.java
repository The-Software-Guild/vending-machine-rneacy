package dev.rneacy.main;

import dev.rneacy.control.VendingControl;
import dev.rneacy.dao.FundsDAO;
import dev.rneacy.dao.InventoryDAO;
import dev.rneacy.service.VendingService;
import dev.rneacy.view.VendingView;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("dev.rneacy");
        context.refresh();

        VendingControl control = context.getBean("vendingControl", VendingControl.class);
        control.run();
    }
}

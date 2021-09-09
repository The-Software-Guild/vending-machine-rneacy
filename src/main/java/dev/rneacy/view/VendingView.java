package dev.rneacy.view;

import dev.rneacy.control.VendingAction;
import dev.rneacy.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class VendingView {
    @Autowired
    private UserIO io;

    public VendingAction getMenuInput() {
        io.print("1: Insert money");
        io.print("2: Choose item");
        io.print("3: Exit");

        return VendingAction.values()[io.readInt("What do you want: ", 1, 3) - 1];
    }

    public void showBanner(String text) {
        String bannerPosts = new String(new char[text.length()]).replace("\0", "-");
        io.print("\n" + bannerPosts);
        io.print(text);
        io.print(bannerPosts);
    }

    public void showMsg(String msg) {
        io.print(msg + "\n");
    }

    public String getStringWithMsg(String msg){
        return io.readString(msg);
    }

    public void showInventoryBreakdown(Map<Item, Integer> inv) {
        for(Map.Entry<Item, Integer> pair : inv.entrySet()) {
            if (pair.getValue() > 0)
                showMsg(String.format("%s (%s left): $%s", pair.getKey().getName(), pair.getValue(), pair.getKey().getPrice()));
        }
    }
}

package dev.rneacy.main;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Util {

    public static BigDecimal toFormat(String amount) {
        BigDecimal d = new BigDecimal(amount);
        return d.setScale(2, RoundingMode.HALF_UP);
    }

    private Util() {}
}

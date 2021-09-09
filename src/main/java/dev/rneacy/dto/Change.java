package dev.rneacy.dto;

import dev.rneacy.main.Util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Objects;

public class Change {

    private BigDecimal quarters, dimes, nickels, cents;
    private final BigDecimal quarter = Util.toFormat("25");
    private final BigDecimal dime = Util.toFormat("10");
    private final BigDecimal nickel = Util.toFormat("5");

    public Change(BigDecimal funds) {
        BigDecimal total = funds.multiply(Util.toFormat("100"));

        quarters = total.divide(quarter, RoundingMode.HALF_UP);
        if(quarters.compareTo(BigDecimal.ONE) >= 0) total = total.subtract(quarter.multiply(quarters));

        dimes = total.divide(dime, RoundingMode.HALF_UP);
        if(dimes.compareTo(BigDecimal.ONE) >= 0) total = total.subtract(dime.multiply(dimes));

        nickels = total.divide(nickel, RoundingMode.HALF_UP);
        if(nickels.compareTo(BigDecimal.ONE) >= 0) total = total.subtract(nickel.multiply(nickels));

        cents = total;
    }

    public BigDecimal getQuarters() {
        return quarters;
    }

    public BigDecimal getDimes() {
        return dimes;
    }

    public BigDecimal getNickels() {
        return nickels;
    }

    public BigDecimal getCents() {
        return cents;
    }

    @Override
    public String toString() {
        //setScale(0, RoundingMode.DOWN)
        return "Your change: " +
                quarters.toBigInteger() + " quarters, " +
                dimes.toBigInteger() + " dimes, " +
                nickels.toBigInteger() + " nickels, and " +
                cents.toBigInteger() + " cents.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Change change = (Change) o;
        return Objects.equals(quarters, change.quarters) && Objects.equals(dimes, change.dimes) && Objects.equals(nickels, change.nickels) && Objects.equals(cents, change.cents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quarters, dimes, nickels, cents);
    }
}

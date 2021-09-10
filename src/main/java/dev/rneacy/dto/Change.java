package dev.rneacy.dto;

import dev.rneacy.main.Util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Objects;

public class Change {

    private BigDecimal quarters, dimes, nickels, cents;

    private enum Coin {
        QUARTER(Util.toFormat("25")),
        DIME(Util.toFormat("10")),
        NICKEL(Util.toFormat("5"));

        private BigDecimal value;

        public BigDecimal getValue() {
            return value;
        }

        Coin(BigDecimal value) {
            this.value = value;
        }
    }

    public Change(BigDecimal funds) {
        BigDecimal total = funds.multiply(Util.toFormat("100"));

        quarters = total.divide(Coin.QUARTER.getValue(), RoundingMode.HALF_UP);
        if(quarters.compareTo(BigDecimal.ONE) >= 0) total = total.subtract(Coin.QUARTER.getValue().multiply(quarters));

        dimes = total.divide(Coin.DIME.getValue(), RoundingMode.HALF_UP);
        if(dimes.compareTo(BigDecimal.ONE) >= 0) total = total.subtract(Coin.DIME.getValue().multiply(dimes));

        nickels = total.divide(Coin.NICKEL.getValue(), RoundingMode.HALF_UP);
        if(nickels.compareTo(BigDecimal.ONE) >= 0) total = total.subtract(Coin.NICKEL.getValue().multiply(nickels));

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

package dev.rneacy.dao;

import dev.rneacy.dto.Change;
import dev.rneacy.main.Util;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FundsDAO implements IFundsDAO {

    private BigDecimal funds;

    public FundsDAO() {
        funds = Util.toFormat("0");
    }

    public BigDecimal getFunds() {
        return funds;
    }

    public BigDecimal addFunds(BigDecimal amount) {
        funds = funds.add(amount);
        return funds;
    }

    public Change spendFunds(BigDecimal amount) {
        funds = funds.subtract(amount);
        return new Change(funds);
    }
}
